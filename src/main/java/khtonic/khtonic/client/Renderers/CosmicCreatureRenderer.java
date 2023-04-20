package khtonic.khtonic.client.Renderers;

import khtonic.khtonic.Entity.CosmicCreatureEntity;
import khtonic.khtonic.Khtonic;
import khtonic.khtonic.client.Models.CosmicCreatureModel;
import khtonic.khtonic.event.ClientEvent;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class CosmicCreatureRenderer extends MobRenderer<CosmicCreatureEntity, CosmicCreatureModel<CosmicCreatureEntity>> {
    public CosmicCreatureRenderer(EntityRendererProvider.Context context) {
        super(context, new CosmicCreatureModel<>(context.bakeLayer(ClientEvent.COSMIC_CREATURE_LAYER)), 0.5F);
    }

    @Override
    @Nonnull
    public ResourceLocation getTextureLocation(@Nonnull CosmicCreatureEntity cosmicCreature) {
        return new ResourceLocation(Khtonic.MODID, "textures/entity/cosmic_creature/cosmic_creature.png");
    }
}
