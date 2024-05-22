package com.example.examplemod.feature.menu;

import com.llamalad7.mixinextras.lib.apache.commons.ObjectUtils;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class GameMenu extends AbstractContainerMenu {


    protected GameMenu(@Nullable MenuType<?> pMenuType, int pContainerId) {
        super(pMenuType, pContainerId);
    }

    public GameMenu(int containerId, Inventory playerInv) {
        super(MenuRegistry.GAME_MENU.get(), containerId);
        // ...
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
