package net.mcdev.mcadditions.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.mcdev.mcadditions.MCAdditions;
import net.mcdev.mcadditions.item.custom.MCAArmorItem;
import net.mcdev.mcadditions.item.custom.OreDetectorItem;
import net.mcdev.mcadditions.item.custom.RubyScepter;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MCAItems {
    //region General items
    //endregion

    //region Materials
    public static final Item DARK_ESSENCE = registerItem("dark_essence", new Item(new FabricItemSettings()));
    //endregion

    //region Ores
    public static final Item RUBY = registerItem("ruby", new Item(new FabricItemSettings()));
    public static final Item RAW_RUBY = registerItem("raw_ruby", new Item(new FabricItemSettings()));
    //endregion

    //region Tools
    public static final Item ORE_DETECTOR = registerItem(
        "ore_detector",
        new OreDetectorItem(new FabricItemSettings().maxCount(1).maxDamage(16))
    );

    public static final Item RUBY_SCEPTER = registerItem(
        "ruby_scepter",
        new RubyScepter(new FabricItemSettings().maxCount(1).maxDamage(8))
    );

    public static final Item RUBY_SWORD = registerItem(
        "ruby_sword",
        new SwordItem(MCAToolMaterials.RUBY, 3, -2.4f, new FabricItemSettings())
    );

    public static final Item RUBY_PICKAXE = registerItem(
        "ruby_pickaxe",
        new PickaxeItem(MCAToolMaterials.RUBY, 1, -2.5f, new FabricItemSettings())
    );

    public static final Item RUBY_AXE = registerItem(
        "ruby_axe",
        new AxeItem(MCAToolMaterials.RUBY, 4, -3f, new FabricItemSettings())
    );

    public static final Item RUBY_SHOVEL = registerItem(
        "ruby_shovel",
        new ShovelItem(MCAToolMaterials.RUBY, 1.5f, -3f, new FabricItemSettings())
    );

    public static final Item RUBY_HOE = registerItem(
        "ruby_hoe",
        new HoeItem(MCAToolMaterials.RUBY, -3, 0f, new FabricItemSettings())
    );
    //endregion

    //region Armors
    public static final Item RUBY_HELMET = registerItem(
        "ruby_helmet",
        new MCAArmorItem(MCAArmorMaterials.RUBY, ArmorItem.Type.HELMET, new FabricItemSettings())
    );

    public static final Item RUBY_CHESTPLATE = registerItem(
        "ruby_chestplate",
        new ArmorItem(MCAArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE, new FabricItemSettings())
    );

    public static final Item RUBY_LEGGINGS = registerItem(
        "ruby_leggings",
        new ArmorItem(MCAArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS, new FabricItemSettings())
    );

    public static final Item RUBY_BOOTS = registerItem(
        "ruby_boots",
        new ArmorItem(MCAArmorMaterials.RUBY, ArmorItem.Type.BOOTS, new FabricItemSettings())
    );
    //endregion


    //region Foods
    //endregion

    //region Fuels
    //endregion

    //region Seeds
    //endregion

    //region Throwables
    //endregion

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MCAdditions.MOD_ID, name), item);
    }

    public static void registerItems() {
        MCAdditions.LOGGER.info("Registering Items for " + MCAdditions.MOD_ID);
    }
}
