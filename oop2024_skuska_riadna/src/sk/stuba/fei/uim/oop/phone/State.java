package sk.stuba.fei.uim.oop.phone;

import sk.stuba.fei.uim.oop.application.Application;

abstract class State {
    /**
     * Returns the name of the state for display purposes.
     * Uses the class name and removes "State" suffix.
     */
    String getName() {
        return getClass().getSimpleName().replace("State", "");
    }

    /**
     * Template method pattern - all states must implement these behaviors
     * but by default they do nothing (most states ignore most actions)
     */

    // Handle power button press
    void powerButtonPressed(Phone phone) {
        // Default: do nothing
    }

    // Handle back button press
    void backButtonPressed(Phone phone) {
        // Default: do nothing
    }

    // Handle application installation
    void install(Phone phone, Application app) {
        // Default: do nothing
    }

    // Handle application uninstallation
    void uninstall(Phone phone, Application app) {
        // Default: do nothing
    }

    // Handle application start
    void start(Phone phone, Application app) {
        // Default: do nothing
    }

    // Handle password entry
    void passwordEntered(Phone phone, String password) {
        // Default: do nothing
    }
}

/**
 * Locked state - the initial state of the phone
 * Only responds to power button press
 */
class LockedState extends State {
    @Override
    void powerButtonPressed(Phone phone) {
        // Transition to WaitingForPassword state
        phone.setState(new WaitingForPasswordState());
    }
}

/**
 * WaitingForPassword state - waiting for user to enter password
 * Can start applications that are startable from locked screen
 */
class WaitingForPasswordState extends State {
    @Override
    void passwordEntered(Phone phone, String password) {
        if (Phone.getCorrectPassword().equals(password)) {
            // Correct password - go to homescreen
            phone.setState(new HomescreenState());
        }
        // Incorrect password - stay in this state
    }

    @Override
    void start(Phone phone, Application app) {
        if (app.startableFromLockedScreen() && phone.getInstalledApplications().contains(app)) {
            // Can start apps like Camera from locked screen
            phone.setRunningApplication(app);
            phone.setState(new ApplicationRunningState(true)); // Started from locked
        }
        // Otherwise do nothing
    }
}

/**
 * Homescreen state - phone is unlocked and at home screen
 * Can start/uninstall applications
 */
class HomescreenState extends State {
    @Override
    void powerButtonPressed(Phone phone) {
        // Lock the phone
        phone.setState(new LockedState());
    }

    @Override
    void start(Phone phone, Application app) {
        if (phone.getInstalledApplications().contains(app)) {
            phone.setRunningApplication(app);
            phone.setState(new ApplicationRunningState(false)); // Started from homescreen
        }
    }

    @Override
    void uninstall(Phone phone, Application app) {
        // Remove the application if it's installed
        phone.removeApplication(app);
    }
}

/**
 * ApplicationRunning state - an application is currently running
 * Can install apps (if running app supports it), go back, or lock
 */
class ApplicationRunningState extends State {
    // Track where we came from to properly handle back button
    private final boolean startedFromLocked;

    ApplicationRunningState(boolean startedFromLocked) {
        this.startedFromLocked = startedFromLocked;
    }

    @Override
    void powerButtonPressed(Phone phone) {
        // Lock the phone and stop the application
        phone.setRunningApplication(null);
        phone.setState(new LockedState());
    }

    @Override
    void backButtonPressed(Phone phone) {
        // Stop the running application
        phone.setRunningApplication(null);

        // Go back to where we came from
        if (startedFromLocked) {
            phone.setState(new LockedState());
        } else {
            phone.setState(new HomescreenState());
        }
    }

    @Override
    void install(Phone phone, Application app) {
        // Can only install if the running app supports installation (like AppStore)
        Application runningApp = phone.getRunningApplication();
        if (runningApp != null && runningApp.canInstallApplication()) {
            phone.addApplication(app);
        }
    }
}