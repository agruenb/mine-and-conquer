package com.example.examplemod.client.renderer;

import com.example.examplemod.feature.entities.AbstractBasicUnit;
import com.example.examplemod.feature.entities.BoomIllager;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import static com.example.examplemod.MineAndConquer.MODID;

public class BoomIllagerRenderer extends BasicUnitRenderer {

    public static final ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/entity/blueguy.png");
    public BoomIllagerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractBasicUnit pEntity) {
        return TEXTURE;
    }

}
