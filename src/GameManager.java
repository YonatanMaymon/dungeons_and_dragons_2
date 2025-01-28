import backend.Tiles.PlayerTypes.Warrior;
import enums.GAME_STATE;
import backend.gameLogic.LevelMap;
import frontend.AlertsHandler;
import frontend.InterfaceManager;

import java.io.IOException;

public class GameManager {

    static GAME_STATE _gameState;
    int _level;

    public GameManager() {
//        _gameState = GAME_STATE.CHARACTER_SELECTION;
        _gameState = GAME_STATE.GAME_PLAYING; // remove past testing
        _level = 1;
    }

    public void game_loop(){
        try {
            Warrior player = new Warrior
                (
                    "yosi",
                    "description",
                    5,
                    100,
                    20,
                    5
                );
            LevelMap map = new LevelMap
                    (_level, player, InterfaceManager::print_combat_log);
            map.loudMap();
            while (_gameState == GAME_STATE.GAME_PLAYING){
                map.update();
            }
        } catch (IOException e) {
            AlertsHandler.on_level_map_file_corruption(_level);
            return;
        }
    }


    public int get_level() {
        return _level;
    }

    public void set_level(int _level) {
        this._level = _level;
    }

    public static GAME_STATE get_gameState() {
        return _gameState;
    }

    public static void set_gameState(GAME_STATE _gameState) {
        GameManager._gameState = _gameState;
    }
}
