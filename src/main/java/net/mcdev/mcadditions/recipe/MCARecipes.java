package net.mcdev.mcadditions.recipe;

import net.mcdev.mcadditions.MCAdditions;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MCARecipes {
    public static final RecipeType<ShearsWoolRecipe> SHEARS_WOOL_RECIPE_TYPE = registerType("shears_wool");

    public static final ShearsWoolRecipeSerializer SHEARS_WOOL_RECIPE_SERIALIZER = Registry.register(
        Registries.RECIPE_SERIALIZER,
        new Identifier(MCAdditions.MOD_ID, "shears_wool"),
        new ShearsWoolRecipeSerializer()
    );

    private static <T extends Recipe<?>> RecipeType<T> registerType(String id) {
        return Registry.register(
                Registries.RECIPE_TYPE, new Identifier(MCAdditions.MOD_ID, id),
                new RecipeType<T>() {
                    @Override
                    public String toString() {
                        return id;
                    }
                }
        );
    }

    public static void registerRecipes() {
        MCAdditions.LOGGER.info("Registering recipes for " + MCAdditions.MOD_ID);
    }
}
