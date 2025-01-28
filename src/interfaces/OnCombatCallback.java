package interfaces;

import data_records.BattleData;

public interface OnCombatCallback {
    public void execute(BattleData battleData);
}
