public enum SupportedCommands {
    ECHO("echo");

    private String value;

    SupportedCommands(String value) {
        this.value = value;
    }

    public static SupportedCommands findByValue(String input) {
        for (SupportedCommands command : values()){
            if (command.toString().equalsIgnoreCase(input))
                return command;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
