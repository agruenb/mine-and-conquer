package com.example.examplemod.feature.menu;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.example.examplemod.MineAndConquer.MODID;

public class MenuRegistry {

    public static final DeferredRegister<MenuType<?>> MENUES = DeferredRegister.create(BuiltInRegistries.MENU, MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<GameMenu>> GAME_MENU = MENUES.register("game_menu",
            () -> new MenuType(GameMenu::new, FeatureFlags.DEFAULT_FLAGS));

}
