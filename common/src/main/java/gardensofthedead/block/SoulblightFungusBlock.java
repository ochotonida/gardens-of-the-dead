package gardensofthedead.block;

import gardensofthedead.GardensOfTheDead;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SoulblightFungusBlock extends FungusBlock {

    protected static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 13, 12);
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOULBLIGHT_FUNGUS_PLANTED = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, GardensOfTheDead.id("soulblight_fungus_planted")
    );
    public static final Block REQUIRED_BLOCK = Blocks.SOUL_SOIL;

    public SoulblightFungusBlock(BlockBehaviour.Properties properties) {
        super(properties, SOULBLIGHT_FUNGUS_PLANTED, REQUIRED_BLOCK);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.WART_BLOCKS)
                || state.is(Blocks.SOUL_SAND)
                || super.mayPlaceOn(state, level, pos);
    }
}
