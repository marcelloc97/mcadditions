package net.mcdev.mcadditions.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.mcdev.mcadditions.world.MCAPlacedFeatures;
import net.minecraft.world.gen.GenerationStep;

public class MCAOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES, MCAPlacedFeatures.RUBY_ORE_PLACED_KEY
        );
    }
}
