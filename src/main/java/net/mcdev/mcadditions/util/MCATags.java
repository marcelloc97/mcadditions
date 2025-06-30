package net.mcdev.mcadditions.util;

import net.mcdev.mcadditions.MCAdditions;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class MCATags {
    public static class Blocks {
        public static final TagKey<Block> ORE_DETECTOR_DETECTABLE_BLOCKS =
            createTag("ore_detector_detectable_blocks");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(MCAdditions.MOD_ID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(MCAdditions.MOD_ID, name));
        }
    }
}
