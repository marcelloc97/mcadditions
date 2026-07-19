package net.mcdev.mcadditions.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.mcdev.mcadditions.item.MCAItems;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

public class MCALootTableModifiers {
    private static final Identifier JUNGLE_TEMPLE_ID = new Identifier("minecraft", "chests/jungle_temple");
    private static final Identifier SHIPWRECK_TREASURE_ID = new Identifier("minecraft", "chests/shipwreck_treasure");
    private static final Identifier SUSPICIOUS_SAND_ID = new Identifier("minecraft", "archaeology/desert_pyramid");

    private static final Identifier COW_ID = new Identifier("minecraft", "entities/cow");


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

            if (SHIPWRECK_TREASURE_ID.equals(id)) {
                LootPool.Builder rawRubyPool = LootPool.builder()
                    .with(
                        ItemEntry.builder(MCAItems.RUBY)
                            .conditionally(RandomChanceLootCondition.builder(0.6f))
                            .apply(SetCountLootFunction.builder(
                                UniformLootNumberProvider.create(5f, 13f)
                            ))
                    )
                    .with(
                        ItemEntry.builder(MCAItems.RUBY_BOOTS)
                            .conditionally(RandomChanceLootCondition.builder(0.3f))
                            .apply(SetCountLootFunction.builder(
                                UniformLootNumberProvider.create(1f, 1f)
                            ))
                    )
                    .with(
                        ItemEntry.builder(MCAItems.RUBY_CHESTPLATE)
                            .conditionally(RandomChanceLootCondition.builder(0.3f))
                            .apply(SetCountLootFunction.builder(
                                UniformLootNumberProvider.create(1f, 1f)
                            ))
                    )
                    .with(
                        ItemEntry.builder(MCAItems.RUBY_HELMET)
                            .conditionally(RandomChanceLootCondition.builder(0.3f))
                            .apply(SetCountLootFunction.builder(
                                UniformLootNumberProvider.create(1f, 1f)
                            ))
                    )
                    .with(
                        ItemEntry.builder(MCAItems.RUBY_LEGGINGS)
                            .conditionally(RandomChanceLootCondition.builder(0.3f))
                            .apply(SetCountLootFunction.builder(
                                UniformLootNumberProvider.create(1f, 1f)
                            ))
                    )
                    .rolls(ConstantLootNumberProvider.create(1));

                tableBuilder.pool(rawRubyPool);
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

            if (COW_ID.equals(id)) {
                LootPool.Builder beefPool = LootPool.builder().with(
                    ItemEntry.builder(Items.BEEF)
                    .apply(
                        SetCountLootFunction.builder(
                            UniformLootNumberProvider.create(2.0f, 5.0f)
                        )
                    )
                    .apply(
                        FurnaceSmeltLootFunction.builder()
                            .conditionally(
                                EntityPropertiesLootCondition.builder(
                                    LootContext.EntityTarget.THIS,
                                    EntityPredicate.fromJson(JsonHelper.deserialize(
                                        "{ \"flags\": { \"is_on_fire\": true } }"
                                    ))
                                )
                            )
                    )
                    .apply(
                        LootingEnchantLootFunction.builder(
                            UniformLootNumberProvider.create(1.0f, 2.0f)
                        )
                    ).build()
                );

                LootPool.Builder leatherPool = LootPool.builder().with(
                    ItemEntry.builder(Items.LEATHER)
                        .apply(
                            SetCountLootFunction.builder(
                                UniformLootNumberProvider.create(2.0f, 4.0f)
                            )
                        )
                        .apply(
                            LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))
                        )
                        .build()
                );

                return LootTable.builder().pool(leatherPool).pool(beefPool).build();
            }

            return null;
        });
    }

//    private LootPool createLootPool(Item item, Count count) {
//        ItemEntry.Builder entry = ItemEntry.builder(item);
//
//        if (count.max > 1.0f) {
//            entry.apply(
//                SetCountLootFunction.builder(
//                    UniformLootNumberProvider.create(count.min, count.max))
//                ).build();
//            )
//        }
//
//        LootPool.Builder pool = LootPool.builder().with(entry.build());
//        return pool.build();
//    }
//
//    public static class Count {
//        public float min = 1.0f;
//        public float max = 1.0f;
//
//        Count(float min, float max) {
//            this.min = min;
//            this.max = max;
//        }
//    }
}

