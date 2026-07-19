package net.mcdev.mcadditions.world;

import net.mcdev.mcadditions.MCAdditions;
import net.mcdev.mcadditions.block.MCABlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class MCAConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> RUBY_ORE_KEY = registerKey("ruby_ore");
//    public static final RegistryKey<ConfiguredFeature<?, ?>> RUBY_GEODE_KEY = registerKey("ruby_geode");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

//        ConfiguredFeatures.register(
//            context, RUBY_GEODE_KEY, Feature.GEODE,
//            new GeodeFeatureConfig(
//                new GeodeLayerConfig(
//                    BlockStateProvider.of(Blocks.AIR),
//                    BlockStateProvider.of(MCABlocks.RUBY_BLOCK),
//                    /*BlockStateProvider.of(Blocks.BUDDING_AMETHYST)*/BlockStateProvider.of(MCABlocks.RUBY_ORE),
//                    BlockStateProvider.of(Blocks.CALCITE),
//                    BlockStateProvider.of(Blocks.SMOOTH_BASALT),
//                    List.of(
//                        MCABlocks.RUBY_ORE.getDefaultState()
////                        Blocks.SMALL_AMETHYST_BUD.getDefaultState(),
////                        Blocks.MEDIUM_AMETHYST_BUD.getDefaultState(),
////                        Blocks.LARGE_AMETHYST_BUD.getDefaultState(),
////                        Blocks.AMETHYST_CLUSTER.getDefaultState()
//                    ),
//                    BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS
//                ),
//
//                new GeodeLayerThicknessConfig(1.7, 2.2, 3.2, 4.2),
//
//                new GeodeCrackConfig(0.95, (double)2.0F, 2),
//
//                0.35, 0.083, true,
//
//                UniformIntProvider.create(4, 6), UniformIntProvider.create(3, 4), UniformIntProvider.create(1, 2),
//
//                -16, 16, 0.05, 1
//            )
//        );

        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldRubyOres = List.of(
            OreFeatureConfig.createTarget(stoneReplaceables, MCABlocks.RUBY_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(deepslateReplaceables, MCABlocks.DEEPSLATE_RUBY_ORE.getDefaultState())
        );

        // 12 is very small and pretty rare
        register(context, RUBY_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldRubyOres, 6));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(MCAdditions.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
        Registerable<ConfiguredFeature<?, ?>> context,
        RegistryKey<ConfiguredFeature<?, ?>> key,
        F feature, FC configuration
    ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
