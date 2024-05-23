package com.example.examplemod.feature.menu;

import com.example.examplemod.feature.entities.BoomIllager;
import com.example.examplemod.feature.entities.EntityRegistry;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import static com.example.examplemod.MineAndConquer.MODID;

public class GameMenuScreen extends AbstractContainerScreen<GameMenu> {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(MODID, "assets/examplemod/textures/entity/boom_illager.png");

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
        // Handle button 1 action
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("Button 1 Pressed"));
        // Handle button 2 action
        Minecraft mc = Minecraft.getInstance();

        if (mc.level == null || mc.player == null) {
            mc.player.sendSystemMessage(Component.literal("Failed to spawn BoomIllager: Invalid player or world context."));
            return;
        }
        Level world = mc.player.level();
        Vec3 position = mc.player.position();

        if (world.isClientSide) {
            mc.player.sendSystemMessage(Component.literal("Failed to spawn BoomIllager: World is clientside"));
            return;
        }
        // Create the BoomIllager entity
        BoomIllager boomIllager = new BoomIllager(EntityRegistry.BOOM_ILLAGER.get(), world); // Adjust EntityType to match your BoomIllager
        boomIllager.setMacTeam(1);
        // Set the position of the BoomIllager
        boomIllager.setPos(position.x, position.y, position.z);

        // Spawn the BoomIllager in the world
        world.addFreshEntity(boomIllager);
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
