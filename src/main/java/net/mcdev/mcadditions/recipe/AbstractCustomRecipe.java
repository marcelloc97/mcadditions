package net.mcdev.mcadditions.recipe;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;


public class AbstractCustomRecipe implements CraftingRecipe {
	protected final Identifier id;
	protected final DefaultedList<Ingredient> ingredients;
	protected final ItemStack output;

	protected AbstractCustomRecipe(Identifier id, DefaultedList<Ingredient> ingredients,
	                               ItemStack output) {
		this.id = id;
		this.ingredients = ingredients;
		this.output = output;
	}

	@Override
	public boolean matches(RecipeInputInventory inventory, World world) {
		RecipeMatcher matcher = new RecipeMatcher();
		int nonEmptyCount = 0;

		for (int i = 0; i < inventory.size(); i++) {
			ItemStack stack = inventory.getStack(i);
			if (!stack.isEmpty()) {
				nonEmptyCount++;
				matcher.addInput(stack);
			}
		}

		return nonEmptyCount == ingredients.size() && matcher.match(this, null);
	}

//	private List<ItemStack> getInputStacks(CraftingInventory craftingInventory) {
//		List<ItemStack> stacks = new ArrayList<>();
//
//		for (int i = 0; i < craftingInventory.size(); i++) {
//			ItemStack stack = craftingInventory.getStack(i);
//			if (!stack.isEmpty()) stacks.add(stack);
//		}
//
//		return stacks;
//	}


	@Override
	public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
		return output.copy();
	}

	@Override
	public boolean fits(int width, int height) {
		return width * height >= ingredients.size();
	}

	@Override
	public ItemStack getOutput(DynamicRegistryManager registryManager) {
		return output;
	}

	@Override
	public Identifier getId() {
		return id;
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
	public DefaultedList<ItemStack> getRemainder(RecipeInputInventory inventory) {
		return null;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return null;
	}
}
