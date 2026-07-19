package net.mcdev.mcadditions.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EssenceExtractor extends Item {
    public EssenceExtractor(Settings settings) {
        super(settings);

        settings.rarity(Rarity.EPIC);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
//        NbtCompound nbt = stack.getOrCreateNbt();
//
//        if (!nbt.contains("full") && !nbt.getBoolean("full")) {
//            if (!nbt.contains("entity_type") && !nbt.contains("full")) {
//                nbt.putString("entity_type", entity.getType().getName().getString());
//                nbt.putBoolean("full", true);
//            }
//
//            entity.damage(entity.getDamageSources().generic(), 1);
//
//            return ActionResult.SUCCESS;
//        }

        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.mcadditions.essence_extractor"));
    }

    //    private void writeItemNbt(NbtCompound nbt) {
//
//    }
}
