package net.mcdev.mcadditions.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.mcdev.mcadditions.MCAdditions;
import net.mcdev.mcadditions.block.MCABlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MCAItemGroups {
    public static final ItemGroup RUBY_GROUP;

    static {
        RUBY_GROUP = Registry.register(
            Registries.ITEM_GROUP, new Identifier(MCAdditions.MOD_ID, "ruby"),
            FabricItemGroup.builder()
                .displayName(Text.translatable("itemgroup.mcadditions"))
                .icon(() -> new ItemStack(MCAItems.RUBY_SCEPTER)).entries(((displayContext, entries) -> {
                    entries.add(MCAItems.ORE_DETECTOR);

                    entries.add(MCAItems.RAW_RUBY);
                    entries.add(MCAItems.RUBY);

                    entries.add(MCAItems.DARK_ESSENCE);

                    entries.add(MCAItems.RUBY_SCEPTER);

                    entries.add(MCABlocks.RUBY_BLOCK);

                    entries.add(MCABlocks.RUBY_STAIRS);
                    entries.add(MCABlocks.RUBY_SLAB);
                    entries.add(MCABlocks.RUBY_BUTTON);
                    entries.add(MCABlocks.RUBY_PRESSURE_PLATE);
                    entries.add(MCABlocks.RUBY_FENCE);
                    entries.add(MCABlocks.RUBY_FENCE_GATE);
                    entries.add(MCABlocks.RUBY_WALL);
                    entries.add(MCABlocks.RUBY_DOOR);
                    entries.add(MCABlocks.RUBY_TRAPDOOR);

                    entries.add(MCABlocks.RAW_RUBY_BLOCK);

                    entries.add(MCABlocks.RUBY_ORE);
                    entries.add(MCABlocks.DEEPSLATE_RUBY_ORE);

                    entries.add(MCAItems.RUBY_SWORD);
                    entries.add(MCAItems.RUBY_PICKAXE);
                    entries.add(MCAItems.RUBY_AXE);
                    entries.add(MCAItems.RUBY_SHOVEL);
                    entries.add(MCAItems.RUBY_HOE);

                    entries.add(MCAItems.RUBY_HELMET);
                    entries.add(MCAItems.RUBY_CHESTPLATE);
                    entries.add(MCAItems.RUBY_LEGGINGS);
                    entries.add(MCAItems.RUBY_BOOTS);
                }))
                .build()
        );
    }

    public static void registerItemGroups() {
        MCAdditions.LOGGER.info("Registering Item Groups for " + MCAdditions.MOD_ID);
    }
}
