package net.mcdev.mcadditions.world;

import net.mcdev.mcadditions.MCAdditions;
import net.mcdev.mcadditions.block.MCABlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class MCAConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> RUBY_ORE_KEY = registerKey("ruby_ore");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacable = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldRubyOres = List.of(
            OreFeatureConfig.createTarget(stoneReplacables, MCABlocks.RUBY_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(deepslateReplacable, MCABlocks.DEEPSLATE_RUBY_ORE.getDefaultState())
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
