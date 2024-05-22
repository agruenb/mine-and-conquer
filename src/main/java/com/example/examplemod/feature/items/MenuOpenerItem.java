package com.example.examplemod.feature.items;

import com.example.examplemod.feature.menu.GameMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MenuOpenerItem extends Item {
    public MenuOpenerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            player.openMenu(new SimpleMenuProvider((id, inv, p) -> new GameMenu(id, inv), Component.literal("Sample Text")));
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
