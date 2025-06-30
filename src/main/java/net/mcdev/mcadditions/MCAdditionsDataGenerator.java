package net.mcdev.mcadditions;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.mcdev.mcadditions.datagen.*;

public class MCAdditionsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(MCABlockTagProvider::new);
		pack.addProvider(MCAItemTagProvider::new);
		pack.addProvider(MCALootTableProvider::new);
		pack.addProvider(MCAModelProvider::new);
		pack.addProvider(MCARecipeProvider::new);
	}
}
