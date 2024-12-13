import java.util.ArrayList;
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

            List<String> supportedCommands = new ArrayList<>();
            if (!supportedCommands.contains(input)) {
                System.out.println(input + ": not found");
            }
        }
    }
}
