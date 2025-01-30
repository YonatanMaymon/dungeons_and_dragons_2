package data_records;


import backend.Tiles.Enemies.Enemy;

import java.util.Arrays;
import java.util.Map;

public record AbilityUseData(String name, Map<String, Integer> damageMap) {
}
