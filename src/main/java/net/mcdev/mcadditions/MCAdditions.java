package net.mcdev.mcadditions;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.mcdev.mcadditions.block.MCABlocks;
import net.mcdev.mcadditions.item.MCAItemGroups;
import net.mcdev.mcadditions.item.MCAItems;
import net.mcdev.mcadditions.util.MCALootTableModifiers;
import net.mcdev.mcadditions.world.gen.MCAWorldGeneration;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCAdditions implements ModInitializer {
	public static final String MOD_ID = "mcadditions";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing MCAdditions mod...");

		MCAItemGroups.registerItemGroups();

		MCAItems.registerItems();
		MCABlocks.registerBlocks();

		MCALootTableModifiers.modifyLootTables();

		MCAWorldGeneration.generateMCAWorldGen();

		CommandRegistrationCallback.EVENT.register(
			((dispatcher, registryAccess, registrationEnvironment) -> {
				dispatcher.register(CommandManager.literal("slk").executes(context -> {
					context.getSource().sendFeedback(() -> Text.literal("Slk mano, não compensa kkkkk"), false);
					return 1;
				}));
			})
		);
	}

	// TODO: permitir adicionar líquido de poção nos caldeirões (talvez? Create já faz isso).
	// TODO: além da geração de ruby no mundo (geodos), ter também mobs que dropam ruby.
	// TODO: criar utilidade para a Dark Essence (provável ser atrelado com o extrator de essência de mob. (feito, mas precisa de revisão)
	// TODO: Criar receita do item Name Tag

	/*
		TODO: criar um item que serve como extrator de essência de mobs, com essa essência, é possível juntar com um
		 	  ovo de galinha para criar um ovo de monstro com a essência do mob específico.
		      O Spawner pode ser criado numa Crafting Table.
		      Para definir um Spawner com um monstro específico, basta interagir com o ovo de monstro na mão (esquerda ou direita).
		      O Spawner pode ser colocado e quebrado sem necessidade do uso de Silk Touch na picareta.

	*/

	/*
		TODO: Add item that functions like "tape measure" from real life. Its use is simple, use it in a block, then in other
		      to calculate the distance (in blocks) from block 1 to block 2.
	*/

	/*
		TODO: LivingEntity class has the clearActiveItem method at line 3155, which is responsible to remove the
		      ItemStack item from inventory in active slot/active hand.
	 */
}