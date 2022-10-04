package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.blocks.SoulSporeBaseBlock;
import gardensofthedead.common.blocks.SoulSporeBlock;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registry.BLOCK_REGISTRY, GardensOfTheDead.MODID);

    public static final RegistryObject<SoulSporeBlock> SOUL_SPORE = BLOCKS.register("soul_spore", () -> new SoulSporeBlock(ModBlockProperties.SOUL_SPORE));
    public static final RegistryObject<SoulSporeBaseBlock> GLOWING_SOUL_SPORE = BLOCKS.register("glowing_soul_spore", () -> new SoulSporeBaseBlock(ModBlockProperties.GLOWING_SOUL_SPORE));

}
