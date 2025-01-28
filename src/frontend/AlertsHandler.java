package frontend;

public class AlertsHandler {
    public static void on_level_map_file_corruption(int lvl){
        System.out.println("a level map file has been corrupted make sure there is a file named levels_dir\\levels_dir\\level"+lvl+".txt");
    }
}
