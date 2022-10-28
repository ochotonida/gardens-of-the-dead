package gardensofthedead.block;

import dev.architectury.injectables.annotations.ExpectPlatform;
import gardensofthedead.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public abstract class WhistlecaneBaseBlock extends Block implements BonemealableBlock {

    protected static final VoxelShape SHAPE = Block.box(5, 0, 5, 11, 16, 11);
    public static final int MAX_HEIGHT = 6;

    public WhistlecaneBaseBlock(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Vec3 offset = state.getOffset(level, pos);
        return SHAPE.move(offset.x, offset.y, offset.z);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isCollisionShapeFullBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState stateBelow = level.getBlockState(pos.below());
        return stateBelow.is(getBodyBlock())
                || stateBelow.is(getHeadBlock())
                || stateBelow.is(BlockTags.NYLIUM)
                || stateBelow.is(Blocks.SOUL_SAND)
                || stateBelow.is(Blocks.SOUL_SOIL);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
        int heightAbove = this.getHeightAboveUpToMax(level, pos);
        int heightBelow = this.getHeightBelowUpToMax(level, pos);
        BlockState topState = level.getBlockState(pos.above(heightAbove));
        return heightAbove + heightBelow + 1 < MAX_HEIGHT
                && topState.is(getHeadBlock())
                && topState.getValue(WhistlecaneBlock.GROWING);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource randomSource, BlockPos pos, BlockState state) {
        int heightAbove = getHeightAboveUpToMax(level, pos);
        int heightBelow = getHeightBelowUpToMax(level, pos);
        int height = heightAbove + heightBelow + 1;
        int extraHeight = 1 + randomSource.nextInt(2);

        for (int blocksGrown = 0; blocksGrown < extraHeight; ++blocksGrown) {
            BlockPos grownPos = pos.above(heightAbove);
            BlockState grownState = level.getBlockState(grownPos);
            if (height >= MAX_HEIGHT || !grownState.getValue(WhistlecaneBlock.GROWING) || !level.isEmptyBlock(grownPos.above())) {
                return;
            }

            growCane(level, grownPos, randomSource, height);
            ++heightAbove;
            ++height;
        }

    }

    @Override
    @SuppressWarnings("deprecation")
    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        return canInstabreak(player) ? 1 : super.getDestroyProgress(state, player, level, pos);
    }

    @ExpectPlatform
    public static boolean canInstabreak(Player player) {
        throw new AssertionError();
    }

    protected void growCane(Level level, BlockPos pos, RandomSource randomSource, int currentHeight) {
        boolean continueGrowing = (currentHeight < MAX_HEIGHT - 5 || !(randomSource.nextFloat() < (MAX_HEIGHT - currentHeight) / 4F)) && currentHeight != MAX_HEIGHT - 1;
        level.setBlock(pos.above(), getHeadBlock().defaultBlockState().setValue(WhistlecaneBlock.GROWING, continueGrowing), Block.UPDATE_ALL);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState above = context.getLevel().getBlockState(context.getClickedPos().above());
        return !above.is(getHeadBlock()) && !above.is(getBodyBlock()) ? getHeadBlock().defaultBlockState() : getBodyBlock().defaultBlockState();
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos updatedPos) {
        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }

        return super.updateShape(state, direction, newState, level, pos, updatedPos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    protected int getHeightAboveUpToMax(BlockGetter level, BlockPos pos) {
        int height = 0;
        while (height < MAX_HEIGHT && level.getBlockState(pos.above(height + 1)).is(this)) {
            ++height;
        }

        return height;
    }

    protected int getHeightBelowUpToMax(BlockGetter level, BlockPos pos) {
        int height = 0;
        while (height < MAX_HEIGHT && level.getBlockState(pos.below(height + 1)).is(this)) {
            ++height;
        }

        return height;
    }

    protected Block getHeadBlock() {
        return ModBlocks.WHISTLECANE.get();
    }

    protected Block getBodyBlock() {
        return ModBlocks.WHISTLECANE_PLANT.get();
    }
}
