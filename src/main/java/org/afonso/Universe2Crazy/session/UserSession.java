package org.afonso.Universe2Crazy.session;

public class UserSession {
    private boolean godModeEnabled = false;

    public boolean isGodModeEnabled() {
        return godModeEnabled;
    }

    public void enableGodMode() {
        godModeEnabled = true;
    }

    public void disableGodMode() {
        godModeEnabled = false;
    }
}
