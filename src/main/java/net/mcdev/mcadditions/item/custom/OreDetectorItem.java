package net.mcdev.mcadditions.item.custom;

import net.mcdev.mcadditions.block.MCABlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OreDetectorItem extends Item {
    protected int detectionRange = 32;

    public OreDetectorItem(Settings settings) {
        super(settings);

        settings.rarity(Rarity.RARE);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            BlockPos positionUsed = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;

            assert player != null;

            for (int y = 0; y <= positionUsed.getY() + detectionRange; y++) {
                BlockState stateY = context.getWorld().getBlockState(positionUsed.down(y));
                BlockPos nextPosY = positionUsed.down(y + 1);

                for (int x = 0; x <= nextPosY.getX() + (detectionRange - 1); x++) {
                    BlockState stateXEast = context.getWorld().getBlockState(positionUsed.east());
                    BlockState stateXWest = context.getWorld().getBlockState(positionUsed.east());

                    if (isValuableBlock(stateXEast)) {
                        outputValuableCoordinates(positionUsed.east(x), player, stateXEast.getBlock());
                        foundBlock = true;

                        break;
                    }
                    else if (isValuableBlock(stateXWest)) {
                        outputValuableCoordinates(positionUsed.west(x), player, stateXWest.getBlock());
                        foundBlock = true;

                        break;
                    }
                }

                if (isValuableBlock(stateY)) {
                    outputValuableCoordinates(positionUsed.down(y), player, stateY.getBlock());
                    foundBlock = true;

                    break;
                }
            }

            if (!foundBlock) {
                player.sendMessage(Text.literal("No valuables found."));
            }
        }

        assert context.getPlayer() != null;

        context.getStack().damage(
            1, context.getPlayer(),
            playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand())
        );

        return ActionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos pos, PlayerEntity player, Block block) {
        String message = (
            "Found " + block.asItem().getName().getString() + " at " +
                "(" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ")."
        );

        player.sendMessage(Text.literal(message), false);
    }

    private boolean isValuableBlock(BlockState state) {
        return (
            state.getBlock() == MCABlocks.RUBY_ORE ||
            state.getBlock() == MCABlocks.DEEPSLATE_RUBY_ORE ||
            state.getBlock() == Blocks.COAL_ORE ||
            state.getBlock() == Blocks.DEEPSLATE_COAL_ORE ||
            state.getBlock() == Blocks.COPPER_ORE ||
            state.getBlock() == Blocks.DEEPSLATE_COPPER_ORE ||
            state.getBlock() == Blocks.IRON_ORE ||
            state.getBlock() == Blocks.DEEPSLATE_IRON_ORE ||
            state.getBlock() == Blocks.GOLD_ORE ||
            state.getBlock() == Blocks.DEEPSLATE_GOLD_ORE ||
            state.getBlock() == Blocks.DIAMOND_ORE ||
            state.getBlock() == Blocks.DEEPSLATE_DIAMOND_ORE ||
            state.getBlock() == Blocks.EMERALD_ORE ||
            state.getBlock() == Blocks.DEEPSLATE_EMERALD_ORE ||
            state.getBlock() == Blocks.LAPIS_ORE ||
            state.getBlock() == Blocks.DEEPSLATE_LAPIS_ORE ||
            state.getBlock() == Blocks.REDSTONE_ORE ||
            state.getBlock() == Blocks.DEEPSLATE_REDSTONE_ORE ||
            state.getBlock() == Blocks.NETHER_GOLD_ORE ||
            state.getBlock() == Blocks.NETHER_QUARTZ_ORE
        );
        //state.isIn(MCATags.Blocks.ORE_DETECTOR_DETECTABLE_BLOCKS);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.mcadditions.ore_detector.line1"));
        tooltip.add(Text.translatable("tooltip.mcadditions.ore_detector.line2"));

        super.appendTooltip(stack, world, tooltip, context);
    }
}
