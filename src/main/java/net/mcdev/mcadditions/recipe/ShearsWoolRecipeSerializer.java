package net.mcdev.mcadditions.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public class ShearsWoolRecipeSerializer implements RecipeSerializer<ShearsWoolRecipe> {
    @Override
    public ShearsWoolRecipe read(Identifier id, JsonObject json) {
        return new ShearsWoolRecipe(id);
    }

    @Override
    public ShearsWoolRecipe read(Identifier id, PacketByteBuf buf) {
        return new ShearsWoolRecipe(id);
    }

    @Override
    public void write(PacketByteBuf buf, ShearsWoolRecipe recipe) {}
}
