package net.mcdev.mcadditions.recipe.custom;

import com.google.gson.JsonObject;
import net.mcdev.mcadditions.MCAdditions;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class StringWoolRecipeHardcoded implements CraftingRecipe {
	private final Identifier id;

	public StringWoolRecipeHardcoded(Identifier id) {
		this.id = id;
		MCAdditions.LOGGER.info("StringWoolRecipeHardcoded instanciada: {}", id);
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
				shears.setDamage(shears.getDamage() + 1);
				if (shears.getDamage() < shears.getMaxDamage()) {
					remainder.set(i, shears);
				}
			}
		}

		return remainder;
	}

	@Override
	public DefaultedList<Ingredient> getIngredients() {
		DefaultedList<Ingredient> list = DefaultedList.of();
		list.add(Ingredient.fromTag(ItemTags.WOOL));
		list.add(Ingredient.ofItems(Items.SHEARS));
		return list;
	}

	@Override
	public boolean fits(int width, int height) {
		return width * height >= 2;
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
	public CraftingRecipeCategory getCategory() {
		return CraftingRecipeCategory.MISC;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return net.mcdev.mcadditions.recipe.MCARecipes.STRING_WOOL_RECIPE_SERIALIZER;
	}

	// getType() NÃO é sobrescrito -> usa o default da interface CraftingRecipe -> RecipeType.CRAFTING

	public static class Serializer implements RecipeSerializer<StringWoolRecipeHardcoded> {
		@Override
		public StringWoolRecipeHardcoded read(Identifier id, JsonObject json) {
			return new StringWoolRecipeHardcoded(id);
		}

		@Override
		public StringWoolRecipeHardcoded read(Identifier id, PacketByteBuf buf) {
			return new StringWoolRecipeHardcoded(id);
		}

		@Override
		public void write(PacketByteBuf buf, StringWoolRecipeHardcoded recipe) {

		}
	}
}