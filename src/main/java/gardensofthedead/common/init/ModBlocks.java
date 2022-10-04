package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.blocks.SoulSporeBaseBlock;
import gardensofthedead.common.blocks.SoulSporeBlock;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registry.BLOCK_REGISTRY, GardensOfTheDead.MODID);

    public static final RegistryObject<SoulSporeBlock> SOUL_SPORE = BLOCKS.register("soul_spore", () -> new SoulSporeBlock(ModBlockProperties.SOUL_SPORE));
    public static final RegistryObject<SoulSporeBaseBlock> GLOWING_SOUL_SPORE = BLOCKS.register("glowing_soul_spore", () -> new SoulSporeBaseBlock(ModBlockProperties.GLOWING_SOUL_SPORE));

    public static final RegistryObject<FlowerPotBlock> POTTED_SOUL_SPORE = flowerPot(SOUL_SPORE, ModBlockProperties.pottedPlant());
    public static final RegistryObject<FlowerPotBlock> POTTED_GLOWING_SOUL_SPORE = flowerPot(GLOWING_SOUL_SPORE, ModBlockProperties.POTTED_GLOWING_SOUL_SPORE);

    private static RegistryObject<FlowerPotBlock> flowerPot(RegistryObject<? extends Block> plant, BlockBehaviour.Properties properties) {
        RegistryObject<FlowerPotBlock> result = BLOCKS.register("potted_" + plant.getId().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, plant, properties));
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(plant.getId(), result);
        return result;
    }
}
