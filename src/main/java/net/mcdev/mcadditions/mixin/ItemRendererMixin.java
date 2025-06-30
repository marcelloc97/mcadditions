package net.mcdev.mcadditions.mixin;


import net.mcdev.mcadditions.MCAdditions;
import net.mcdev.mcadditions.item.MCAItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useRubyScepterModel(
        BakedModel value, ItemStack stack, ModelTransformationMode renderMode,
        boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay
    ) {
        if (stack.isOf(MCAItems.RUBY_SCEPTER) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(
                new ModelIdentifier(MCAdditions.MOD_ID, "ruby_scepter_3d", "inventory")
            );
        }

        return value;
    }
}