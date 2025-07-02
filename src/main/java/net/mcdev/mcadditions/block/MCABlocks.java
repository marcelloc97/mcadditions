package net.mcdev.mcadditions.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.mcdev.mcadditions.MCAdditions;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class MCABlocks {
    public static final Block RUBY_BLOCK;
    public static final Block RAW_RUBY_BLOCK;

    public static final Block RUBY_ORE;
    public static final Block DEEPSLATE_RUBY_ORE;

    public static final Block RUBY_STAIRS;
    public static final Block RUBY_SLAB;

    public static final Block RUBY_BUTTON;
    public static final Block RUBY_PRESSURE_PLATE;

    public static final Block RUBY_FENCE;
    public static final Block RUBY_FENCE_GATE;
    public static final Block RUBY_WALL;

    public static final Block RUBY_DOOR;
    public static final Block RUBY_TRAPDOOR;


    static {
        //region Building Blocks
        RUBY_BLOCK = registerBlock(
            "ruby_block",
            new Block(
                FabricBlockSettings
                    .copyOf(Blocks.IRON_BLOCK)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            )
        );

        RUBY_STAIRS = registerBlock(
            "ruby_stairs",
            new StairsBlock(
                RUBY_BLOCK.getDefaultState(),
                FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
            )
        );

        RUBY_SLAB = registerBlock(
            "ruby_slab",
            new SlabBlock(
                FabricBlockSettings.copyOf(Blocks.COBBLESTONE_SLAB)
            )
        );

        RUBY_BUTTON = registerBlock(
            "ruby_button",
            new ButtonBlock(
                FabricBlockSettings.copyOf(Blocks.IRON_BLOCK),
                BlockSetType.IRON,
                10, true
            )
        );

        RUBY_PRESSURE_PLATE = registerBlock(
            "ruby_pressure_plate",
            new PressurePlateBlock(
                PressurePlateBlock.ActivationRule.EVERYTHING,
                FabricBlockSettings.copyOf(Blocks.IRON_BLOCK),
                BlockSetType.IRON
            )
        );

        RUBY_FENCE = registerBlock(
            "ruby_fence",
            new FenceBlock(
                FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
            )
        );

        RUBY_FENCE_GATE = registerBlock(
            "ruby_fence_gate",
            new FenceGateBlock(
                FabricBlockSettings.copyOf(Blocks.IRON_BLOCK),
                WoodType.OAK
            )
        );

        RUBY_WALL = registerBlock(
            "ruby_wall",
            new WallBlock(
                FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
            )
        );

        RUBY_DOOR = registerBlock(
            "ruby_door",
            new DoorBlock(
                FabricBlockSettings.copyOf(Blocks.IRON_DOOR),
                BlockSetType.IRON
            )
        );

        RUBY_TRAPDOOR = registerBlock(
            "ruby_trapdoor",
            new TrapdoorBlock(
                FabricBlockSettings.copyOf(Blocks.IRON_TRAPDOOR),
                BlockSetType.IRON
            )
        );
        //endregion

        //region Misc Blocks
        RAW_RUBY_BLOCK = registerBlock(
            "raw_ruby_block",
            new Block(
                FabricBlockSettings
                    .copyOf(Blocks.IRON_BLOCK)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            )
        );
        //endregion

        //region ORES
        RUBY_ORE = registerBlock(
            "ruby_ore",
            new ExperienceDroppingBlock(
                FabricBlockSettings
                    .copyOf(Blocks.STONE)
                    .strength(2f)
                    .luminance(6),
                UniformIntProvider.create(1, 3)
            )
        );

        DEEPSLATE_RUBY_ORE = registerBlock(
            "deepslate_ruby_ore",
            new ExperienceDroppingBlock(
                FabricBlockSettings
                    .copyOf(Blocks.DEEPSLATE)
                    .strength(4f)
                    .luminance(6),
                UniformIntProvider.create(2, 4)
            )
        );
        //endregion

        //region Special Blocks
        //endregion

        //region Crop Blocks
        //endregion
    }


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);

        return Registry.register(Registries.BLOCK, new Identifier(MCAdditions.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(
            Registries.ITEM, new Identifier(MCAdditions.MOD_ID, name),
            new BlockItem(block, new FabricItemSettings())
        );
    }

    public static void registerBlocks() {
        MCAdditions.LOGGER.info("Registering blocks for " + MCAdditions.MOD_ID);
    }
}
