package khtonic.khtonic.Entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import khtonic.khtonic.Entity.monsters.SoulEaterEntity;
import khtonic.khtonic.Khtonic;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SoulEaterRenderer extends GeoEntityRenderer<SoulEaterEntity> {

    public SoulEaterRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SoulEaterModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(SoulEaterEntity instance) {
        return new ResourceLocation(Khtonic.MODID, "textures/entity/souleater/souleater.png");
    }

    @Override
    public RenderType getRenderType (SoulEaterEntity animatable, float partialTicks, PoseStack stack,
                                        MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     ResourceLocation textureLocation) {
        stack.scale(0.8F, 0.8F, 0.8F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }


}
