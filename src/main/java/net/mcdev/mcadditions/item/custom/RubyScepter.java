package net.mcdev.mcadditions.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.mcdev.mcadditions.item.MCAItems;
import net.mcdev.mcadditions.util.MCAUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RubyScepter extends ToolItem {
    private float originalAttackDamage;
    private float attackDamage;
    private float toolMaterialAttackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public enum RepairSource {
        ANVIL, MENDING
    }

    // TODO: opções para funcionalidade
    // 1 - Comandar Aldeões
    // 2 - Soltar magia (projetil)
    // 3 - Transmutar itens/blocos
    // 4 - Criar uma espécie de Ice Rod do Terraria
    // 5 - Transmutar entidades ou curar Zombie Villagers e Witches [Aprovado]

    public RubyScepter(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, settings);

        settings.rarity(Rarity.EPIC);
        settings.fireproof();

        this.originalAttackDamage = attackDamage;
        this.toolMaterialAttackDamage = toolMaterial.getAttackDamage();
        this.attackDamage = (attackDamage + toolMaterialAttackDamage) / 1.25F;

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(
            EntityAttributes.GENERIC_ATTACK_DAMAGE,
            new EntityAttributeModifier(
                ATTACK_DAMAGE_MODIFIER_ID,
                "Weapon modifier",
                this.attackDamage,
                EntityAttributeModifier.Operation.ADDITION
            )
        );
        builder.put(
            EntityAttributes.GENERIC_ATTACK_SPEED,
            new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", (double)attackSpeed, EntityAttributeModifier.Operation.ADDITION)
        );
        this.attributeModifiers = builder.build();
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // make the weapon break
        stack.setDamage(stack.getDamage() + 1);

        // if it's broken
        if (stack.getDamage() >= stack.getMaxDamage()) {
            attacker.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, 1.0F, 1.0F);
            this.attackDamage = this.originalAttackDamage / 2;
        }

        if (target.isAlive() && !target.isFireImmune()) {
            if (target.isWet()) {
                target.playSound(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, 1.0F, 1.0F);
            }
            else {
                target.setFireTicks(MCAUtils.getTicksBySeconds(3));
                target.setOnFire(true);
            }
        }
        else target.playSound(SoundEvents.BLOCK_SIGN_WAXED_INTERACT_FAIL, 1.0F, 1.0F);

        return true;
    }

    public void onRepaired(ItemStack stack, World world, PlayerEntity player, int amountRepaired, RepairSource source) {
        String origin = source == RepairSource.ANVIL ? "in anvil" : "via Mending";
        player.sendMessage(Text.literal("Ruby Scepter repaired " + origin + "! (+" + amountRepaired + ")"), true);
        world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.PLAYERS, 1.0F, 1.0F);

        if (stack.getDamage() < (stack.getMaxDamage() / 2)) {
            this.attackDamage = (this.originalAttackDamage + this.toolMaterialAttackDamage) / 2;
        }
        else if (stack.getDamage() <= 0) {
            this.attackDamage = (this.originalAttackDamage + this.toolMaterialAttackDamage) / 1.25F;
        }
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(MCAItems.RUBY);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.mcadditions.ruby_scepter"));

        super.appendTooltip(stack, world, tooltip, context);
    }
}
