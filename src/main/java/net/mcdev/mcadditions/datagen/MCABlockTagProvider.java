package net.mcdev.mcadditions.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.mcdev.mcadditions.block.MCABlocks;
import net.mcdev.mcadditions.util.MCATags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class MCABlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public MCABlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.FENCES)
            .add(MCABlocks.RUBY_FENCE);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
            .add(MCABlocks.RUBY_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.WALLS)
            .add(MCABlocks.RUBY_WALL);


        getOrCreateTagBuilder(MCATags.Blocks.ORE_DETECTOR_DETECTABLE_BLOCKS)
            .add(MCABlocks.RUBY_ORE)
            .add(MCABlocks.DEEPSLATE_RUBY_ORE)
            .add(MCABlocks.NETHER_RUBY_ORE)
            .add(MCABlocks.END_STONE_RUBY_ORE)
            .forceAddTag(BlockTags.COPPER_ORES)
            .forceAddTag(BlockTags.IRON_ORES)
            .forceAddTag(BlockTags.GOLD_ORES)
            .forceAddTag(BlockTags.DIAMOND_ORES)
            .forceAddTag(BlockTags.EMERALD_ORES)
            .forceAddTag(BlockTags.REDSTONE_ORES)
            .forceAddTag(BlockTags.LAPIS_ORES);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(MCABlocks.RAW_RUBY_BLOCK)
            .add(MCABlocks.RUBY_BLOCK)
            .add(MCABlocks.RUBY_ORE)
            .add(MCABlocks.DEEPSLATE_RUBY_ORE)
            .add(MCABlocks.NETHER_RUBY_ORE)
            .add(MCABlocks.END_STONE_RUBY_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
            .add(MCABlocks.RAW_RUBY_BLOCK)
            .add(MCABlocks.RUBY_BLOCK)
            .add(MCABlocks.RUBY_ORE)
            .add(MCABlocks.NETHER_RUBY_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
            .add(MCABlocks.DEEPSLATE_RUBY_ORE)
            .add(MCABlocks.END_STONE_RUBY_ORE);
    }
}
