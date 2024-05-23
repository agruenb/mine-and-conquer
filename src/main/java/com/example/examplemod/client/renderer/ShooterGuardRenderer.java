package com.example.examplemod.client.renderer;

import com.example.examplemod.feature.entities.ShooterGuard;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import static com.example.examplemod.MineAndConquer.MODID;

public class ShooterGuardRenderer extends MobRenderer<ShooterGuard, PlayerModel<ShooterGuard>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/entity/flowercreeper.png");
    public ShooterGuardRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PlayerModel<>(pContext.bakeLayer(ModelLayers.PLAYER), true), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(ShooterGuard pEntity) {
        return TEXTURE;
    }

}
