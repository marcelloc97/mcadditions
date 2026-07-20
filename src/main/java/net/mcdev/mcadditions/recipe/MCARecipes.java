package net.mcdev.mcadditions.recipe;

import net.mcdev.mcadditions.MCAdditions;
import net.mcdev.mcadditions.recipe.custom.StringCobwebRecipe;
import net.mcdev.mcadditions.recipe.custom.StringWoolRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MCARecipes {
    // Recipe Types
    public static final RecipeType<StringWoolRecipe> STRING_WOOL_RECIPE_TYPE = registerType("string_wool");
    public static final RecipeType<StringWoolRecipe> STRING_COBWEB_RECIPE_TYPE = registerType("string_cobweb");

    // Serializers
    public static final CustomRecipeSerializer<StringWoolRecipe> STRING_WOOL_RECIPE_SERIALIZER =
        Registry.register(
            Registries.RECIPE_SERIALIZER,
            new Identifier(MCAdditions.MOD_ID, "string_wool"),
            new CustomRecipeSerializer<>(StringWoolRecipe::new)
        );

    public static final CustomRecipeSerializer<StringCobwebRecipe> STRING_COBWEB_RECIPE_SERIALIZER =
        Registry.register(
            Registries.RECIPE_SERIALIZER,
            new Identifier(MCAdditions.MOD_ID, "string_cobweb"),
            new CustomRecipeSerializer<>(StringCobwebRecipe::new)
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
        MCAdditions.LOGGER.info("Registering custom recipes for " + MCAdditions.MOD_ID);
    }
}
