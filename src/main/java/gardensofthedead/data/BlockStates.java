package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, GardensOfTheDead.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
}
