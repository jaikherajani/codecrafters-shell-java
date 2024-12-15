import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public enum BuiltInCommands implements ICommand {
    ECHO("echo") {
        @Override
        public void getOutput(String param) {
            System.out.println(param);
        }
    },
    TYPE("type") {
        @Override
        public void getOutput(String param) {
            if (BuiltInCommands.findByValue(param) != null)
                System.out.println(param + " is a shell builtin");
            else {
                String path = Main.getPath(param);
                if (path != null)
                    System.out.println(param + " is " + path);
                else
                    System.out.println(param + ": not found");
            }
        }
    },
    EXIT("exit") {
        @Override
        public void getOutput(String param) {
            System.exit(0);
        }
    },
    PWD("pwd") {
        @Override
        public void getOutput(String param) {
            System.out.println(System.getProperty("user.dir"));
        }
    },
    CD("cd") {
        @Override
        public void getOutput(String param) {
            Path fullPath = Paths.get(param);
            if (Files.exists(fullPath)) {
                System.setProperty("user.dir", param);
            }
            else
                System.out.println("cd: "+ param + ": No such file or directory");
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
