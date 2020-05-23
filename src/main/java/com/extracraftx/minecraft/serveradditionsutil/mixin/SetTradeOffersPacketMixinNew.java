package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientTradeOfferProvider;

import net.minecraft.network.packet.s2c.play.SetTradeOffersS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.village.TradeOffer;
import net.minecraft.village.TraderOfferList;

@Mixin(SetTradeOffersS2CPacket.class)
public abstract class SetTradeOffersPacketMixinNew{

    @Shadow
    private TraderOfferList recipes;

    @Inject(method = "<init>(ILnet/minecraft/village/TraderOfferList;IIZZ)V", at=@At("RETURN"))
    private void onInit(CallbackInfo info){
        if(this.recipes == null)
            return;
        TraderOfferList clientRecipes = new TraderOfferList();
        for(TradeOffer original : recipes){
            clientRecipes.add(((ClientTradeOfferProvider)original).getClientTradeOffer());
        }
        recipes = clientRecipes;
    }

}