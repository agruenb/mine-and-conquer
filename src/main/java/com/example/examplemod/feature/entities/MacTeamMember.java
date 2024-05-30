package com.example.examplemod.feature.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class MacTeamMember extends PathfinderMob {

    private int currentMacTeam = 0;

    public MacTeamMember(EntityType<? extends PathfinderMob> type, Level pLevel) {
        super(type, pLevel);
    }

    protected boolean shouldAttack(LivingEntity entity) {
        if (entity instanceof MacTeamMember) {
            return ((MacTeamMember) entity).getMacTeam() != this.getMacTeam();
        }
        return false; // Attack entities that are not TeamMembers
    }

    @Override
    public void checkDespawn() {
        // Prevents the entity from despawning
    }

    public int getMacTeam() {
        return this.currentMacTeam;
    }
    public void setMacTeam(int team) {
        this.currentMacTeam = team;
    }
}
