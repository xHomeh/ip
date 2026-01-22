public class Blue {
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

    private static void bye() {
        String goodbye = "__________________________________________________ \n"
                    + "Byeee~ See you soon! \n"
                    + "__________________________________________________ \n";
        System.out.println(goodbye);
    }

    public static void main(String[] args) {
        greet();
    }
}
