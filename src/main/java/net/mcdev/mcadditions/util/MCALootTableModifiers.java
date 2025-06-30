package net.mcdev.mcadditions.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.mcdev.mcadditions.item.MCAItems;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MCALootTableModifiers {
    private static final Identifier JUNGLE_TEMPLE_ID = new Identifier("minecraft", "chests/jungle_temple");
    private static final Identifier SUSPICIOUS_SAND_ID = new Identifier("minecraft", "archaeology/desert_pyramid");


    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, lootTableSource) -> {
            if (JUNGLE_TEMPLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.2f)) // 0.2f = 20% drop chance ; 1f = 100% drop chance
                    .with(ItemEntry.builder(MCAItems.ORE_DETECTOR))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
        }));

        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, lootTable, lootTableSource) -> {
            if (SUSPICIOUS_SAND_ID.equals(id)) {
                List<LootPoolEntry> entries = new ArrayList<>(Arrays.asList(lootTable.pools[0].entries));

                entries.add(ItemEntry.builder(MCAItems.ORE_DETECTOR).build());
                entries.add(ItemEntry.builder(MCAItems.RUBY).weight(5).build());
                entries.add(ItemEntry.builder(MCAItems.RUBY_SCEPTER).build());

                LootPool.Builder pool = LootPool.builder().with(entries);
                return LootTable.builder().pool(pool).build();
            }

            return null;
        });
    }
}
