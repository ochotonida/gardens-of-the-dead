package gardensofthedead.block;

import gardensofthedead.network.NetworkHandler;
import gardensofthedead.network.WhistleEffectPacket;
import gardensofthedead.platform.PlatformServices;
import gardensofthedead.registry.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class WhistlecaneBlock extends Block implements BonemealableBlock {

    public static final BooleanProperty GROWING = BooleanProperty.create("growing");
    public static final VoxelShape SHAPE = Block.box(5, 0, 5, 11, 16, 11);
    public static final double GROW_CHANCE = 0.1;
    public static final double WHISTLE_CHANCE = 1 / 40D;
    public static final int MAX_HEIGHT = 6;

    public WhistlecaneBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(GROWING, true));
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
        return stateBelow.is(this)
                || stateBelow.is(BlockTags.NYLIUM)
                || stateBelow.is(Blocks.SOUL_SAND)
                || stateBelow.is(Blocks.SOUL_SOIL);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        int heightAbove = this.getHeightAboveUpToMax(level, pos);
        int heightBelow = this.getHeightBelowUpToMax(level, pos);
        BlockState topState = level.getBlockState(pos.above(heightAbove));
        return heightAbove + heightBelow + 1 < MAX_HEIGHT && topState.getValue(WhistlecaneBlock.GROWING);
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
        if (PlatformServices.platformHelper.isSword(player.getMainHandItem())) {
            return 1;
        }
        return super.getDestroyProgress(state, player, level, pos);
    }

    protected void growCane(Level level, BlockPos pos, RandomSource randomSource, int currentHeight) {
        boolean continueGrowing = currentHeight == 0 || !(randomSource.nextFloat() < 1F / (MAX_HEIGHT - currentHeight));
        BlockState newState = defaultBlockState().setValue(WhistlecaneBlock.GROWING, continueGrowing);
        level.setBlock(pos.above(), newState, Block.UPDATE_ALL);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos abovePos = context.getClickedPos().above();
        BlockState aboveState = context.getLevel().getBlockState(abovePos);
        return defaultBlockState().setValue(GROWING, !aboveState.is(this));
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

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GROWING);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos updatedPos) {
        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }
        if (direction == Direction.UP) {
            return defaultBlockState().setValue(GROWING, !newState.is(this));
        }

        return super.updateShape(state, direction, newState, level, pos, updatedPos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        if (state.getValue(GROWING)) {
            int height = getHeightBelowUpToMax(level, pos) + 1;
            if (level.isEmptyBlock(pos.above()) && height < MAX_HEIGHT && randomSource.nextFloat() < GROW_CHANCE) {
                growCane(level, pos, randomSource, height);
            }
        } else if (randomSource.nextFloat() < WHISTLE_CHANCE && level.isEmptyBlock(pos.above())) {
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
        sendWhistlePacket(level, pos);
    }

    private void sendWhistlePacket(ServerLevel level, BlockPos pos) {
        NetworkHandler.sendToTrackingPlayers(level, pos, new WhistleEffectPacket(pos, level));
    }
}
