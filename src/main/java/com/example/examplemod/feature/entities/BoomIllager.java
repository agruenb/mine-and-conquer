package com.example.examplemod.feature.entities;

import com.example.examplemod.feature.entities.projectiles.ExplosiveArrow;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class BoomIllager extends AbstractIllager implements RangedAttackMob {

    Level level;

    public BoomIllager(EntityType<? extends AbstractIllager> type, Level pLevel) {
        super(type, pLevel);
        this.level = pLevel;
    }

    public BoomIllager(Level pLevel, double x, double y, double z) {
        this(EntityRegistry.BOOM_ILLAGER.get(), pLevel);
        this.level = pLevel;
        setPos(x, y, z);
    }

    public BoomIllager(Level pLevel, BlockPos position) {
        this(EntityRegistry.BOOM_ILLAGER.get(), pLevel);
        this.level = pLevel;
        setPos(position.getX(), position.getY(), position.getZ());
    }

    public static AttributeSupplier.Builder createAttributes(){
        return AbstractIllager.createMonsterAttributes();
    }

    public static boolean canSpawn(EntityType<BoomIllager> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random){
        return AbstractIllager.checkMonsterSpawnRules(entityType, level, spawnType, position, random);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.0D, 20, 30.0F));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        //this.targetSelector.removeGoal(goal -> goal instanceof NearestAttackableTargetGoal);
        // Add custom target goal to attack all living entities except BoomIllager
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, this::shouldAttack));
    }

    private boolean shouldAttack(LivingEntity entity) {
        // Exclude BoomIllager entities from being targeted
        return !(entity instanceof BoomIllager);
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        ItemStack explosiveArrowStack = new ItemStack(Items.ARROW);
        ExplosiveArrow explosiveArrow = new ExplosiveArrow(this.level, this, explosiveArrowStack);
        double d0 = target.getX() - this.getX();
        double d1 = target.getBoundingBox().minY + (double)(target.getBoundingBox().getYsize() / 3.0F) - explosiveArrow.getY();
        double d2 = target.getZ() - this.getZ();
        double d3 = (double) Math.sqrt(d0 * d0 + d2 * d2);
        explosiveArrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.level.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(explosiveArrow);
    }

    @Override
    public void applyRaidBuffs(int pWave, boolean pUnusedFalse) {

    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.SKELETON_SHOOT;
    }

    // Additional methods and overrides as needed
}
