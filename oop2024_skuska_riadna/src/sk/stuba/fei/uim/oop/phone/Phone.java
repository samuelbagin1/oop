package sk.stuba.fei.uim.oop.phone;

import sk.stuba.fei.uim.oop.application.Application;

import java.util.HashSet;
import java.util.Set;

public class Phone {
    // The current state of the phone - this is the key to the State pattern
    // We delegate all behavior to this state object
    private State state;

    // Set of installed applications
    private Set<Application> installedApplications;

    // Currently running application (null if none)
    private Application runningApplication;

    // Password for unlocking the phone
    private static final String CORRECT_PASSWORD = "123";

    public Phone() {
        // Initialize with preinstalled applications
        this.installedApplications = new HashSet<>();
        this.installedApplications.add(new Application("Firefox", false, false));
        this.installedApplications.add(new Application("AppStore", true, false));
        this.installedApplications.add(new Application("Camera", false, true));

        // Start in the Locked state
        this.state = new LockedState();
        this.runningApplication = null;
    }

    // Package-private method to change state - only states can call this
    void setState(State newState) {
        this.state = newState;
    }

    // Package-private getter for password validation
    static String getCorrectPassword() {
        return CORRECT_PASSWORD;
    }

    // Package-private setter for running application
    void setRunningApplication(Application app) {
        this.runningApplication = app;
    }

    // Package-private method to add an application
    void addApplication(Application app) {
        this.installedApplications.add(app);
    }

    // Package-private method to remove an application
    void removeApplication(Application app) {
        this.installedApplications.remove(app);
    }

    // All public methods delegate to the current state
    public void powerButtonPressed() {
        state.powerButtonPressed(this);
    }

    public void backButtonPressed() {
        state.backButtonPressed(this);
    }

    public void install(Application app) {
        state.install(this, app);
    }

    public void uninstall(Application app) {
        state.uninstall(this, app);
    }

    public void start(Application app) {
        state.start(this, app);
    }

    public void passwordEntered(String password) {
        state.passwordEntered(this, password);
    }

    public Set<Application> getInstalledApplications() {
        return new HashSet<>(installedApplications);
    }

    public Application getRunningApplication() {
        return runningApplication;
    }

    public String getStateName() {
        return state.getName();
    }
}