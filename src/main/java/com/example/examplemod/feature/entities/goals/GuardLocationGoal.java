package com.example.examplemod.feature.entities.goals;

import com.example.examplemod.feature.entities.AbstractProjectileShooterUnit;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class GuardLocationGoal extends Goal {
    private final AbstractProjectileShooterUnit mob;
    private final Vec3 targetPosition;

    protected double wantedX;
    protected double wantedY;
    protected double wantedZ;

    public GuardLocationGoal(AbstractProjectileShooterUnit mob, Vec3 targetPosition) {
        this.mob = mob;
        this.targetPosition = targetPosition;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Nullable
    protected Vec3 getPosition() {
        return DefaultRandomPos.getPosTowards(this.mob, 2, 1, this.targetPosition, 0);//amplifier causes them to move around alot more
    }

    @Override
    public boolean canUse() {
        Vec3 vec3 = this.getPosition();
        if (vec3 == null) {
            return false;
        } else {
            this.wantedX = vec3.x;
            this.wantedY = vec3.y;
            this.wantedZ = vec3.z;
            return true;
        }
    }

    @Override
    public boolean canContinueToUse() {
        // The goal can continue if the mob has not reached the target position
        return !this.mob.getNavigation().isDone();
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, 1.0);
    }

    @Override
    public void stop() {
        this.mob.getNavigation().stop();
        super.stop();
    }
}
