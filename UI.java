public class UI {

    public static void banner() {
        System.out.println();
        System.out.println(Color.CYAN + Color.BOLD);
        System.out.println("  ==========================================");
        System.out.println("   _____ _____ ____ _   _ ____  ____  _____ ____  ");
        System.out.println("  |_   _| ____/ ___| | | |  _ \\|  _ \\| ____|  _ \\ ");
        System.out.println("    | | |  _|| |   | |_| | |_) | |_) |  _| | |_) |");
        System.out.println("    | | | |__| |___|  _  |  __/|  _ <| |___|  __/ ");
        System.out.println("    |_| |_____\\____|_| |_|_|   |_| \\_\\_____|_|    ");
        System.out.println("  ==========================================");
        System.out.println(Color.RESET);
        System.out.println(Color.DIM + "         Interview Readiness Assessment Tool  v2.0" + Color.RESET);
        System.out.println(Color.DIM + "         Java • Python • C++                       " + Color.RESET);
        System.out.println();
    }

    public static void line(char ch, int len) {
        System.out.println("  " + String.valueOf(ch).repeat(len));
    }

    public static void pause(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    public static void clearScreen() {
        // Works on most Unix terminals; harmless on Windows
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
