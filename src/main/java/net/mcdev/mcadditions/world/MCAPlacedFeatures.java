package net.mcdev.mcadditions.world;

import net.mcdev.mcadditions.MCAdditions;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class MCAPlacedFeatures {
    public static final RegistryKey<PlacedFeature> RUBY_ORE_PLACED_KEY = registerKey("ruby_ore_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(
            context, RUBY_ORE_PLACED_KEY,
            configuredFeatureRegistryEntryLookup.getOrThrow(MCAConfiguredFeatures.RUBY_ORE_KEY),

            // trapezoid makes the middle between the positive and negative offsets of ore spawn have much more Veins per Chunk
            // uniform is all the way between positive and negative offsets
            MCAOrePlacement.modifiersWithCount(
                6, // Veins per Chunk
                HeightRangePlacementModifier.trapezoid(YOffset.fixed(-40), YOffset.fixed(20))
            )
        );
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MCAdditions.MOD_ID, name));
    }

    private static void register(
        Registerable<PlacedFeature> context,
        RegistryKey<PlacedFeature> key,
        RegistryEntry<ConfiguredFeature<?, ?>> configuration,
        List<PlacementModifier> modifiers
    ) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
