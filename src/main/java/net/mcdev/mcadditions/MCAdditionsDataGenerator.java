package net.mcdev.mcadditions;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.mcdev.mcadditions.datagen.*;
import net.mcdev.mcadditions.world.MCAConfiguredFeatures;
import net.mcdev.mcadditions.world.MCAPlacedFeatures;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class MCAdditionsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(MCABlockTagProvider::new);
		pack.addProvider(MCAItemTagProvider::new);
		pack.addProvider(MCALootTableProvider::new);
		pack.addProvider(MCAModelProvider::new);
		pack.addProvider(MCARecipeProvider::new);
		pack.addProvider(MCAWorldGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, MCAConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, MCAPlacedFeatures::bootstrap);
	}
}
