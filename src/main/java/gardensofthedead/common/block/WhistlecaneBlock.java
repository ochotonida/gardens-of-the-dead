package gardensofthedead.common.block;

import gardensofthedead.common.block.entity.WhistlecaneBlockEntity;
import gardensofthedead.common.init.ModBlockEntityTypes;
import gardensofthedead.common.init.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

public class WhistlecaneBlock extends Block implements IPlantable, BonemealableBlock, EntityBlock {

    protected static final VoxelShape SHAPE = Block.box(5, 0, 5, 11, 16, 11);
    public static final BooleanProperty GROWING = BooleanProperty.create("growing");
    public static final BooleanProperty TOP = BooleanProperty.create("top");
    public static final int MAX_HEIGHT = 6;
    public static final float GROW_CHANCE = 0.1F;

    public WhistlecaneBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(GROWING, true).setValue(TOP, true));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GROWING, TOP);
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Vec3 offset = state.getOffset(level, pos);
        return SHAPE.move(offset.x, offset.y, offset.z);
    }

    public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @SuppressWarnings("deprecation")
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType pathComputationType) {
        return false;
    }

    @SuppressWarnings("deprecation")
    public boolean isCollisionShapeFullBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    @SuppressWarnings("deprecation")
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(TOP);
    }

    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        if (state.getValue(GROWING) && level.isEmptyBlock(pos.above())) {
            int height = getHeightBelowUpToMax(level, pos) + 1;
            if (height < MAX_HEIGHT && ForgeHooks.onCropsGrowPre(level, pos, state, randomSource.nextFloat() < GROW_CHANCE)) {
                growCane(level, pos, randomSource, height);
                ForgeHooks.onCropsGrowPost(level, pos, state);
                return;
            }
        }

        if (randomSource.nextFloat() < 0.1 && level.isEmptyBlock(pos.above())) {
            whistle(state, level, pos, randomSource);
        }
    }

    private void whistle(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        Vec3 offset = state.getOffset(level, pos);
        double x = pos.getX() + offset.x + 0.5;
        double z = pos.getZ() + offset.z + 0.5;
        double y = pos.getY() + offset.y + 1;
        float volume = 1;
        float pitch = randomSource.nextFloat() * 0.3F + 0.85F;
        level.playSound(null, x, y, z, ModSoundEvents.WHISTLECANE_WHISTLE.get(), SoundSource.BLOCKS, volume, pitch);
        level.getBlockEntity(pos, ModBlockEntityTypes.WHISTLECANE.get())
                .ifPresent(WhistlecaneBlockEntity::sendWhistlePacket);
    }

    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState stateBelow = level.getBlockState(pos.below());
        return stateBelow.is(this) || stateBelow.is(BlockTags.NYLIUM) || stateBelow.is(Blocks.SOUL_SAND);
    }

    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos updatedPos) {
        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }

        boolean isTop = !level.getBlockState(pos.above()).is(this);
        if (isTop ^ state.getValue(TOP)) {
            return state.setValue(TOP, isTop);
        }

        return super.updateShape(state, direction, newState, level, pos, updatedPos);
    }

    public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
        int heightAbove = this.getHeightAboveUpToMax(level, pos);
        int heightBelow = this.getHeightBelowUpToMax(level, pos);
        return heightAbove + heightBelow + 1 < MAX_HEIGHT && level.getBlockState(pos.above(heightAbove)).getValue(GROWING);
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource randomSource, BlockPos pos, BlockState state) {
        int heightAbove = getHeightAboveUpToMax(level, pos);
        int heightBelow = getHeightBelowUpToMax(level, pos);
        int height = heightAbove + heightBelow + 1;
        int extraHeight = 1 + randomSource.nextInt(2);

        for (int blocksGrown = 0; blocksGrown < extraHeight; ++blocksGrown) {
            BlockPos grownPos = pos.above(heightAbove);
            BlockState grownState = level.getBlockState(grownPos);
            if (height >= MAX_HEIGHT || !grownState.getValue(GROWING) || !level.isEmptyBlock(grownPos.above())) {
                return;
            }

            growCane(level, grownPos, randomSource, height);
            ++heightAbove;
            ++height;
        }

    }

    @SuppressWarnings("deprecation")
    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        return player.getMainHandItem().canPerformAction(ToolActions.SWORD_DIG) ? 1 : super.getDestroyProgress(state, player, level, pos);
    }

    protected void growCane(Level level, BlockPos pos, RandomSource randomSource, int currentHeight) {
        boolean continueGrowing = (currentHeight < MAX_HEIGHT - 5 || !(randomSource.nextFloat() < 0.25)) && currentHeight != MAX_HEIGHT - 1;
        level.setBlock(pos.above(), defaultBlockState().setValue(GROWING, continueGrowing), Block.UPDATE_ALL);
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

    @Override
    public BlockState getPlant(BlockGetter level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() != this) {
            return defaultBlockState();
        }
        return state;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        if (state.getValue(TOP)) {
            return ModBlockEntityTypes.WHISTLECANE.get().create(pos, state);
        }
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (state.getValue(TOP) && level.isClientSide()) {
            return WhistlecaneBlockEntity::tick;
        }
        return null;
    }
}
