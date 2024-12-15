public enum BuiltInCommands implements ICommand {
    ECHO("echo") {
        @Override
        public String getOutput(String param) {
            return param;
        }
    },
    TYPE("type") {
        @Override
        public String getOutput(String param) {
            if (BuiltInCommands.findByValue(param) != null)
                return param + " is a shell builtin";
            else {
                String path = Main.getPath(param);
                if (path != null)
                    return param + " is " + path;
                else
                    return param + ": not found";
            }
        }
    },
    EXIT("exit") {
        @Override
        public String getOutput(String param) {
            System.exit(0);
            return "";
        }
    },
    PWD("pwd") {
        @Override
        public String getOutput(String param) {
            return System.getProperty("user.dir");
        }
    },
    CD("cd") {
        @Override
        public String getOutput(String param) {
            return System.getProperty("user.dir");
        }
    };

    private String value;

    BuiltInCommands(String value) {
        this.value = value;
    }

    public static BuiltInCommands findByValue(String input) {
        for (BuiltInCommands command : values()){
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
