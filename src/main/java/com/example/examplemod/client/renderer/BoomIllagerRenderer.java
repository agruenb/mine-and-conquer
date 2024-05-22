package com.example.examplemod.client.renderer;

import com.example.examplemod.feature.entities.BoomIllager;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.AbstractIllager;

import static com.example.examplemod.MineAndConquer.MODID;

public class BoomIllagerRenderer extends MobRenderer<BoomIllager, IllagerModel<BoomIllager>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/entity/boom_illager.png");
    public BoomIllagerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new IllagerModel<>(pContext.bakeLayer(ModelLayers.PILLAGER)), 1.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(BoomIllager pEntity) {
        return TEXTURE;
    }

}
