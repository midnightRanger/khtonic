package khtonic.khtonic.networking;

import khtonic.khtonic.Khtonic;
import khtonic.khtonic.networking.packet.InsightDataSyncS2CPacket;
import khtonic.khtonic.networking.packet.InsightS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Khtonic.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        INSTANCE = net;

        net.messageBuilder(InsightS2CPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(InsightS2CPacket::new)
                .encoder(InsightS2CPacket::toBytes)
                .consumerMainThread(InsightS2CPacket::handle)
                .add();

        net.messageBuilder(InsightDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(InsightDataSyncS2CPacket::new)
                .encoder(InsightDataSyncS2CPacket::toBytes)
                .consumerMainThread(InsightDataSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
