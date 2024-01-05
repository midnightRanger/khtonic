package khtonic.khtonic.Entity.client;

import khtonic.khtonic.Entity.monsters.SoulEaterEntity;
import khtonic.khtonic.Khtonic;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SoulEaterModel extends AnimatedGeoModel<SoulEaterEntity> {
    @Override
    public ResourceLocation getModelResource(SoulEaterEntity object) {
        return new ResourceLocation(Khtonic.MODID, "geo/souleater.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SoulEaterEntity object) {
        return new ResourceLocation(Khtonic.MODID, "textures/entity/souleater/souleater.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SoulEaterEntity animatable) {
        return new ResourceLocation(Khtonic.MODID, "animations/souleater.animation.json");
    }
}
