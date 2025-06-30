package net.mcdev.mcadditions.item;

import net.mcdev.mcadditions.MCAdditions;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.function.Supplier;

public enum MCAArmorMaterials implements ArmorMaterial {
    RUBY(
        "ruby", 25, new int[] { 3, 7, 5, 3 },
        10, 2.0f, 0.1f,
        SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.ofItems(MCAItems.RUBY)
    );

    private static final int[] BASE_DURABILITY = { 11, 16, 15, 13 };

    private final String name;
    private final int durability;
    private final int[] protections;
    private final int enchantability;
    private final float toughness;
    private final float knockbackResistance;
    private final SoundEvent equipSound;
    private final Supplier<Ingredient> repairIngredient;

    MCAArmorMaterials(
        String name, int durability, int[] protections, int enchantability,
        float toughness, float knockbackResistance,
        SoundEvent equipSound, Supplier<Ingredient> repairIngredient
    ) {
        this.name = name;

        this.durability = durability;
        this.protections = protections;
        this.enchantability = enchantability;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;

        this.equipSound = equipSound;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public String getName() {
        return "%s:%s".formatted(MCAdditions.MOD_ID, name);
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * durability;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return protections[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}
