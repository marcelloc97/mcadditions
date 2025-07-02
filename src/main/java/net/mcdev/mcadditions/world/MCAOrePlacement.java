package net.mcdev.mcadditions.world;

import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class MCAOrePlacement {
//    public static final RegistryKey<PlacedFeature> ORE_DIAMOND = PlacedFeatures.of("ore_diamond");
//    public static final RegistryKey<PlacedFeature> ORE_DIAMOND_LARGE = PlacedFeatures.of("ore_diamond_large");
//    public static final RegistryKey<PlacedFeature> ORE_DIAMOND_BURIED = PlacedFeatures.of("ore_diamond_buried");

//    public static void boostrap() {
//        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry20 = registryEntryLookup.getOrThrow(OreConfiguredFeatures.ORE_DIAMOND_SMALL);
//        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry21 = registryEntryLookup.getOrThrow(OreConfiguredFeatures.ORE_DIAMOND_LARGE);
//        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry22 = registryEntryLookup.getOrThrow(OreConfiguredFeatures.ORE_DIAMOND_BURIED);

//        PlacedFeatures.register(
//            featureRegisterable,
//            ORE_DIAMOND,
//            registryEntry20,
//            modifiersWithCount(7, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)))
//        );
//        PlacedFeatures.register(
//            featureRegisterable,
//            ORE_DIAMOND_LARGE,
//            registryEntry21,
//            modifiersWithRarity(9, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)))
//        );
//        PlacedFeatures.register(
//            featureRegisterable,
//            ORE_DIAMOND_BURIED,
//            registryEntry22,
//            modifiersWithCount(4, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)))
//        );
//    }

    public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}
