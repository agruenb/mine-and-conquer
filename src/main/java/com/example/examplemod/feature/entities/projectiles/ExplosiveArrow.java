package com.example.examplemod.feature.entities.projectiles;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import static net.minecraft.world.level.Level.*;

public class ExplosiveArrow extends Arrow {

    private Level level;

    public ExplosiveArrow(EntityType<? extends ExplosiveArrow> type, Level world) {
        super(type, world);
        this.level = world;
    }

    public ExplosiveArrow(Level world, LivingEntity shooter, ItemStack pickupItemStack) {
        super(world, shooter, pickupItemStack);
        this.level = world;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level.isClientSide) {
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.0F, false, ExplosionInteraction.BLOCK);
            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected void onHit(HitResult pResult){
        super.onHit(pResult);
        if (!this.level.isClientSide) {
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.0F, false, ExplosionInteraction.BLOCK);
            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected void doPostHurtEffects(LivingEntity living) {
        super.doPostHurtEffects(living);
        living.setArrowCount(living.getArrowCount() - 1);
    }

    @Override
    protected void tickDespawn() {
        if (this.pickup == Pickup.DISALLOWED) {
            this.remove(RemovalReason.DISCARDED);
        }
    }
}