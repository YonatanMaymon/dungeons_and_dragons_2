package data_records;

public record BattleData(String attacker, String defender,int attackRoll, int defenceRoll, int remainingHealth) {
    public int damage_taken(){
        return Math.max(attackRoll - defenceRoll,0);
    }
}
