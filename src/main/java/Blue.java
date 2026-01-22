import java.util.Scanner;

public class Blue {
    // Prints the greeting message when the chatbot starts running
    private static void greet() {
        String logo = " ____  _            \n"
                + "|  _ \\| |             \n"
                + "| |_) | |_   _  ___    \n"
                + "|  _ <| | | | |/ _ \\  \n"
                + "| |_) | | |_| |  __/   \n"
                + "|____/|_|\\__,_|\\___| \n";
        System.out.println(logo);

        String greeting = "__________________________________________________ \n"
                    + "Hi☆・*。It's me, Blue! \n"
                    + "What do you need help with? \n"
                    + "__________________________________________________ \n";
        System.out.println(greeting);
    }

    // Prints the goodbye message when the chatbot is quit
    private static void bye() {
        String goodbye = "__________________________________________________ \n"
                    + "Byeee~ See you soon! \n"
                    + "__________________________________________________ \n";
        System.out.println(goodbye);
    }

    // Returns a boolean and checks if the user typed either quit, bye, or exit to quit the chatbot
    private static boolean checkExitInput(String input) {
        return input.equalsIgnoreCase("bye") || input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit");
    }

    private static void wrapTextWithLines(String str) {
        String wrappedText = "__________________________________________________ \n"
                + str + "\n"
                + "__________________________________________________ \n";
        System.out.println(wrappedText);
    }

    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (checkExitInput(input)) {
                bye();
                break;
            }

            wrapTextWithLines(input);
        }
    }
}
