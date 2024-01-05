package khtonic.khtonic.init;

import khtonic.khtonic.Entity.CosmicCreatureEntity;
import khtonic.khtonic.Entity.monsters.SoulEaterEntity;
import khtonic.khtonic.Khtonic;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.text.DefaultFormatterFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Khtonic.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_DEFERRED = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Khtonic.MODID);
    public static final DeferredRegister<Item> ITEM_DEFERRED = DeferredRegister.create(ForgeRegistries.ITEMS, Khtonic.MODID);
    public static final Collection<RegistryObject<Item>> SPAWN_EGGS = new ArrayList<>();
    public static final RegistryObject<EntityType<CosmicCreatureEntity>> COSMIC_CREATURE = registerCreature("cosmic_creature", () -> CosmicCreatureEntity::new, 0.4F, 0.95F, 0x000000, 0xFFFFFF);

    public static final RegistryObject<EntityType<SoulEaterEntity>> SOUL_EATER = registerMonster("soul_eater", () -> SoulEaterEntity::new, 0.4F, 0.95F, 0x000000, 0xFFFFFF);

    private static <T extends Animal> RegistryObject<EntityType<T>> registerCreature(String name, Supplier<EntityType.EntityFactory<T>> factory, float width, float height, int eggPrimary, int eggSecondary) {
        ResourceLocation location = new ResourceLocation(Khtonic.MODID, name);
        RegistryObject<EntityType<T>> entityType = ENTITY_DEFERRED.register(name, () -> EntityType.Builder.of(factory.get(), MobCategory.CREATURE).sized(width, height).setTrackingRange(64).setUpdateInterval(1).build(location.toString()));

        RegistryObject<Item> spawnEgg = ITEM_DEFERRED.register(name + "_spawn_egg", () -> new ForgeSpawnEggItem(entityType, eggPrimary, eggSecondary, new Item.Properties()));
        SPAWN_EGGS.add(spawnEgg);
        return entityType;
    }

    private static <T extends Monster> RegistryObject<EntityType<T>> registerMonster(String name, Supplier<EntityType.EntityFactory<T>> factory, float width, float height, int eggPrimary, int eggSecondary) {
        ResourceLocation location = new ResourceLocation(Khtonic.MODID, name);
        RegistryObject<EntityType<T>> entityType = ENTITY_DEFERRED.register(name, () -> EntityType.Builder.of(factory.get(), MobCategory.CREATURE).sized(width, height).setTrackingRange(64).setUpdateInterval(1).build(location.toString()));

        RegistryObject<Item> spawnEgg = ITEM_DEFERRED.register(name + "_spawn_egg", () -> new ForgeSpawnEggItem(entityType, eggPrimary, eggSecondary, new Item.Properties()));
        SPAWN_EGGS.add(spawnEgg);
        return entityType;
    }
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(COSMIC_CREATURE.get(), CosmicCreatureEntity.createAttributes().build());
        event.put(SOUL_EATER.get(), SoulEaterEntity.createMobAttributes().build());
    }


}
