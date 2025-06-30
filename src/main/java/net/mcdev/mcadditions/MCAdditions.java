package net.mcdev.mcadditions;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.mcdev.mcadditions.block.MCABlocks;
import net.mcdev.mcadditions.item.MCAItemGroups;
import net.mcdev.mcadditions.item.MCAItems;
import net.mcdev.mcadditions.util.MCALootTableModifiers;
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

		CommandRegistrationCallback.EVENT.register(
			((dispatcher, registryAccess, registrationEnvironment) -> {
				dispatcher.register(CommandManager.literal("slk").executes(context -> {
					context.getSource().sendFeedback(() -> Text.literal("Slk mano, não compensa kkkkk"), false);
					return 1;
				}));
			})
		);
	}

	// TODO: permitir adicionar líquido de poção nos caldeirões.
	// TODO: além da geração de ruby no mundo (geodos), ter também mobs que dropam ruby.
	// TODO: criar utilidade para a Dark Essence (provável ser atrelado com o extrator de essência de mob.

	/*
		TODO: criar um item que serve como extrator de essência de mobs, com essa essência, é possível juntar com um
		      Spawner criado numa Crafting Table para definir um Spawner com aquele monstro específico.
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