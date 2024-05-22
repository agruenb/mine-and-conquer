package com.example.examplemod.feature.items;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.example.examplemod.MineAndConquer.MODID;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> MENU_OPENER_ITEM = ITEMS.register("menu_opener_item",
            () -> new MenuOpenerItem(new Item.Properties())
            );
}
