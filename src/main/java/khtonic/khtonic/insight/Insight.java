package khtonic.khtonic.insight;

import net.minecraft.nbt.CompoundTag;

public class Insight {
    private int insight;
    private final int MIN_INSIGHT = 0;
    private final int MAX_INSIGHT = 100;

    public int getInsight() {
        return insight;
    }

    public void addInsight(int value) {
        this.insight = Math.min(insight + value, MAX_INSIGHT);
    }

    public void subInsight(int value) {
        this.insight = Math.max(insight - value, MIN_INSIGHT);
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("insight", insight);
    }

    public void copyFrom(Insight source) {
        this.insight = source.insight;
    }

    public void loadNBTData(CompoundTag nbt) {
        insight = nbt.getInt("insight");
    }

}
