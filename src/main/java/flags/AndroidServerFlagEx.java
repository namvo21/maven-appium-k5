package flags;

import io.appium.java_client.service.local.flags.ServerArgument;

public enum AndroidServerFlagEx implements ServerArgument {

    ALLOW_INSECURE("--allow-insecure");

    private String arg;
    AndroidServerFlagEx(String arg) {
        this.arg= arg;
    }

    @Override
    public String getArgument() {
        return this.arg;
    }
}
