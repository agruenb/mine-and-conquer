package com.example.examplemod.network;

import com.example.examplemod.MineAndConquer;
import com.example.examplemod.feature.entities.BoomIllager;
import com.example.examplemod.feature.entities.EntityRegistry;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import org.slf4j.Logger;

public class ServerPayloadHandler implements IPayloadHandler<ModRecord> {

    @Override
    public void handle(ModRecord payload, IPayloadContext context) {
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("Server message"));
        Player player = context.player();
        BoomIllager boomIllager = new BoomIllager(EntityRegistry.BOOM_ILLAGER.get(), player.level()); // Adjust EntityType to match your BoomIllager
        boomIllager.setPos(player.position().x, player.position().y, player.position().z);
        MineAndConquer.LOGGER.debug(String.format("%f, %f, %f",player.position().x, player.position().y, player.position().z));
        player.level().addFreshEntity(boomIllager);
    }
}
