package khtonic.khtonic.event;

import khtonic.khtonic.Khtonic;
import khtonic.khtonic.init.ItemInit;
import khtonic.khtonic.networking.ModMessages;
import khtonic.khtonic.networking.packet.InsightS2CPacket;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvent {
    @Mod.EventBusSubscriber(modid = Khtonic.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onGetItem(PlayerEvent.ItemPickupEvent event) {
            ItemStack necronomicon = new ItemStack(ItemInit.NECRONOMICON.get(),1);
            Item currentItem = event.getStack().getItem();
            if(currentItem == necronomicon.getItem()) {
                ModMessages.sendToServer(new InsightS2CPacket());

            }
        }
    }

}
