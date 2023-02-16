package khtonic.khtonic.event;

import khtonic.khtonic.Khtonic;
import khtonic.khtonic.client.InsightHudElement;
import khtonic.khtonic.init.ItemInit;
import khtonic.khtonic.insight.Insight;
import khtonic.khtonic.insight.InsightProvider;
import khtonic.khtonic.networking.ModMessages;
import khtonic.khtonic.networking.packet.InsightS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
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

    @Mod.EventBusSubscriber(modid = Khtonic.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("insight", InsightHudElement.INSIGHT_HUD);
        }
    }

}
