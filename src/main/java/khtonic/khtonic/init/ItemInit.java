package khtonic.khtonic.init;

import khtonic.khtonic.Khtonic;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Khtonic.MODID);

    public static final RegistryObject<Item> NECRONOMICON = ITEMS.register("necronomicon",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> MOON_CREATURE_HEAD = ITEMS.register("moon_creature_head",
            ()-> new Item(new Item.Properties().tab(ModCreativeTab.instance)));

    public static class ModCreativeTab extends CreativeModeTab {
        private ModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(NECRONOMICON.get());
        }

        public static final ModCreativeTab instance = new ModCreativeTab(CreativeModeTab.TABS.length, "khtonic");

    }
}
