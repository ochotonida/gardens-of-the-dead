package gardensofthedead.common.block;

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

    public static final VoxelShape TOP_SHAPE_UP = Block.box(1, 0, 1, 15, 8, 15);
    public static final VoxelShape TOP_SHAPE_DOWN = Block.box(1, 8, 1, 15, 16, 15);

    public static final int MAX_LENGTH_LONG = 8;
    public static final int MAX_LENGTH_SHORT = 3;

    public SoulSporeBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(TOP, true).setValue(GROWING, true));
    }

    public static int getMaxLength(BlockState supportingBlock) {
        if (supportingBlock.is(Blocks.SOUL_SAND)) {
            return MAX_LENGTH_LONG;
        } else {
            return MAX_LENGTH_SHORT;
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TOP, GROWING);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos updatedPos) {
        if (direction == state.getValue(DIRECTION)) {
            if (newState.is(this) || newState.is(ModBlocks.GLOWING_SOUL_SPORE.get())) {
                if (state.getValue(TOP) && newState.getValue(DIRECTION) == state.getValue(DIRECTION)) {
                    // update shape when plant is placed/grown above
                    return state.setValue(TOP, false).setValue(GROWING, true);
                }
            }
        }

        return super.updateShape(state, direction, newState, level, pos, updatedPos);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (!state.hasProperty(TOP) || !state.getValue(TOP)) {
            return super.getShape(state, level, pos, context);
        } else if (state.hasProperty(DIRECTION) && state.getValue(DIRECTION) == Direction.UP) {
            return TOP_SHAPE_UP;
        } else {
            return TOP_SHAPE_DOWN;
        }
    }

    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        Direction direction = state.getValue(DIRECTION);
        if (randomSource.nextFloat() < 0.1F && level.isEmptyBlock(pos.relative(direction))) {
            int length = 1;
            while (level.getBlockState(pos.relative(direction.getOpposite(), length)).is(this)) {
                length++;
            }

            BlockState supportingBlock = level.getBlockState(pos.relative(direction.getOpposite(), length));

            int maxLength = getMaxLength(supportingBlock);

            if (state.getValue(TOP) && (length >= maxLength || randomSource.nextInt(maxLength - length + 1) == 0)) {
                if (length > 1 && randomSource.nextInt(8) == 0) {
                    BlockState glowingSoulSpore = ModBlocks.GLOWING_SOUL_SPORE.get()
                            .defaultBlockState()
                            .setValue(DIRECTION, direction);
                    level.setBlockAndUpdate(pos, glowingSoulSpore);
                } else {
                    level.setBlock(pos, state.setValue(GROWING, false), Block.UPDATE_NONE);
                }
            } else {
                if (ForgeHooks.onCropsGrowPre(level, pos, state, true)) {
                    BlockState grownState = defaultBlockState().setValue(DIRECTION, direction);
                    level.setBlockAndUpdate(pos.relative(direction), grownState);
                    ForgeHooks.onCropsGrowPost(level, pos.relative(direction), grownState);
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
