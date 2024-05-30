package com.example.examplemod.feature.menu;

import com.example.examplemod.MineAndConquer;
import com.example.examplemod.feature.entities.BoomIllager;
import com.example.examplemod.feature.entities.EntityRegistry;
import com.example.examplemod.network.ModRecord;
import com.llamalad7.mixinextras.utils.MixinExtrasLogger;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import org.slf4j.Logger;

import static com.example.examplemod.MineAndConquer.MODID;

public class GameMenuScreen extends AbstractContainerScreen<GameMenu> {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(MODID, "textures/entity/blueguy.png");

    public GameMenuScreen(GameMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);
    }

    @Override
    protected void init() {
        super.init();
        // Add buttons to the screen
        this.addRenderableWidget(
                new Button.Builder(Component.literal("Button 1"), button -> onButton1Pressed())
                .pos(this.leftPos + 50, this.topPos + 50)
                .size(100, 20)
                .build());
        this.addRenderableWidget(
                new Button.Builder(Component.literal("Button 2"), button -> onButton2Pressed())
                        .pos(this.leftPos + 50, this.topPos + 150)
                        .size(100, 20)
                        .build());
    }

    private void onButton1Pressed() {
        Minecraft mc = Minecraft.getInstance();

        if (mc.level == null || mc.player == null) {
            mc.player.sendSystemMessage(Component.literal("Failed to spawn BoomIllager: Invalid player or world context."));
            return;
        }
        Level world = mc.player.level();

        if (world.isClientSide) {
            ModRecord msg = new ModRecord("Test", 45);
            PacketDistributor.sendToServer(msg);
        }
    }

    private void onButton2Pressed() {
        // Handle button 2 action
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("Button 2 Pressed"));
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        pGuiGraphics.blit(BACKGROUND_TEXTURE, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);

        /*
         * This method is added by the container screen to render
         * the tooltip of the hovered slot.
         */
        this.renderTooltip(graphics, mouseX, mouseY);
    }
}
