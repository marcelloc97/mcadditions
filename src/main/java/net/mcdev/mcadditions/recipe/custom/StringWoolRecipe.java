package net.mcdev.mcadditions.recipe.custom;

import net.mcdev.mcadditions.MCAdditions;
import net.mcdev.mcadditions.recipe.AbstractCustomRecipe;
import net.mcdev.mcadditions.recipe.MCARecipes;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
//import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class StringWoolRecipe extends AbstractCustomRecipe {
    public StringWoolRecipe(Identifier id, DefaultedList<Ingredient> ingredients, ItemStack output) {
        super(id, ingredients, output);

        MCAdditions.LOGGER.info("Shears Wool recipe");
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        boolean foundWool = false, foundShears = false;
        int nonEmpty = 0;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);

            if (stack.isEmpty()) continue;
            nonEmpty++;
            if (stack.isIn(ItemTags.WOOL)) foundWool = true;
            else if (stack.isOf(Items.SHEARS)) foundShears = true;
            else return false;
        }

        return foundWool && foundShears && nonEmpty == 2;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(RecipeInputInventory inventory) {
        MCAdditions.LOGGER.info("getRemainder chamado!"); // <-- log temporário
        DefaultedList<ItemStack> remainder = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);

            if (stack.isOf(Items.SHEARS)) {
                ItemStack shears = stack.copy();
                shears.setCount(1);
                shears.setDamage(shears.getDamage() + 1);

                if (shears.getDamage() < shears.getMaxDamage()) {
                    remainder.set(i, shears);
                }
            }

        }

        return remainder;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MCARecipes.STRING_WOOL_RECIPE_SERIALIZER;
    }

//    @Override
//    public RecipeType<?> getType() {
//        return MCARecipes.STRING_WOOL_RECIPE_TYPE;
//    }
}
