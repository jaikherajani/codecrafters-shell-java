import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage

        // infinite loop for REPL
        while (true) {
            System.out.print("$ ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            String[] ops = input.split(" ", 2);

            if (SupportedCommands.findByValue(ops[0]) == null) {
                System.out.println(input + ": not found");
            }
            else {
                SupportedCommands command = SupportedCommands.findByValue(ops[0]);
                switch (command) {
                    case EXIT:
                        System.exit(0);
                        break;
                    case ECHO:
                        System.out.println(ops[1]);
                        break;
                    case TYPE:
                        if (SupportedCommands.findByValue(ops[1]) != null)
                            System.out.println(ops[1] + " is a shell builtin");
                        else {
                            String path = getPath(ops[1]);
                            if (path != null)
                                System.out.println(ops[1] + " is " + path);
                            else
                                System.out.println(ops[1] + ": not found");
                        }
                        break;
                }
            }
        }
    }

    private static String getPath(String name){
        // read PATH value
        for(String path : System.getenv("PATH").split(":")){
            Path fullPath = Path.of(path, name);
            if (Files.isRegularFile(fullPath))
                return fullPath.toString();
        }
        return null;
    }
}
