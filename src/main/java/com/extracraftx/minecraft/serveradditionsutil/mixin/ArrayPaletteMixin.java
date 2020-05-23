package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientBlockStateProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.chunk.ArrayPalette;

@Mixin(ArrayPalette.class)
public abstract class ArrayPaletteMixin{

    @ModifyArg(method = "toPacket", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketByteBuf;writeVarInt(I)Lnet/minecraft/network/PacketByteBuf;", ordinal=1), index=0)
    private int modifyId(int original){
        BlockState state = Block.getStateFromRawId(original);
        Block block = state.getBlock();
        if(block instanceof ClientBlockStateProvider)
            return Block.getRawIdFromState(((ClientBlockStateProvider)block).getClientBlockState(state));
        return original;
    }

}