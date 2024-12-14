import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
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
                String path = getPath(ops[0]);
                if (path != null) {
                    //System.out.println(ops[1] + " is " + path);
                    ProcessBuilder pb = new ProcessBuilder(path, ops[1]);
                    Process p = pb.start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        System.out.println(line);
                    }
                }
                else
                    System.out.println(ops[0] + ": not found");
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
