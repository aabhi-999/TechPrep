/**
 * ANSI escape codes for coloured console output.
 * Automatically disabled on Windows (cmd/powershell lack ANSI support by default).
 */
public class Color {

    private static final boolean ENABLED = !System.getProperty("os.name", "")
                                                   .toLowerCase().contains("win");

    public static final String RESET   = code("\033[0m");
    public static final String BOLD    = code("\033[1m");
    public static final String DIM     = code("\033[2m");
    public static final String GREEN   = code("\033[32m");
    public static final String RED     = code("\033[31m");
    public static final String YELLOW  = code("\033[33m");
    public static final String CYAN    = code("\033[36m");
    public static final String MAGENTA = code("\033[35m");
    public static final String BLUE    = code("\033[34m");
    public static final String BG_GREEN= code("\033[42m");
    public static final String BG_RED  = code("\033[41m");

    private static String code(String s) { return ENABLED ? s : ""; }

    public static String green(String s)   { return GREEN   + s + RESET; }
    public static String red(String s)     { return RED     + s + RESET; }
    public static String yellow(String s)  { return YELLOW  + s + RESET; }
    public static String cyan(String s)    { return CYAN    + s + RESET; }
    public static String magenta(String s) { return MAGENTA + s + RESET; }
    public static String bold(String s)    { return BOLD    + s + RESET; }
}
