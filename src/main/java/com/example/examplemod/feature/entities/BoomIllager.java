package com.example.examplemod.feature.entities;

import com.example.examplemod.feature.entities.goals.AttackLocationGoal;
import com.example.examplemod.feature.entities.projectiles.ExplosiveArrow;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.StrollToPoi;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BoomIllager extends AbstractProjectileShooterUnit {

    public BoomIllager(EntityType<? extends AbstractProjectileShooterUnit> type, Level pLevel) {
        super(type, pLevel);
        this.level = pLevel;
        equipArmor();
    }

    public BoomIllager(Level pLevel, double x, double y, double z) {
        this(EntityRegistry.BOOM_ILLAGER.get(), pLevel);
        setPos(x, y, z);
    }

    public BoomIllager(Level pLevel, BlockPos position) {
        this(EntityRegistry.BOOM_ILLAGER.get(), pLevel);
        setPos(position.getX(), position.getY(), position.getZ());
    }

    public static AttributeSupplier.Builder createAttributes(){
        return AbstractProjectileShooterUnit.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.3D);
    }

    public void equipArmor(){
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.LEATHER_LEGGINGS));
        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 0.5D, 30, 15.0F));
        this.goalSelector.addGoal(3, new AttackLocationGoal(this, new Vec3(0,0,0)));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        //this.targetSelector.removeGoal(goal -> goal instanceof NearestAttackableTargetGoal);
        // Add custom target goal to attack all living entities except BoomIllager
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, this::shouldAttack));
    }
}
