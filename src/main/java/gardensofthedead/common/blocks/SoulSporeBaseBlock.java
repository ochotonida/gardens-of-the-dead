package gardensofthedead.common.blocks;

import gardensofthedead.common.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import org.jetbrains.annotations.Nullable;

public class SoulSporeBaseBlock extends Block implements IPlantable, IForgeShearable {

    public static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 16, 15);

    public static final DirectionProperty DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;

    public SoulSporeBaseBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(DIRECTION, Direction.UP));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DIRECTION);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(DIRECTION, context.getNearestLookingVerticalDirection().getOpposite());
    }

    @SuppressWarnings("deprecation")
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos updatedPos) {
        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }

        return super.updateShape(state, direction, newState, level, pos, updatedPos);
    }

    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(DIRECTION);
        BlockPos attachedPos = pos.relative(direction.getOpposite());
        BlockState supportingBlock = level.getBlockState(attachedPos);
        return supportingBlock.canSustainPlant(level, pos, Direction.UP, this)
                || supportingBlock.is(Blocks.SOUL_SAND)
                || supportingBlock.is(Blocks.SOUL_SOIL)
                || supportingBlock.is(BlockTags.WART_BLOCKS)
                || supportingBlock.is(ModBlocks.SOUL_SPORE.get()) && supportingBlock.getValue(DIRECTION) == direction;
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos) {
        return PlantType.NETHER;
    }

    @Override
    public BlockState getPlant(BlockGetter world, BlockPos pos) {
        return defaultBlockState();
    }
}
