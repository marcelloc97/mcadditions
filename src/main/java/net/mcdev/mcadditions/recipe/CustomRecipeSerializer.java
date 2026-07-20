package net.mcdev.mcadditions.recipe;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;

public class CustomRecipeSerializer<T extends AbstractCustomRecipe> implements RecipeSerializer<T> {
	private final RecipeFactory<T> factory;

	public CustomRecipeSerializer(RecipeFactory<T> factory) {
		this.factory = factory;
	}

	@Override
	public T read(Identifier id, JsonObject json) {
		ItemStack output = readOutput(JsonHelper.getObject(json, "result"));

		var ingredientsJson = JsonHelper.getArray(json, "ingredients");
		DefaultedList<Ingredient> ingredients = DefaultedList.of();

		for (int i = 0; i < ingredientsJson.size(); i++) {
			ingredients.add(Ingredient.fromJson(ingredientsJson.get(i)));
		}

		return factory.create(id, ingredients, output);
	}

	@Override
	public T read(Identifier id, PacketByteBuf buf) {
		int size = buf.readVarInt();
		DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(size, Ingredient.EMPTY);

		for (int i = 0; i < size; i++) {
			ingredients.set(i, Ingredient.fromPacket(buf));
		}

		ItemStack output = buf.readItemStack();
		return factory.create(id, ingredients, output);
	}

	@Override
	public void write(PacketByteBuf buf, T recipe) {
		buf.writeVarInt(recipe.getIngredients().size());

		for (Ingredient ingredient : recipe.getIngredients()) {
			ingredient.write(buf);
		}

		buf.writeItemStack(recipe.getOutput(null));
	}

	private ItemStack readOutput(JsonObject json) {
		Identifier itemId = new Identifier(JsonHelper.getString(json, "item"));
		int count = JsonHelper.getInt(json, "count", 1);

		return new ItemStack(Registries.ITEM.get(itemId), count);
	}

	@FunctionalInterface
	public interface RecipeFactory<T extends AbstractCustomRecipe> {
		T create(Identifier id, DefaultedList<Ingredient> ingredients, ItemStack output);
	}
}
