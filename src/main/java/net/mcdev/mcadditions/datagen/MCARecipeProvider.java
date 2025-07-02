package net.mcdev.mcadditions.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.mcdev.mcadditions.block.MCABlocks;
import net.mcdev.mcadditions.item.MCAItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class MCARecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> RUBY_SMELTABLES = List.of(
        MCAItems.RAW_RUBY,
        MCABlocks.RUBY_ORE, MCABlocks.DEEPSLATE_RUBY_ORE
    );

    public MCARecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        generateBlastingSmeltingRecipes(exporter);

        generateShapelessItemsRecipes(exporter);
        generateShapelessBlocksRecipes(exporter);

        generateShapedItemsRecipes(exporter);
        generateShapedBlocksRecipes(exporter);

        generateVanillaPlusRecipes(exporter);
    }

    private void generateBlastingSmeltingRecipes(Consumer<RecipeJsonProvider> exporter) {
        // Smelting
        offerSmelting(
            exporter,
            RUBY_SMELTABLES,
            RecipeCategory.MISC,
            MCAItems.RUBY,
            0.7f, 200, "ruby"
        );

        // Blasting
        offerBlasting(
            exporter,
            RUBY_SMELTABLES,
            RecipeCategory.MISC,
            MCAItems.RUBY,
            0.7f, 200, "ruby"
        );
    }

    private void generateShapelessItemsRecipes(Consumer<RecipeJsonProvider> exporter) {
    }

    private void generateShapelessBlocksRecipes(Consumer<RecipeJsonProvider> exporter) {
        // Ruby Stairs
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, MCABlocks.RUBY_STAIRS, MCAItems.RUBY);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, MCABlocks.RUBY_SLAB, MCAItems.RUBY);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, MCABlocks.RUBY_BUTTON, MCAItems.RUBY);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, MCABlocks.RUBY_PRESSURE_PLATE, MCAItems.RUBY);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, MCABlocks.RUBY_FENCE, MCAItems.RUBY);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, MCABlocks.RUBY_FENCE_GATE, MCAItems.RUBY);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, MCABlocks.RUBY_WALL, MCAItems.RUBY);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, MCABlocks.RUBY_DOOR, MCAItems.RUBY);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, MCABlocks.RUBY_TRAPDOOR, MCAItems.RUBY);
    }

    private void generateShapedItemsRecipes(Consumer<RecipeJsonProvider> exporter) {
        // Ore Detector
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, MCAItems.ORE_DETECTOR, 1)
            .pattern("  S")
            .pattern("RSR")
            .pattern("III")
            .input('S', Items.STICK)
            .input('R', Items.REDSTONE)
            .input('I', Items.IRON_BLOCK)
            .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
            .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
            .criterion(hasItem(Items.IRON_BLOCK), conditionsFromItem(Items.IRON_BLOCK))
            .offerTo(exporter, new Identifier(getRecipeName(MCAItems.ORE_DETECTOR)));

        // Ruby Scepter
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, MCAItems.RUBY_SCEPTER, 1)
            .pattern("  R")
            .pattern(" I ")
            .pattern("I  ")
            .input('R', MCAItems.RUBY)
            .input('I', Items.IRON_INGOT)
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
            .offerTo(exporter, new Identifier(getRecipeName(MCAItems.RUBY_SCEPTER)));

        // Ruby Sword
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, MCAItems.RUBY_SWORD, 1)
            .pattern(" R ")
            .pattern(" R ")
            .pattern(" S ")
            .input('R', MCAItems.RUBY)
            .input('S', Items.STICK)
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
            .offerTo(exporter, new Identifier(getRecipeName(MCAItems.RUBY_SWORD)));

        // Ruby Pickaxe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, MCAItems.RUBY_PICKAXE, 1)
            .pattern("RRR")
            .pattern(" S ")
            .pattern(" S ")
            .input('R', MCAItems.RUBY)
            .input('S', Items.STICK)
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
            .offerTo(exporter, new Identifier(getRecipeName(MCAItems.RUBY_PICKAXE)));

        // Ruby Axe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, MCAItems.RUBY_AXE, 1)
            .pattern("RR ")
            .pattern("RS ")
            .pattern(" S ")
            .input('R', MCAItems.RUBY)
            .input('S', Items.STICK)
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
            .offerTo(exporter, new Identifier(getRecipeName(MCAItems.RUBY_AXE)));

        // Ruby Shovel
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, MCAItems.RUBY_SHOVEL, 1)
            .pattern(" R ")
            .pattern(" S ")
            .pattern(" S ")
            .input('R', MCAItems.RUBY)
            .input('S', Items.STICK)
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
            .offerTo(exporter, new Identifier(getRecipeName(MCAItems.RUBY_SHOVEL)));

        // Ruby Hoe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, MCAItems.RUBY_HOE, 1)
            .pattern("RR ")
            .pattern(" S ")
            .pattern(" S ")
            .input('R', MCAItems.RUBY)
            .input('S', Items.STICK)
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
            .offerTo(exporter, new Identifier(getRecipeName(MCAItems.RUBY_HOE)));

        // Ruby Helmet
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, MCAItems.RUBY_HELMET, 1)
            .pattern("RRR")
            .pattern("R R")
            .pattern("   ")
            .input('R', MCAItems.RUBY)
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .offerTo(exporter, new Identifier(getRecipeName(MCAItems.RUBY_HELMET)));

        // Ruby Chestplate
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, MCAItems.RUBY_CHESTPLATE, 1)
            .pattern("R R")
            .pattern("RRR")
            .pattern("RRR")
            .input('R', MCAItems.RUBY)
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .offerTo(exporter, new Identifier(getRecipeName(MCAItems.RUBY_CHESTPLATE)));

        // Ruby Leggings
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, MCAItems.RUBY_LEGGINGS, 1)
            .pattern("RRR")
            .pattern("R R")
            .pattern("R R")
            .input('R', MCAItems.RUBY)
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .offerTo(exporter, new Identifier(getRecipeName(MCAItems.RUBY_LEGGINGS)));

        // Ruby Boots
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, MCAItems.RUBY_BOOTS, 1)
            .pattern("   ")
            .pattern("R R")
            .pattern("R R")
            .input('R', MCAItems.RUBY)
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .offerTo(exporter, new Identifier(getRecipeName(MCAItems.RUBY_BOOTS)));
    }

    private void generateShapedBlocksRecipes(Consumer<RecipeJsonProvider> exporter) {
        // Ruby Item and Ruby Block
        offerReversibleCompactingRecipes(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            MCAItems.RUBY,
            RecipeCategory.DECORATIONS,
            MCABlocks.RUBY_BLOCK
        );

        createStairsRecipe(MCABlocks.RUBY_STAIRS, Ingredient.ofItems(MCAItems.RUBY))
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .offerTo(exporter, new Identifier(getRecipeName(MCABlocks.RUBY_STAIRS)));

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, MCABlocks.RUBY_SLAB, MCAItems.RUBY);

        offerPressurePlateRecipe(exporter, MCABlocks.RUBY_PRESSURE_PLATE, MCAItems.RUBY);

        createFenceRecipe(MCABlocks.RUBY_FENCE, Ingredient.ofItems(MCAItems.RUBY))
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .offerTo(exporter, new Identifier(getRecipeName(MCABlocks.RUBY_FENCE)));

        createFenceGateRecipe(MCABlocks.RUBY_FENCE_GATE, Ingredient.ofItems(MCAItems.RUBY))
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .offerTo(exporter, new Identifier(getRecipeName(MCABlocks.RUBY_FENCE_GATE)));

        // Ruby Wall
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, MCABlocks.RUBY_WALL)
            .pattern(" R ")
            .pattern("RRR")
            .pattern("RRR")
            .input('R', MCAItems.RUBY)
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .offerTo(exporter, new Identifier(getRecipeName(MCABlocks.RUBY_WALL)));

        createDoorRecipe(MCABlocks.RUBY_DOOR, Ingredient.ofItems(MCAItems.RUBY))
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .offerTo(exporter, new Identifier(getRecipeName(MCABlocks.RUBY_DOOR)));

        // Ruby Trapdoor
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, MCABlocks.RUBY_TRAPDOOR)
            .pattern("   ")
            .pattern("RRR")
            .pattern("RRR")
            .input('R', MCAItems.RUBY)
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .offerTo(exporter, new Identifier(getRecipeName(MCABlocks.RUBY_TRAPDOOR)));

        // Raw Ruby Block
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, MCABlocks.RAW_RUBY_BLOCK, 1)
            .pattern("SR ")
            .pattern("RS ")
            .pattern("   ")
            .input('S', Items.STONE)
            .input('R', MCAItems.RAW_RUBY)
            .criterion(hasItem(Items.STONE), conditionsFromItem(Items.STONE))
            .criterion(hasItem(MCAItems.RUBY), conditionsFromItem(MCAItems.RUBY))
            .offerTo(exporter, new Identifier(getRecipeName(MCABlocks.RAW_RUBY_BLOCK)));
    }

    private void generateVanillaPlusRecipes(Consumer<RecipeJsonProvider> exporter) {
        // String to Cobweb
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Blocks.COBWEB)
            .pattern("S S")
            .pattern(" S ")
            .pattern("S S")
            .input('S', Items.STRING)
            .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
            .offerTo(exporter, new Identifier(getRecipeName(Blocks.COBWEB)));

        // Cobweb to String
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.STRING, 5)
            .input(Blocks.COBWEB)
            .criterion(hasItem(Blocks.COBWEB), conditionsFromItem(Blocks.COBWEB))
            .offerTo(exporter, new Identifier(getRecipeName(Items.STRING)));

        // Mob Spawner
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.SPAWNER)
            .pattern("CCC")
            .pattern("CDC")
            .pattern("CCC")
            .input('C', Items.CHAIN)
            .input('D', MCAItems.DARK_ESSENCE)
            .criterion(hasItem(Items.CHAIN), conditionsFromItem(Items.CHAIN))
            .criterion(hasItem(MCAItems.DARK_ESSENCE), conditionsFromItem(MCAItems.DARK_ESSENCE))
            .offerTo(exporter, new Identifier(getRecipeName(Blocks.SPAWNER)));

        // Mob Spawner with Monster
//        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Blocks.SPAWNER, 1)
//            .input(Blocks.SPAWNER)
//            .input(MCAItems.DARK_ESSENCE)
//            .criterion(hasItem(Blocks.SPAWNER), conditionsFromItem(Blocks.SPAWNER))
//            .criterion(hasItem(MCAItems.DARK_ESSENCE), conditionsFromItem(MCAItems.DARK_ESSENCE))
//            .offerTo(exporter, new Identifier(getRecipeName(Items.STRING)));


        // Saddle
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.SADDLE)
            .pattern(" L ")
            .pattern("L L")
            .pattern("I I")
            .input('L', Items.LEATHER)
            .input('I', Items.IRON_INGOT)
            .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
            .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
            .offerTo(exporter, new Identifier(getRecipeName(Items.SADDLE)));

        // Leather Horse Armor
//        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.LEATHER_HORSE_ARMOR)
//            .pattern("  L")
//            .pattern("LWL")
//            .pattern("LLL")
//            .input('L', Items.LEATHER)
//            .input('W', ItemTags.WOOL)
//            .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
//            .offerTo(exporter, new Identifier(getRecipeName(Items.LEATHER_HORSE_ARMOR)));

        // Iron Horse Armor
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.IRON_HORSE_ARMOR)
            .pattern("  I")
            .pattern("ILI")
            .pattern("III")
            .input('I', Items.IRON_INGOT)
            .input('L', Items.LEATHER)
            .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
            .offerTo(exporter, new Identifier(getRecipeName(Items.IRON_HORSE_ARMOR)));

        // Gold Horse Armor
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.GOLDEN_HORSE_ARMOR)
            .pattern("  G")
            .pattern("GLG")
            .pattern("GGG")
            .input('G', Items.GOLD_INGOT)
            .input('L', Items.LEATHER)
            .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
            .offerTo(exporter, new Identifier(getRecipeName(Items.GOLDEN_HORSE_ARMOR)));

        // Diamond Horse Armor
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.DIAMOND_HORSE_ARMOR)
            .pattern("  D")
            .pattern("DLD")
            .pattern("DDD")
            .input('D', Items.DIAMOND)
            .input('L', Items.LEATHER)
            .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
            .offerTo(exporter, new Identifier(getRecipeName(Items.DIAMOND_HORSE_ARMOR)));
    }
}
