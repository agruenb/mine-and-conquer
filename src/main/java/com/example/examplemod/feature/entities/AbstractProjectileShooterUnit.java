package com.example.examplemod.feature.entities;

import com.example.examplemod.feature.entities.projectiles.ExplosiveArrow;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public abstract class AbstractProjectileShooterUnit extends AbstractBasicUnit implements RangedAttackMob {

    public Level level;
    int inaccuracy = 10;

    public AbstractProjectileShooterUnit(EntityType<? extends MacTeamMember> type, Level pLevel) {
        super(type, pLevel);
        this.level = pLevel;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        ItemStack explosiveArrowStack = new ItemStack(Items.ARROW);
        ExplosiveArrow explosiveArrow = new ExplosiveArrow(this.level, this, explosiveArrowStack);
        double d0 = target.getX() - this.getX();
        double d1 = target.getBoundingBox().minY + (double)(target.getBoundingBox().getYsize() / 3.0F) - explosiveArrow.getY();
        double d2 = target.getZ() - this.getZ();
        double d3 = (double) Math.sqrt(d0 * d0 + d2 * d2);
        explosiveArrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(this.inaccuracy));
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(explosiveArrow);
    }
}
