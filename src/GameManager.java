import backend.Tiles.Player;
import backend.Util;
import backend.gameLogic.UnitFactory;
import backend.gameLogic.LevelMap;
import exeptions.DebugException;
import exeptions.InsufficientResourcesException;
import frontend.AlertsHandler;
import frontend.InterfaceManager;

import java.io.IOException;

public class GameManager {

    public void game_loop() {
        UnitFactory unitFactory = new UnitFactory();
        int playerNum = 0;
        try {
            playerNum = InterfaceManager.choose_character(unitFactory.list_players());
        } catch (RuntimeException e) {
            AlertsHandler.print_exception(e);
            game_loop();
        }
        Player player = unitFactory.get_player(playerNum);
        for (int level = 1; level < Util.get_max_lvl(); level++) {
            tick_loop(level,player);
        }
    }
    void tick_loop(int level, Player player){
        try {
            LevelMap map = new LevelMap
                    (
                            level,
                            player,
                            InterfaceManager::print_combat_log,
                            InterfaceManager::print_ability_use_log,
                            InterfaceManager::kill_msg,
                            InterfaceManager::print_map_and_stats
                    );
            map.loud_map();
            while (map.levelPlaying){
                try {
                    map.update();
                }catch (InsufficientResourcesException e){
                    AlertsHandler.print_exception(e);
                }
            }

        } catch (IOException e) {
            AlertsHandler.on_level_map_file_corruption(level);
        } catch (DebugException e){
            AlertsHandler.print_exception(e);
        }
    }

}
