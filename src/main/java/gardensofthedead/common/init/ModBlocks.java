package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.blocks.SoulcreepBlock;
import gardensofthedead.common.blocks.SoulcreepPlantBlock;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registry.BLOCK_REGISTRY, GardensOfTheDead.MODID);

    public static final RegistryObject<SoulcreepPlantBlock> SOULCREEP_PLANT = BLOCKS.register("soulcreep_plant", () -> new SoulcreepPlantBlock(ModBlockProperties.SOULCREEP_PLANT));
    public static final RegistryObject<SoulcreepBlock> SOULCREEP = BLOCKS.register("soulcreep", () -> new SoulcreepBlock(ModBlockProperties.SOULCREEP));

}
