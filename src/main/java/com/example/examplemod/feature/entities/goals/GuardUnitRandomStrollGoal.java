package com.example.examplemod.feature.entities.goals;

import com.example.examplemod.feature.entities.GuardUnit;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

public class GuardUnitRandomStrollGoal<T extends PathfinderMob & GuardUnit> extends RandomStrollGoal {

    protected final T guardMob;

    int strollRadius;
    public GuardUnitRandomStrollGoal(T pMob, double pSpeedModifier, int pStrollRadius) {
        super(pMob, pSpeedModifier);
        this.strollRadius = pStrollRadius;
        this.guardMob = pMob;
    }

    @Override
    protected Vec3 getPosition() {
        return DefaultRandomPos.getPosTowards(this.mob, this.strollRadius, 2, this.guardMob.getGuardPosition(), 0);
    }
}
