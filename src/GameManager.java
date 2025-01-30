import backend.Tiles.Player;
import backend.gameLogic.UnitFactory;
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
            UnitFactory unitFactory = new UnitFactory();
            int playerNum = 0;
            try {
                playerNum = InterfaceManager.choose_character(unitFactory.listPlayers());
            }catch (RuntimeException e){
                AlertsHandler.print_exception(e);
                game_loop();
            }
            Player player = unitFactory.get_player(playerNum);
            LevelMap map = new LevelMap
                    (
                        _level,
                        player,
                        InterfaceManager::print_combat_log,
                        InterfaceManager::print_ability_use_log
                    );
            map.loudMap();
            while (_gameState != GAME_STATE.GAME_OVER){
                if (_gameState == GAME_STATE.GAME_PLAYING)
                    map.update();
                else if (_gameState == GAME_STATE.CHARACTER_SELECTION) {

                }
            }
        } catch (IOException e) {
            AlertsHandler.on_level_map_file_corruption(_level);
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
