package net.mcdev.mcadditions;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.mcdev.mcadditions.block.MCABlocks;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;

public class MCAdditionsClient implements ClientModInitializer {
//    private static KeyBinding openScreenKeyBinding;

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(MCABlocks.RUBY_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MCABlocks.RUBY_TRAPDOOR, RenderLayer.getCutout());


    }
}
