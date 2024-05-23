package com.example.examplemod.feature.blocks;

import com.example.examplemod.feature.entities.EntityRegistry;
import com.example.examplemod.feature.entities.ShooterGuard;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockTypes;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.obj.ObjMaterialLibrary;

public class ShooterGuardBlock extends Block {

    public ShooterGuardBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(3.5F)
                .sound(SoundType.METAL));
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (!level.isClientSide && level instanceof ServerLevel) {
            ShooterGuard guard = EntityRegistry.SHOOTER_GUARD.get().create((ServerLevel) level);
            if (guard != null) {
                guard.moveTo(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, guard.getYRot(), guard.getXRot());
                //guard.guardLocation = new Vec3(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                level.addFreshEntity(guard);
            }
        }
        super.setPlacedBy(level, pos, state, placer, stack);
    }
}
