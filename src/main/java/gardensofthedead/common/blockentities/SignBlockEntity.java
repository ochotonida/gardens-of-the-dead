package gardensofthedead.common.blockentities;

import gardensofthedead.common.init.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class SignBlockEntity extends net.minecraft.world.level.block.entity.SignBlockEntity {

    public SignBlockEntity(BlockPos pos, BlockState blockState) {
        super(pos, blockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntityTypes.SIGN.get();
    }
}
