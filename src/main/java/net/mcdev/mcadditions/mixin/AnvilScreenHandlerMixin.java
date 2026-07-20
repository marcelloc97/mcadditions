package net.mcdev.mcadditions.mixin;

import net.mcdev.mcadditions.item.custom.RubyScepter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {
	@Inject(method = "onTakeOutput", at = @At("HEAD"))
	private void mca$onTakeOutput(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
		if (stack.getItem() instanceof RubyScepter scepter) {
			ItemStack original = ((ForgingScreenHandler)(Object) this).getSlot(0).getStack();
			int before = original.getDamage();
			int after = stack.getDamage();

			if (after < before) {
				scepter.onRepaired(
					stack, player.getWorld(), player,
					before - after, RubyScepter.RepairSource.ANVIL
				);
			}
		}
	}
}
