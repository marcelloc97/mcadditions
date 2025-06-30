package net.mcdev.mcadditions.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.mcdev.mcadditions.block.MCABlocks;
import net.mcdev.mcadditions.item.MCAItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class MCALootTableProvider extends FabricBlockLootTableProvider {
    public MCALootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(MCABlocks.RAW_RUBY_BLOCK);

        addDrop(MCABlocks.RUBY_BLOCK);
        addDrop(MCABlocks.RUBY_STAIRS);
        addDrop(MCABlocks.RUBY_SLAB, slabDrops(MCABlocks.RUBY_SLAB));
        addDrop(MCABlocks.RUBY_BUTTON);
        addDrop(MCABlocks.RUBY_PRESSURE_PLATE);
        addDrop(MCABlocks.RUBY_FENCE);
        addDrop(MCABlocks.RUBY_FENCE_GATE);
        addDrop(MCABlocks.RUBY_WALL);
        addDrop(MCABlocks.RUBY_DOOR, doorDrops(MCABlocks.RUBY_DOOR));
        addDrop(MCABlocks.RUBY_TRAPDOOR);


        addDrop(
            MCABlocks.RUBY_ORE,
            copperLikeOreDrops(MCABlocks.RUBY_ORE, MCAItems.RAW_RUBY, 2, 5)
        );

        addDrop(
            MCABlocks.DEEPSLATE_RUBY_ORE,
            copperLikeOreDrops(MCABlocks.DEEPSLATE_RUBY_ORE, MCAItems.RAW_RUBY, 4, 8)
        );

        addDrop(
            MCABlocks.NETHER_RUBY_ORE,
            copperLikeOreDrops(MCABlocks.RUBY_ORE, MCAItems.RAW_RUBY, 2, 5)
        );

        addDrop(
            MCABlocks.END_STONE_RUBY_ORE,
            copperLikeOreDrops(MCABlocks.RUBY_ORE, MCAItems.RAW_RUBY, 6, 12)
        );
    }

    public LootTable.Builder copperLikeOreDrops(Block drop, Item item, int min, int max) {
        return dropsWithSilkTouch(
            drop,
            this.applyExplosionDecay(
                drop,
                ItemEntry.builder(item)
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)))
                    .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
            )
        );
    }
}
