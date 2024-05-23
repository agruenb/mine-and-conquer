package com.example.examplemod.feature.entities;

import com.example.examplemod.feature.entities.goals.GuardLocationGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ShooterGuard extends AbstractProjectileShooterUnit {

    public ShooterGuard(EntityType<? extends AbstractProjectileShooterUnit> type, Level pLevel) {
        super(type, pLevel);
        this.level = pLevel;
        setMacTeam(1);
    }

    public ShooterGuard(Level pLevel, double x, double y, double z) {
        this(EntityRegistry.SHOOTER_GUARD.get(), pLevel);
        setPos(x, y, z);
    }

    public ShooterGuard(Level pLevel, BlockPos position) {
        this(EntityRegistry.SHOOTER_GUARD.get(), pLevel);
        setPos(position.getX(), position.getY(), position.getZ());
    }

    public static AttributeSupplier.Builder createAttributes(){
        return AbstractProjectileShooterUnit.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.3D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1D, 40, 15.0F));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new GuardLocationGoal(this, new Vec3(0,-61,0)));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        // Add custom target goal to attack all living entities except BoomIllager
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, this::shouldAttack));
    }
}
