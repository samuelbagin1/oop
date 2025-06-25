package sk.stuba.fei.uim.oop.application;

import java.util.Objects;

public class Application {
    private String name;
    private boolean canInstall;
    private boolean startableFromLocked;

    public Application(String name, boolean canInstallApplication, boolean startableFromLockedScreen) {
        this.name = name;
        this.canInstall = canInstallApplication;
        this.startableFromLocked = startableFromLockedScreen;
    }

    public Application(String name) {
        this(name, false, false);
    }

    public String getName() {
        return name;
    }

    public boolean canInstallApplication() { // ci aplikacia sluzi na instalovanie dalsich aplikacii
        return canInstall;
    }

    public boolean startableFromLockedScreen() { // ci mozno spustit bez zadania hesla
        return startableFromLocked;
    }



    /**
     * Two applications are equal if they have the same name AND the same capabilities.
     * This ensures that an app with the same name but different permissions
     * is treated as a different application.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;
        return canInstall == that.canInstall &&
                startableFromLocked == that.startableFromLocked &&
                Objects.equals(name, that.name);
    }

    /**
     * hashCode must be consistent with equals - if two objects are equal,
     * they must have the same hash code. We include all fields used in equals.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, canInstall, startableFromLocked);
    }
}
