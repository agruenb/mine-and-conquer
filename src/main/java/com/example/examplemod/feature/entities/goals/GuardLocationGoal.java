package com.example.examplemod.feature.entities.goals;

import com.example.examplemod.feature.entities.AbstractProjectileShooterUnit;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class GuardLocationGoal extends Goal {
    private final Mob mob;
    private final Vec3 targetPosition;

    public GuardLocationGoal(Mob mob, Vec3 targetPosition) {
        this.mob = mob;
        this.targetPosition = targetPosition;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        // The goal can start if the mob is not already at the target position
        return !mob.blockPosition().equals(targetPosition);
    }

    @Override
    public boolean canContinueToUse() {
        // The goal can continue if the mob has not reached the target position
        return !mob.blockPosition().equals(targetPosition);
    }

    @Override
    public void start() {
        // Move the mob to the target position
        mob.getNavigation().moveTo(targetPosition.x, targetPosition.y, targetPosition.z, 1.0);
    }
}
