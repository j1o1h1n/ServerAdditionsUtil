package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientBlockStateProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.chunk.BiMapPalette;

@Mixin(BiMapPalette.class)
public abstract class BiMapPaletteMixin{

    @ModifyArg(method = "toPacket", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/PacketByteBuf;writeVarInt(I)Lnet/minecraft/util/PacketByteBuf;", ordinal=1), index=0)
    private int modifyId(int original){
        BlockState state = Block.getStateFromRawId(original);
        Block block = state.getBlock();
        if(block instanceof ClientBlockStateProvider)
            return Block.getRawIdFromState(((ClientBlockStateProvider)block).getClientBlockState(state));
        return original;
    }

}