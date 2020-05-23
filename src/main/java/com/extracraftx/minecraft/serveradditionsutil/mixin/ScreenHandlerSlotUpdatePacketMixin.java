package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenHandlerSlotUpdateS2CPacket.class)
public abstract class ScreenHandlerSlotUpdatePacketMixin {

    @Shadow
    private ItemStack stack;

    @Inject(method = "<init>(IILnet/minecraft/item/ItemStack;)V", at = @At("RETURN"))
    private void onInit(CallbackInfo info){
        Item item = this.stack.getItem();
        if(item instanceof ClientItemStackProvider){
            this.stack = ((ClientItemStackProvider)item).getClientItemStack(this.stack);
        }
    }

}