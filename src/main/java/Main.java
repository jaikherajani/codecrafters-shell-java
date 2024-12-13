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

            String[] ops = input.split(" ", 2);

            if ("exit".equals(ops[0]) && "0".equals(ops[1]))
                break;

            if (SupportedCommands.findByValue(ops[0]) == null) {
                System.out.println(input + ": not found");
            }
            else {
                SupportedCommands command = SupportedCommands.findByValue(ops[0]);
                switch (command) {
                    case SupportedCommands.ECHO:
                        System.out.println(ops[1]);
                }
            }
        }
    }
}
