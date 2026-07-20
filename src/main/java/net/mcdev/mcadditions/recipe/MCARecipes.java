package net.mcdev.mcadditions.recipe;

import net.mcdev.mcadditions.MCAdditions;
import net.mcdev.mcadditions.recipe.custom.StringCobwebRecipe;
import net.mcdev.mcadditions.recipe.custom.StringWoolRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MCARecipes {
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


    public static void registerRecipes() {
        MCAdditions.LOGGER.info("Registering custom recipes for " + MCAdditions.MOD_ID);
    }
}
