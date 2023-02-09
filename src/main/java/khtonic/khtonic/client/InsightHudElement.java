package khtonic.khtonic.client;

import com.mojang.blaze3d.systems.RenderSystem;
import khtonic.khtonic.Khtonic;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class InsightHudElement {
    private static final ResourceLocation INSIGHT = new ResourceLocation(Khtonic.MODID,
            "textures/insight/insight.png");
    public static final IGuiOverlay INSIGHT_HUD = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
       int x = screenWidth/2;
       int y = screenHeight;

       RenderSystem.setShader(GameRenderer::getPositionTexShader);
       RenderSystem.setShaderColor(1.0F, 1.0F,1.0F, 1.0F);
       RenderSystem.setShaderTexture(0, INSIGHT);
        GuiComponent.blit(poseStack,x-94, y-54, 0,0,12,12,12,12);
    });
}
