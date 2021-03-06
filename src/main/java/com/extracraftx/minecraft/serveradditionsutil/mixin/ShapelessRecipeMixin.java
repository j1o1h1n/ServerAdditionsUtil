package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;
import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientRecipeProvider;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;

@Mixin(ShapelessRecipe.class)
public abstract class ShapelessRecipeMixin implements ClientRecipeProvider<ShapelessRecipe>{
    @Shadow @Final
    private Identifier id;
    @Shadow @Final
    private String group;
    @Shadow @Final
    private ItemStack output;
    @Shadow @Final
    private DefaultedList<Ingredient> input;

    @Override
    public ShapelessRecipe getClientRecipe() {
        ItemStack output = this.output;
        Item outputItem = output.getItem();
        if(outputItem instanceof ClientItemStackProvider){
            output = ((ClientItemStackProvider)outputItem).getClientItemStack(output);
        }
        return new ShapelessRecipe(id, group, output, input);
    }
}