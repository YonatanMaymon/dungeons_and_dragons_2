package backend.Tiles;

import data_records.AbilityUseData;
import exeptions.InsufficientResourcesException;

import java.util.function.Consumer;

public interface HeroicUnit {
    void cast_ability();
    void on_ability_cast();

}
