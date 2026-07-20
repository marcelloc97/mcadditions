package net.mcdev.mcadditions.recipe;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class ShearsWoolRecipe implements CraftingRecipe {
    private final Identifier id;
    private final DefaultedList<Ingredient> ingredients;

    public ShearsWoolRecipe(Identifier id) {
        this.id = id;
        this.ingredients = DefaultedList.of();
        this.ingredients.add(Ingredient.fromTag(ItemTags.WOOL));
        this.ingredients.add(Ingredient.ofItems(Items.SHEARS));
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
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        return new ItemStack(Items.STRING, 4);
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(RecipeInputInventory inventory) {
        DefaultedList<ItemStack> remainder = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);

            if (stack.isOf(Items.SHEARS)) {
                ItemStack shears = stack.copy();
                shears.setCount(1);
                // shears.damage(1, player, () -> {}); // use this if you have a mod that subscribe the craft function and want to get the unbreaking enchantment
                shears.setDamage(shears.getDamage() + 1);

                if (shears.getDamage() < shears.getMaxDamage())
                    remainder.set(i, shears); // go back to the grid with +1 damage

                // else if broken, then get empty
            }
        }

        return remainder;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return CraftingRecipeCategory.MISC;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= ingredients.size();
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return new ItemStack(Items.STRING, 4);
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MCARecipes.SHEARS_WOOL_RECIPE_SERIALIZER;
    }
}
