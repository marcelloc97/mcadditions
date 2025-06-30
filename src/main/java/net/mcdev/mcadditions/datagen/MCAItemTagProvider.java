package net.mcdev.mcadditions.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.mcdev.mcadditions.item.MCAItems;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class MCAItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public MCAItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // Tools
        getOrCreateTagBuilder(ItemTags.SWORDS)
            .add(MCAItems.RUBY_SWORD);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
            .add(MCAItems.RUBY_PICKAXE);

        getOrCreateTagBuilder(ItemTags.AXES)
            .add(MCAItems.RUBY_AXE);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
            .add(MCAItems.RUBY_SHOVEL);

        getOrCreateTagBuilder(ItemTags.HOES)
            .add(MCAItems.RUBY_HOE);

        // Armors
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
            .add(MCAItems.RUBY_HELMET, MCAItems.RUBY_CHESTPLATE, MCAItems.RUBY_LEGGINGS, MCAItems.RUBY_BOOTS);
    }
}
