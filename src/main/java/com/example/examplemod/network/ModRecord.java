package com.example.examplemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import static com.example.examplemod.MineAndConquer.MODID;

public record ModRecord(String name, int age) implements CustomPacketPayload {

    public static final Type<ModRecord> TYPE = new Type<>(new ResourceLocation(MODID, "mod_record"));

    // Each pair of elements defines the stream codec of the element to encode/decode and the getter for the element to encode
    // 'name' will be encoded and decoded as a string
    // 'age' will be encoded and decoded as an integer
    // The final parameter takes in the previous parameters in the order they are provided to construct the payload object
    public static final StreamCodec<RegistryFriendlyByteBuf, ModRecord> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            ModRecord::name,
            ByteBufCodecs.VAR_INT,
            ModRecord::age,
            ModRecord::new
    );

    @Override
    public Type<ModRecord> type() {
        return TYPE;
    }
}