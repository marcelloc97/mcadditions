package net.mcdev.mcadditions.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.mcdev.mcadditions.block.MCABlocks;
import net.mcdev.mcadditions.item.MCAItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class MCAModelProvider extends FabricModelProvider {
    public MCAModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool rubyPool = (
            blockStateModelGenerator.registerCubeAllModelTexturePool(MCABlocks.RUBY_BLOCK)
        );

        blockStateModelGenerator.registerSimpleCubeAll(MCABlocks.RAW_RUBY_BLOCK);

        rubyPool.stairs(MCABlocks.RUBY_STAIRS);
        rubyPool.slab(MCABlocks.RUBY_SLAB);
        rubyPool.button(MCABlocks.RUBY_BUTTON);
        rubyPool.pressurePlate(MCABlocks.RUBY_PRESSURE_PLATE);
        rubyPool.fence(MCABlocks.RUBY_FENCE);
        rubyPool.fenceGate(MCABlocks.RUBY_FENCE_GATE);
        rubyPool.wall(MCABlocks.RUBY_WALL);

        blockStateModelGenerator.registerDoor(MCABlocks.RUBY_DOOR);
        blockStateModelGenerator.registerTrapdoor(MCABlocks.RUBY_TRAPDOOR);

        blockStateModelGenerator.registerSimpleCubeAll(MCABlocks.RUBY_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(MCABlocks.DEEPSLATE_RUBY_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(MCABlocks.NETHER_RUBY_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(MCABlocks.END_STONE_RUBY_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(MCAItems.RAW_RUBY, Models.GENERATED);
        itemModelGenerator.register(MCAItems.RUBY, Models.GENERATED);

        itemModelGenerator.register(MCAItems.DARK_ESSENCE, Models.GENERATED);

        itemModelGenerator.register(MCAItems.ORE_DETECTOR, Models.GENERATED);

        itemModelGenerator.register(MCAItems.RUBY_SWORD, Models.HANDHELD);
        itemModelGenerator.register(MCAItems.RUBY_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(MCAItems.RUBY_AXE, Models.HANDHELD);
        itemModelGenerator.register(MCAItems.RUBY_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(MCAItems.RUBY_HOE, Models.HANDHELD);

        itemModelGenerator.registerArmor(((ArmorItem) MCAItems.RUBY_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) MCAItems.RUBY_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) MCAItems.RUBY_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) MCAItems.RUBY_BOOTS));
    }
}
