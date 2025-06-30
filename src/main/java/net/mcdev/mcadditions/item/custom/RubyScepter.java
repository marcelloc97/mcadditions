package net.mcdev.mcadditions.item.custom;

import net.mcdev.mcadditions.item.MCAItems;
import net.mcdev.mcadditions.util.MCAUtils;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class RubyScepter extends Item {
    // opções para funcionalidade
    // 1 - Comandar Aldeões
    // 2 - Soltar magia (projetil)
    // 3 - Transmutar itens/blocos
    // 4 - Criar uma espécie de Ice Rod do Terraria
    // 5 - Transmutar entidades ou curar Zombie Villagers e Witches

    public RubyScepter(Settings settings) {
        super(settings);

        settings.rarity(Rarity.EPIC);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        user.setCurrentHand(hand);
        user.addStatusEffect(
            new StatusEffectInstance(
                StatusEffects.FIRE_RESISTANCE, MCAUtils.getTicksBySeconds(30),
                0, false, false, true
            ),
            null
        );

        itemStack.damage(
            1,
            user,
            playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand())
        );

        return TypedActionResult.success(itemStack);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(MCAItems.RUBY);
    }
}
