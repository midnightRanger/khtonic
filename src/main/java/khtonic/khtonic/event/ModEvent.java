package khtonic.khtonic.event;

import khtonic.khtonic.Khtonic;
import khtonic.khtonic.insight.Insight;
import khtonic.khtonic.insight.InsightProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Khtonic.MODID)
public class ModEvent {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(InsightProvider.PLAYER_INSIGHT).isPresent()) {
                event.addCapability(new ResourceLocation(Khtonic.MODID, "properties"), new InsightProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(InsightProvider.PLAYER_INSIGHT).ifPresent(oldStore -> {
                event.getOriginal().getCapability(InsightProvider.PLAYER_INSIGHT).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(Insight.class);
    }
}
