package khtonic.khtonic.networking.packet;

import khtonic.khtonic.init.ItemInit;
import khtonic.khtonic.insight.InsightProvider;
import khtonic.khtonic.networking.ModMessages;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class InsightS2CPacket {

    public InsightS2CPacket() {

    }

    public InsightS2CPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    private static final String MESSAGE_INSIGHT_UP = "message.khtonic.insight_up";

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

                    ServerPlayer player = context.getSender();
                    ServerLevel level = player.getLevel();

                        player.sendSystemMessage(Component.translatable(MESSAGE_INSIGHT_UP).withStyle(ChatFormatting.ITALIC));
                        level.playSound(null, player.getOnPos(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS,
                                0.5f, level.random.nextFloat() * 0.1f * 0.9f);

                            player.getCapability(InsightProvider.PLAYER_INSIGHT).ifPresent(insight -> {
                            insight.addInsight(10);
                            player.sendSystemMessage(Component.literal("Current insight: " + insight.getInsight()));
                                ModMessages.sendToPlayer(new InsightDataSyncS2CPacket(insight.getInsight()), player);
                        });
                }
        );
        return true;
    }

    private boolean hasInsightEvent(ServerPlayer player, ServerLevel level) {
        return player.getInventory().contains(new ItemStack(ItemInit.NECRONOMICON.get(), 1));
    }



}
