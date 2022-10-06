package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.blocks.SoulBlightFungusBlock;
import gardensofthedead.common.blocks.SoulSporeBaseBlock;
import gardensofthedead.common.blocks.SoulSporeBlock;
import gardensofthedead.common.blocks.StrippableLogBlock;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registry.BLOCK_REGISTRY, GardensOfTheDead.MODID);

    public static final RegistryObject<SoulSporeBlock> SOUL_SPORE = BLOCKS.register("soul_spore", () -> new SoulSporeBlock(ModBlockProperties.SOUL_SPORE));
    public static final RegistryObject<SoulSporeBaseBlock> GLOWING_SOUL_SPORE = BLOCKS.register("glowing_soul_spore", () -> new SoulSporeBaseBlock(ModBlockProperties.GLOWING_SOUL_SPORE));

    public static final RegistryObject<Block> SOULBLIGHT_FUNGUS = BLOCKS.register("soulblight_fungus", () -> new SoulBlightFungusBlock(ModBlockProperties.SOULBLIGHT_FUNGUS));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SOULBLIGHT_STEM = strippedSoulblightStem("stripped_soulblight_stem");
    public static final RegistryObject<RotatedPillarBlock> SOULBLIGHT_STEM = soulblightStem("soulblight_stem", STRIPPED_SOULBLIGHT_STEM);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SOULBLIGHT_HYPHAE = strippedSoulblightStem("stripped_soulblight_hyphae");
    public static final RegistryObject<RotatedPillarBlock> SOULBLIGHT_HYPHAE = soulblightStem("soulblight_hyphae", STRIPPED_SOULBLIGHT_HYPHAE);
    public static final RegistryObject<Block> BLIGHTWART_BLOCK = BLOCKS.register("blightwart_block", () -> new Block(ModBlockProperties.BLIGHTWART_BLOCK));

    public static final RegistryObject<FlowerPotBlock> POTTED_SOUL_SPORE = flowerPot(SOUL_SPORE);
    public static final RegistryObject<FlowerPotBlock> POTTED_GLOWING_SOUL_SPORE = flowerPot(GLOWING_SOUL_SPORE, ModBlockProperties.POTTED_GLOWING_SOUL_SPORE);
    public static final RegistryObject<FlowerPotBlock> POTTED_SOULBLIGHT_FUNGUS = flowerPot(SOULBLIGHT_FUNGUS);

    private static RegistryObject<RotatedPillarBlock> soulblightStem(String name, Supplier<? extends Block> strippedLogBlock) {
        return BLOCKS.register(name, () -> new StrippableLogBlock(ModBlockProperties.SOULBLIGHT_STEM, strippedLogBlock));
    }

    private static RegistryObject<RotatedPillarBlock> strippedSoulblightStem(String name) {
        return BLOCKS.register(name, () -> new RotatedPillarBlock(ModBlockProperties.SOULBLIGHT_STEM));
    }

    private static RegistryObject<FlowerPotBlock> flowerPot(RegistryObject<? extends Block> plant) {
        return flowerPot(plant, ModBlockProperties.pottedPlant());
    }

    private static RegistryObject<FlowerPotBlock> flowerPot(RegistryObject<? extends Block> plant, BlockBehaviour.Properties properties) {
        RegistryObject<FlowerPotBlock> result = BLOCKS.register("potted_" + plant.getId().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, plant, properties));
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(plant.getId(), result);
        return result;
    }
}
