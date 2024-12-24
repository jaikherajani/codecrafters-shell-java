import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
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

            BuiltInCommands command = BuiltInCommands.findByValue(ops[0]);
            if (command != null) {
                command.getOutput(ops.length > 1 ? ops[1] : "");
            }
            else {
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
                    System.out.println(ops[0] + ": command not found");
            }
        }
    }

    public static String getPath(String name){
        // read PATH value
        for(String path : System.getenv("PATH").split(":")){
            Path fullPath = Path.of(path, name);
            if (Files.isRegularFile(fullPath))
                return fullPath.toString();
        }
        return null;
    }
}
