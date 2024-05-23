package com.example.examplemod.feature.entities;

import com.example.examplemod.feature.entities.projectiles.ExplosiveArrow;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.example.examplemod.MineAndConquer.MODID;

public class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<BoomIllager>> BOOM_ILLAGER = ENTITIES.register("boom_illager",
            () -> EntityType.Builder.<BoomIllager>of(BoomIllager::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f)
                    .build(new ResourceLocation(MODID, "boom_illager").toString())
            );

    public static final DeferredHolder<EntityType<?>, EntityType<ShooterGuard>> SHOOTER_GUARD = ENTITIES.register("shooter_guard",
            () -> EntityType.Builder.<ShooterGuard>of(ShooterGuard::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f)
                    .build(new ResourceLocation(MODID, "shooter_guard").toString())
    );

    public static final DeferredHolder<EntityType<?>, EntityType<ExplosiveArrow>> EXPLOSIVE_ARROW = ENTITIES.register("explosive_arrow",
            () -> EntityType.Builder.<ExplosiveArrow>of(ExplosiveArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .build(new ResourceLocation(MODID, "explosive_arrow").toString()));

}
