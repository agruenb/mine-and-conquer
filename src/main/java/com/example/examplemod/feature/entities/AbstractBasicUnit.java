package com.example.examplemod.feature.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentTable;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class AbstractBasicUnit extends MacTeamMember {

    ItemStack ironHelmet = new ItemStack(Items.IRON_HELMET);

    public AbstractBasicUnit(EntityType<? extends PathfinderMob> type, Level pLevel) {
        super(type, pLevel);
    }

    public void wearHelmet() {
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_HELMET));
    }
}
