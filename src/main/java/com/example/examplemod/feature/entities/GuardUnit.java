package com.example.examplemod.feature.entities;

import net.minecraft.world.phys.Vec3;

public interface GuardUnit {

    public void setGuardPosition(Vec3 newPosition);

    public Vec3 getGuardPosition();
}
