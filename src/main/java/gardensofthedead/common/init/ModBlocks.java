package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.blocks.StandingSignBlock;
import gardensofthedead.common.blocks.WallSignBlock;
import gardensofthedead.common.blocks.*;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registry.BLOCK_REGISTRY, GardensOfTheDead.MODID);

    public static final RegistryObject<SoulSporeBlock> SOUL_SPORE = BLOCKS.register("soul_spore", () -> new SoulSporeBlock(ModBlockProperties.SOUL_SPORE));
    public static final RegistryObject<SoulSporeBaseBlock> GLOWING_SOUL_SPORE = BLOCKS.register("glowing_soul_spore", () -> new SoulSporeBaseBlock(ModBlockProperties.GLOWING_SOUL_SPORE));

    public static final RegistryObject<Block> SOULBLIGHT_FUNGUS = BLOCKS.register("soulblight_fungus", () -> new SoulblightFungusBlock(ModBlockProperties.SOULBLIGHT_FUNGUS));
    public static final RegistryObject<Block> SOULBLIGHT_SPROUTS = BLOCKS.register("soulblight_sprouts", () -> new SoulblightSproutsBlock(ModBlockProperties.SOULBLIGHT_SPROUTS));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SOULBLIGHT_STEM = strippedSoulblightStem("stripped_soulblight_stem");
    public static final RegistryObject<RotatedPillarBlock> SOULBLIGHT_STEM = soulblightStem("soulblight_stem", STRIPPED_SOULBLIGHT_STEM);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SOULBLIGHT_HYPHAE = strippedSoulblightStem("stripped_soulblight_hyphae");
    public static final RegistryObject<RotatedPillarBlock> SOULBLIGHT_HYPHAE = soulblightStem("soulblight_hyphae", STRIPPED_SOULBLIGHT_HYPHAE);
    public static final RegistryObject<Block> BLIGHTWART_BLOCK = BLOCKS.register("blightwart_block", () -> new Block(ModBlockProperties.BLIGHTWART_BLOCK));

    public static final RegistryObject<Block> BLISTERCROWN = BLOCKS.register("blistercrown", () -> new BlistercrownBlock(ModBlockProperties.BLISTERCROWN));
    public static final RegistryObject<Block> TALL_BLISTERCROWN = BLOCKS.register("tall_blistercrown", () -> new TallBlistercrownBlock(ModBlockProperties.BLISTERCROWN));

    public static final RegistryObject<FlowerPotBlock> POTTED_SOUL_SPORE = flowerPot(SOUL_SPORE);
    public static final RegistryObject<FlowerPotBlock> POTTED_GLOWING_SOUL_SPORE = flowerPot(GLOWING_SOUL_SPORE, ModBlockProperties.POTTED_GLOWING_SOUL_SPORE);
    public static final RegistryObject<FlowerPotBlock> POTTED_SOULBLIGHT_FUNGUS = flowerPot(SOULBLIGHT_FUNGUS);
    public static final RegistryObject<FlowerPotBlock> POTTED_SOULBLIGHT_SPROUTS = flowerPot(SOULBLIGHT_SPROUTS);
    public static final RegistryObject<FlowerPotBlock> POTTED_BLISTERCROWN = flowerPot(BLISTERCROWN);

    public static final RegistryObject<Block> SOULBLIGHT_PLANKS = BLOCKS.register("soulblight_planks", () -> new Block(ModBlockProperties.SOULBLIGHT_PLANKS));
    public static final RegistryObject<SlabBlock> SOULBLIGHT_SLAB = BLOCKS.register("soulblight_slab", () -> new SlabBlock(ModBlockProperties.SOULBLIGHT_PLANKS));
    public static final RegistryObject<PressurePlateBlock> SOULBLIGHT_PRESSURE_PLATE = BLOCKS.register("soulblight_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlockProperties.SOULBLIGHT_BUTTONS));
    public static final RegistryObject<FenceBlock> SOULBLIGHT_FENCE = BLOCKS.register("soulblight_fence", () -> new FenceBlock(ModBlockProperties.SOULBLIGHT_PLANKS));
    public static final RegistryObject<TrapDoorBlock> SOULBLIGHT_TRAPDOOR = BLOCKS.register("soulblight_trapdoor", () -> new TrapDoorBlock(ModBlockProperties.SOULBLIGHT_TRAPDOOR));
    public static final RegistryObject<FenceGateBlock> SOULBLIGHT_FENCE_GATE = BLOCKS.register("soulblight_fence_gate", () -> new FenceGateBlock(ModBlockProperties.SOULBLIGHT_PLANKS));
    public static final RegistryObject<StairBlock> SOULBLIGHT_STAIRS = BLOCKS.register("soulblight_stairs", () -> new StairBlock(() -> SOULBLIGHT_PLANKS.get().defaultBlockState(), ModBlockProperties.SOULBLIGHT_PLANKS));
    public static final RegistryObject<ButtonBlock> SOULBLIGHT_BUTTON = BLOCKS.register("soulblight_button", () -> new WoodButtonBlock(ModBlockProperties.SOULBLIGHT_BUTTONS));
    public static final RegistryObject<DoorBlock> SOULBLIGHT_DOOR = BLOCKS.register("soulblight_door", () -> new DoorBlock(ModBlockProperties.SOULBLIGHT_DOOR));
    public static final RegistryObject<StandingSignBlock> SOULBLIGHT_SIGN = BLOCKS.register("soulblight_sign", () -> new StandingSignBlock(ModBlockProperties.SOULBLIGHT_SIGN, ModWoodTypes.SOULBLIGHT));
    public static final RegistryObject<WallSignBlock> SOULBLIGHT_WALL_SIGN = BLOCKS.register("soulblight_wall_sign", () -> new WallSignBlock(ModBlockProperties.SOULBLIGHT_WALL_SIGN, ModWoodTypes.SOULBLIGHT));

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
