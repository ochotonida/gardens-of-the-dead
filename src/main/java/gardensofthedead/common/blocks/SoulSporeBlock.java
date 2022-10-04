package gardensofthedead.common.blocks;

import gardensofthedead.common.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

public class SoulSporeBlock extends SoulSporeBaseBlock {

    public static final BooleanProperty TOP = BooleanProperty.create("top");
    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 1);

    public static final VoxelShape TOP_SHAPE = Block.box(4, 0, 4, 12, 12, 12);
    public static final VoxelShape PLANT_SHAPE = Block.box(1, 0, 1, 15, 16, 15);

    public SoulSporeBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(TOP, true).setValue(STAGE, 0));
    }

    public static int getMaxHeight(BlockState supportingBlock) {
        if (supportingBlock.is(Blocks.SOUL_SAND)) {
            return 6;
        } else if (supportingBlock.is(Blocks.SOUL_SOIL)) {
            return 3;
        } else {
            return 2;
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TOP, STAGE);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState previousState, LevelAccessor level, BlockPos pos, BlockPos updatesPos) {
        if (direction == Direction.UP && previousState.is(this)) {
            boolean top = !level.getBlockState(updatesPos).is(this)
                    && !level.getBlockState(updatesPos).is(ModBlocks.GLOWING_SOUL_SPORE.get());
            return state.setValue(TOP, top).setValue(STAGE, 0);
        }

        return super.updateShape(state, direction, previousState, level, pos, updatesPos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.hasProperty(TOP) && state.getValue(TOP) ? TOP_SHAPE : PLANT_SHAPE;
    }

    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        if (randomSource.nextFloat() < 0.1F && level.isEmptyBlock(pos.above())) {
            int height = 1;
            while (level.getBlockState(pos.below(height)).is(this)) {
                height++;
            }

            BlockState supportingBlock = level.getBlockState(pos.below(height));
            int maxHeight = getMaxHeight(supportingBlock);

            if (height >= maxHeight || randomSource.nextInt(maxHeight - height + 1) == 0) {
                if (height > 1 && randomSource.nextInt(6) == 0) {
                    level.setBlockAndUpdate(pos, ModBlocks.GLOWING_SOUL_SPORE.get().defaultBlockState());
                } else {
                    level.setBlock(pos, state.setValue(STAGE, 1), Block.UPDATE_NONE);
                }
            } else {
                if (ForgeHooks.onCropsGrowPre(level, pos, state, true)) {
                    level.setBlockAndUpdate(pos.above(), defaultBlockState());
                    ForgeHooks.onCropsGrowPost(level, pos.above(), defaultBlockState());
                    level.setBlock(pos, state.setValue(TOP, false), 4);
                }
            }
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(TOP) && state.getValue(STAGE) == 0;
    }
}
