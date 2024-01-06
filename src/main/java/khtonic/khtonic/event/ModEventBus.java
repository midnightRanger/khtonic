package khtonic.khtonic.event;

import khtonic.khtonic.Entity.monsters.SoulEaterEntity;
import khtonic.khtonic.Khtonic;
import khtonic.khtonic.init.EntityInit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Khtonic.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBus {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(EntityInit.SOUL_EATER.get(), SoulEaterEntity.setAttributes());

        //TODO Перенести Cosmic Creature сюда же
    }

}
