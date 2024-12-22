package lts;

/**
 * Logger and printer tools.
 *
 * @pattern Singleton
 * @version 1.0
 * @author bufferum7011
 */
public class Print {


    ////////// Constructors //////////
    private Print() {
        printer_enable();
        logger_enable();
    }


    ////////// Variables //////////
    private static Print print;
    private static boolean key_print;
    private static boolean key_logger;
    private static String log;


    ////////// Methods //////////
    public static void printer_enable() { key_print = true; }
    public static void printer_disable() { key_print = false; }
    public static void logger_enable() { key_logger = true; }
    public static void logger_disable() { key_logger = false; }

    public static String get_log() { return log; }

    public static void result(Object cmd) { condition(cmd, Colors.YELLOW); }
    public static void way(Object cmd)    { condition(cmd, Colors.BLUE); }
    public static void debag(Object cmd)  { condition(cmd, Colors.PURPLE); }
    public static void error(Object cmd)  { condition(cmd, Colors.RED_BACKGROUND); }

    private static void condition(Object cmd, Colors color) {

        // For singleton
        if(print == null) {
            print = new Print();
        }

        if(key_print) {
            System.out.print(color.out + cmd + Colors.TEXT_RESET.out);
        }

        if(key_logger) {
            log += cmd;
        }

    }


    ////////// Enums //////////
    private enum Colors {

        ////////// BASE //////////
        YELLOW("\033[1;93m"),
        BLUE("\033[1;94m"),
        PURPLE("\033[1;95m"),
        RED("\033[1;91m"),
        BLACK("\033[1;90m"),
        WHITE("\033[1;97m"),
        CYAN("\033[1;96m"),
        GREEN("\033[1;92m"),

        ////////// BACKGROUNDS //////////
        YELLOW_BACKGROUND("\033[0;103m"),
        BLUE_BACKGROUND("\033[0;104m"),
        PURPLE_BACKGROUND("\033[0;105m"),
        RED_BACKGROUND("\033[0;101m"),
        BLACK_BACKGROUND("\033[0;100m"),
        WHITE_BACKGROUND("\033[0;107m"),
        CYAN_BACKGROUND("\033[0;106m"),
        GREEN_BACKGROUND("\033[0;102m"),

        ////////// UNDERLINE //////////
        YELLOW_UNDERLINED("\033[4;33m"),
        BLUE_UNDERLINED("\033[4;34m"),
        PURPLE_UNDERLINED("\033[4;35m"),
        RED_UNDERLINED("\033[4;31m"),
        BLACK_UNDERLINED("\033[4;30m"),
        WHITE_UNDERLINED("\033[4;37m"),
        CYAN_UNDERLINED("\033[4;36m"),
        GREEN_UNDERLINED("\033[4;32m"),

        ////////// TEXT_RESET //////////
        TEXT_RESET("\033[0m");


        ////////// Constructors //////////
        private String out;
        private Colors(String out) { this.out = out; }

    }


}