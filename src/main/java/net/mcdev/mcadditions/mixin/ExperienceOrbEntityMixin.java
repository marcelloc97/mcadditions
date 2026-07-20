package net.mcdev.mcadditions.mixin;

import net.mcdev.mcadditions.item.custom.RubyScepter;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

@Mixin(ExperienceOrbEntity.class)
public class ExperienceOrbEntityMixin {

	@Unique
	private int mca$repairDepth = 0;

	@Unique
	private final Map<ItemStack, Integer> mca$damageBefore = new HashMap<>();

	@Inject(method = "repairPlayerGears", at = @At("HEAD"))
	private void mca$captureBefore(PlayerEntity player, int amount, CallbackInfoReturnable<Integer> cir) {
		if (mca$repairDepth == 0) {
			mca$damageBefore.clear();

			for (ItemStack stack : player.getInventory().main) {
				if (stack.getItem() instanceof RubyScepter) {
					mca$damageBefore.put(stack, stack.getDamage());
				}
			}
		}

		mca$repairDepth++;
	}

	@Inject(method = "repairPlayerGears", at = @At("RETURN"))
	private void mca$checkAfter(PlayerEntity player, int amount, CallbackInfoReturnable<Integer> cir) {
		mca$repairDepth--;

		if (mca$repairDepth == 0) {
			for (Map.Entry<ItemStack, Integer> entry : mca$damageBefore.entrySet()) {
				ItemStack stack = entry.getKey();
				int before = entry.getValue();

				if (stack.getDamage() < before) {
					((RubyScepter) stack.getItem()).onRepaired(
						stack, player.getWorld(), player,
						before - stack.getDamage(), RubyScepter.RepairSource.MENDING
					);
				}
			}
		}
	}
}
