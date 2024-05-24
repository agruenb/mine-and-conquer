package com.example.examplemod.client.renderer;

import com.example.examplemod.feature.entities.AbstractBasicUnit;
import com.example.examplemod.feature.entities.ShooterGuard;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

import static com.example.examplemod.MineAndConquer.MODID;

public class BasicUnitRenderer extends HumanoidMobRenderer<AbstractBasicUnit, PlayerModel<AbstractBasicUnit>> {

    public BasicUnitRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PlayerModel<>(pContext.bakeLayer(ModelLayers.PLAYER), true), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this,
                new HumanoidModel<>(pContext.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new HumanoidModel<>(pContext.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)),
                pContext.getModelManager()
        ));
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractBasicUnit pEntity) {
        return null;
    }

}
