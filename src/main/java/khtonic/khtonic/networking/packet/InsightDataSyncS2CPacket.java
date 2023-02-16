package khtonic.khtonic.networking.packet;

import khtonic.khtonic.client.ClientInsightData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;


public class InsightDataSyncS2CPacket {
    private final int insight;

    public InsightDataSyncS2CPacket(int insight) {
        this.insight = insight;
    }

    public InsightDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.insight = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(insight);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientInsightData.set(insight);
        });
        return true;
    }
}
