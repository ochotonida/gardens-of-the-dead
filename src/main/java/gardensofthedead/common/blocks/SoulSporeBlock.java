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

    public static final VoxelShape TOP_SHAPE_UP = Block.box(1, 0, 1, 15, 8, 15);
    public static final VoxelShape TOP_SHAPE_DOWN = Block.box(1, 8, 1, 15, 16, 15);

    public SoulSporeBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(TOP, true).setValue(GROWING, true));
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
        super.createBlockStateDefinition(builder);
        builder.add(TOP, GROWING);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos updatedPos) {
        if (direction == state.getValue(DIRECTION)) {
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
        if (randomSource.nextFloat() < 0.1F && level.isEmptyBlock(pos.above())) {
            Direction direction = state.getValue(DIRECTION);
            int height = 1;
            while (level.getBlockState(pos.relative(direction, height)).is(this)) {
                height++;
            }

            BlockState supportingBlock = level.getBlockState(pos.relative(direction, height));

            int maxHeight = getMaxHeight(supportingBlock);

            if (!state.getValue(TOP) && (height >= maxHeight || randomSource.nextInt(maxHeight - height + 1) == 0)) {
                if (height > 1 && randomSource.nextInt(8) == 0) {
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
                    level.setBlockAndUpdate(pos.above(), grownState);
                    ForgeHooks.onCropsGrowPost(level, pos.above(), grownState);
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
