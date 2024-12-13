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
                        else
                            System.out.println(ops[1] + ": not found");
                }
            }
        }
    }
}
