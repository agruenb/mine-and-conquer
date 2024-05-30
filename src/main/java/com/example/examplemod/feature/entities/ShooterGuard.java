package com.example.examplemod.feature.entities;

import com.example.examplemod.feature.entities.goals.GuardLocationGoal;
import com.example.examplemod.feature.entities.goals.GuardUnitRandomStrollGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ShooterGuard extends AbstractProjectileShooterUnit implements GuardUnit {

    public static final Vec3 defaultGuardPosition = new Vec3(0,0,0);
    public static final int TARGET_RANGE = 40;
    public static final int SHOOT_RANGE = 35;
    public static final int RELOAD_TIME = 40;

    private GuardLocationGoal guardGoalStore;

    public Vec3 guardPosition = defaultGuardPosition;

    public ShooterGuard(EntityType<? extends AbstractProjectileShooterUnit> type, Level pLevel) {
        super(type, pLevel);
        this.level = pLevel;
        setMacTeam(1);
        equipArmor();
    }

    public ShooterGuard(Level pLevel, double x, double y, double z) {
        this(EntityRegistry.SHOOTER_GUARD.get(), pLevel);
        setPos(x, y, z);
    }

    public ShooterGuard(Level pLevel, BlockPos position) {
        this(EntityRegistry.SHOOTER_GUARD.get(), pLevel);
        setPos(position.getX(), position.getY(), position.getZ());
    }

    public void equipArmor(){
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.GOLDEN_HELMET));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS));
        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
    }

    public static AttributeSupplier.Builder createAttributes(){
        return AbstractProjectileShooterUnit.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FOLLOW_RANGE, ShooterGuard.TARGET_RANGE);
    }

    public void setGuardPosition(Vec3 newPosition){
        this.guardPosition = newPosition;
        if(this.guardGoalStore != null){
            this.goalSelector.removeGoal(this.guardGoalStore);
        }
        this.guardGoalStore = new GuardLocationGoal(this, this.guardPosition);
        this.goalSelector.addGoal(2, this.guardGoalStore);
    }

    public Vec3 getGuardPosition(){
        return this.guardPosition;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1D, ShooterGuard.RELOAD_TIME, ShooterGuard.SHOOT_RANGE));
        //prio 2 guard goal
        this.goalSelector.addGoal(3, new GuardUnitRandomStrollGoal<ShooterGuard>(this, .6, 4));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        // Add custom target goal to attack all living entities except BoomIllager
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, this::shouldAttack));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Monster.class, true));
    }
}
