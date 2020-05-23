package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(InventoryS2CPacket.class)
public abstract class InventoryPacketMixin{

    @Shadow
    private List<ItemStack> contents;

    @Inject(method = "<init>(ILnet/minecraft/util/collection/DefaultedList;)V", at= @At("RETURN"))
    private void onInit(CallbackInfo info){
        for(int i = 0; i < contents.size(); i++){
            ItemStack original = contents.get(i);
            Item item = original.getItem();
            if(item instanceof ClientItemStackProvider){
                contents.set(i, ((ClientItemStackProvider)item).getClientItemStack(original));
            }
        }
    }

}