package gardensofthedead.common.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ModBlockProperties {

    public static BlockBehaviour.Properties SOUL_SPORE = BlockBehaviour.Properties.of(Material.PLANT)
            .color(MaterialColor.COLOR_BROWN)
            .sound(SoundType.WEEPING_VINES)
            .noCollission()
            .instabreak();

    public static BlockBehaviour.Properties GLOWING_SOUL_SPORE = copy(SOUL_SPORE)
            .lightLevel(state -> 7);

    public static BlockBehaviour.Properties copy(BlockBehaviour.Properties properties) {
        return BlockBehaviour.Properties.copy(new BlockBehaviour(properties) {
            @Override
            public Item asItem() {
                throw new UnsupportedOperationException();
            }

            @Override
            protected Block asBlock() {
                throw new UnsupportedOperationException();
            }
        });
    }
}
