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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

public class SoulSporeBlock extends SoulSporeBaseBlock {

    public static final BooleanProperty TOP = BooleanProperty.create("top");
    public static final BooleanProperty GROWING = BooleanProperty.create("growing");

    public static final VoxelShape TOP_SHAPE = Block.box(1, 0, 1, 15, 8, 15);

    public SoulSporeBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(TOP, true).setValue(GROWING, true));
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
        builder.add(TOP, GROWING);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos updatedPos) {
        if (direction == Direction.UP) {
            if (newState.is(this) || newState.is(ModBlocks.GLOWING_SOUL_SPORE.get())) {
                if (state.getValue(TOP)) {
                    // update shape when plant is placed/grown above
                    return state.setValue(TOP, false).setValue(GROWING, true);
                }
            }
        }

        return super.updateShape(state, direction, newState, level, pos, updatedPos);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.hasProperty(TOP) && state.getValue(TOP) ? TOP_SHAPE : super.getShape(state, level, pos, context);
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

            if (!state.getValue(TOP) && (height >= maxHeight || randomSource.nextInt(maxHeight - height + 1) == 0)) {
                if (height > 1 && randomSource.nextInt(8) == 0) {
                    level.setBlockAndUpdate(pos, ModBlocks.GLOWING_SOUL_SPORE.get().defaultBlockState());
                } else {
                    level.setBlock(pos, state.setValue(GROWING, false), Block.UPDATE_NONE);
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
        return state.getValue(GROWING);
    }
}
