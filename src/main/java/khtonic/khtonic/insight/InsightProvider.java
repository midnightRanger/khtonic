package khtonic.khtonic.insight;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InsightProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
        public static Capability<Insight> PLAYER_INSIGHT = CapabilityManager.get(new CapabilityToken<Insight>() {});
        private Insight insight = null;
        private final LazyOptional<Insight> optional = LazyOptional.of(this::createInsight);

        private Insight createInsight() {
            if(this.insight == null) {
                this.insight = new Insight();
            }

            return this.insight;
        }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_INSIGHT) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
            CompoundTag nbt = new CompoundTag();
            createInsight().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createInsight().loadNBTData(nbt);
    }
}
