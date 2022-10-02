package gardensofthedead.common.blocks;

import gardensofthedead.common.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.function.ToIntFunction;

public class SoulcreepBlock extends GrowingPlantHeadBlock {

    public static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 12, 12);
    public static final BooleanProperty GLOWING = BooleanProperty.create("glowing");

    public SoulcreepBlock(Properties properties) {
        super(properties, Direction.UP, SHAPE, false, 0.05);
        registerDefaultState(defaultBlockState().setValue(GLOWING, false));
    }

    public static ToIntFunction<BlockState> emission(int light) {
        return state -> state.getValue(GLOWING) ? light : 0;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, GLOWING);
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.relative(growthDirection.getOpposite());
        BlockState supportingState = level.getBlockState(blockpos);
        if (!super.canSurvive(state, level, pos)) {
            return false;
        }
        if (!supportingState.is(getBodyBlock()) && !supportingState.is(getHeadBlock())) {
            return true;
        }
        return !supportingState.hasProperty(GLOWING) || !supportingState.getValue(GLOWING);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState replacedState = context.getLevel().getBlockState(context.getClickedPos().relative(growthDirection));

        if (!replacedState.is(getHeadBlock()) && !replacedState.is(getBodyBlock())) {
            int height = getHeightOrMax(context.getLevel(), context.getClickedPos());
            BlockState supportingState = context.getLevel().getBlockState(context.getClickedPos().below(height));

            if (supportingState.is(getBodyBlock())) {
                return defaultBlockState().setValue(AGE, MAX_AGE);
            }

            int minAge = height;
            if (!supportingState.is(Blocks.SOUL_SAND)) { // TODO replace with tag
                // grow 4 blocks tall max on non-soul sand
                minAge = Math.max(height, MAX_AGE - 3);
            }

            int age = minAge + context.getLevel().getRandom().nextInt(MAX_AGE - minAge + 1);
            return defaultBlockState().setValue(AGE, age);
        }

        return getBodyBlock().defaultBlockState();
    }

    public int getHeightOrMax(Level level, BlockPos top) {
        for (int i = 0; i < MAX_AGE; i++) {
            BlockState state = level.getBlockState(top.below(i));
            if (!state.is(getBodyBlock()) && !state.is(getHeadBlock())) {
                return i;
            }
        }
        return MAX_AGE;
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource randomSource) {
        return 0;
    }

    @Override
    protected boolean canGrowInto(BlockState state) {
        return state.isAir();
    }

    public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClientSide) {
        return !state.getValue(GLOWING);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource randomSource, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(GLOWING, true), UPDATE_CLIENTS);
    }

    @Override
    protected Block getBodyBlock() {
        return ModBlocks.SOULCREEP_PLANT.get();
    }
}
