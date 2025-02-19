package frontend;

public class AlertsHandler {
    static final String RED = "\u001B[31m";
    static final String RESET = "\u001B[0m";

    public static void on_level_map_file_corruption(int lvl){
        System.out.println("a level map file has been corrupted make sure there is a file named levels_dir\\levels_dir\\level"+lvl+".txt");
    }

    public static void print_exception(Exception e){
        System.out.println(RED + e.getMessage() + RESET);
        InterfaceManager.sleep();
    }
}
