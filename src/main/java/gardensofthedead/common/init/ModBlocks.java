package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.block.StandingSignBlock;
import gardensofthedead.common.block.WallSignBlock;
import gardensofthedead.common.block.*;
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
    public static final RegistryObject<Block> BLIGHTWART_BLOCK = block("blightwart_block", ModBlockProperties.BLIGHTWART_BLOCK);

    public static final RegistryObject<Block> BLISTERCROWN = BLOCKS.register("blistercrown", () -> new BlistercrownBlock(ModBlockProperties.BLISTERCROWN));
    public static final RegistryObject<DoublePlantBlock> TALL_BLISTERCROWN = BLOCKS.register("tall_blistercrown", () -> new TallBlistercrownBlock(ModBlockProperties.BLISTERCROWN));

    public static final RegistryObject<Block> WHISTLECANE = BLOCKS.register("whistlecane", () -> new WhistlecaneBlock(ModBlockProperties.WHISTLECANE));
    public static final RegistryObject<Block> WHISTLECANE_PLANT = BLOCKS.register("whistlecane_plant", () -> new WhistlecanePlantBlock(ModBlockProperties.WHISTLECANE_BASE));

    public static final RegistryObject<FlowerPotBlock> POTTED_SOUL_SPORE = flowerPot(SOUL_SPORE);
    public static final RegistryObject<FlowerPotBlock> POTTED_GLOWING_SOUL_SPORE = flowerPot(GLOWING_SOUL_SPORE, ModBlockProperties.POTTED_GLOWING_SOUL_SPORE);
    public static final RegistryObject<FlowerPotBlock> POTTED_SOULBLIGHT_FUNGUS = flowerPot(SOULBLIGHT_FUNGUS);
    public static final RegistryObject<FlowerPotBlock> POTTED_SOULBLIGHT_SPROUTS = flowerPot(SOULBLIGHT_SPROUTS);
    public static final RegistryObject<FlowerPotBlock> POTTED_BLISTERCROWN = flowerPot(BLISTERCROWN);
    public static final RegistryObject<FlowerPotBlock> POTTED_WHISTLECANE = flowerPot(WHISTLECANE);

    public static final RegistryObject<Block> SOULBLIGHT_PLANKS = block("soulblight_planks", ModBlockProperties.SOULBLIGHT_PLANKS);
    public static final RegistryObject<SlabBlock> SOULBLIGHT_SLAB = BLOCKS.register("soulblight_slab", () -> new SlabBlock(ModBlockProperties.SOULBLIGHT_PLANKS));
    public static final RegistryObject<StairBlock> SOULBLIGHT_STAIRS = stairs("soulblight_stairs", SOULBLIGHT_PLANKS, ModBlockProperties.SOULBLIGHT_PLANKS);
    public static final RegistryObject<FenceBlock> SOULBLIGHT_FENCE = BLOCKS.register("soulblight_fence", () -> new FenceBlock(ModBlockProperties.SOULBLIGHT_PLANKS));
    public static final RegistryObject<FenceGateBlock> SOULBLIGHT_FENCE_GATE = BLOCKS.register("soulblight_fence_gate", () -> new FenceGateBlock(ModBlockProperties.SOULBLIGHT_PLANKS));
    public static final RegistryObject<ButtonBlock> SOULBLIGHT_BUTTON = BLOCKS.register("soulblight_button", () -> new WoodButtonBlock(ModBlockProperties.SOULBLIGHT_BUTTONS));
    public static final RegistryObject<PressurePlateBlock> SOULBLIGHT_PRESSURE_PLATE = BLOCKS.register("soulblight_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlockProperties.SOULBLIGHT_BUTTONS));
    public static final RegistryObject<DoorBlock> SOULBLIGHT_DOOR = BLOCKS.register("soulblight_door", () -> new DoorBlock(ModBlockProperties.SOULBLIGHT_DOOR));
    public static final RegistryObject<TrapDoorBlock> SOULBLIGHT_TRAPDOOR = BLOCKS.register("soulblight_trapdoor", () -> new TrapDoorBlock(ModBlockProperties.SOULBLIGHT_TRAPDOOR));
    public static final RegistryObject<StandingSignBlock> SOULBLIGHT_SIGN = BLOCKS.register("soulblight_sign", () -> new StandingSignBlock(ModBlockProperties.SOULBLIGHT_SIGN, ModWoodTypes.SOULBLIGHT));
    public static final RegistryObject<WallSignBlock> SOULBLIGHT_WALL_SIGN = BLOCKS.register("soulblight_wall_sign", () -> new WallSignBlock(ModBlockProperties.SOULBLIGHT_WALL_SIGN, ModWoodTypes.SOULBLIGHT));

    public static final RegistryObject<RotatedPillarBlock> WHISTLECANE_BLOCK = BLOCKS.register("whistlecane_block", () -> new RotatedPillarBlock(ModBlockProperties.WHISTLECANE_BLOCK));
    public static final RegistryObject<SlabBlock> WHISTLECANE_SLAB = BLOCKS.register("whistlecane_slab", () -> new SlabBlock(ModBlockProperties.WHISTLECANE_BLOCK));
    public static final RegistryObject<StairBlock> WHISTLECANE_STAIRS = stairs("whistlecane_stairs", WHISTLECANE_BLOCK, ModBlockProperties.WHISTLECANE_BLOCK);
    public static final RegistryObject<WhistlecaneFenceBlock> WHISTLECANE_FENCE = BLOCKS.register("whistlecane_fence", () -> new WhistlecaneFenceBlock(ModBlockProperties.WHISTLECANE_BLOCK));
    public static final RegistryObject<FenceGateBlock> WHISTLECANE_FENCE_GATE = BLOCKS.register("whistlecane_fence_gate", () -> new FenceGateBlock(ModBlockProperties.WHISTLECANE_BLOCK));
    public static final RegistryObject<ButtonBlock> WHISTLECANE_BUTTON = BLOCKS.register("whistlecane_button", () -> new WoodButtonBlock(ModBlockProperties.WHISTLECANE_BUTTONS));
    public static final RegistryObject<PressurePlateBlock> WHISTLECANE_PRESSURE_PLATE = BLOCKS.register("whistlecane_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlockProperties.WHISTLECANE_BUTTONS));
    public static final RegistryObject<DoorBlock> WHISTLECANE_DOOR = BLOCKS.register("whistlecane_door", () -> new DoorBlock(ModBlockProperties.WHISTLECANE_DOOR));
    public static final RegistryObject<TrapDoorBlock> WHISTLECANE_TRAPDOOR = BLOCKS.register("whistlecane_trapdoor", () -> new TrapDoorBlock(ModBlockProperties.WHISTLECANE_TRAPDOOR));
    public static final RegistryObject<StandingSignBlock> WHISTLECANE_SIGN = BLOCKS.register("whistlecane_sign", () -> new StandingSignBlock(ModBlockProperties.WHISTLECANE_SIGN, ModWoodTypes.WHISTLECANE));
    public static final RegistryObject<WallSignBlock> WHISTLECANE_WALL_SIGN = BLOCKS.register("whistlecane_wall_sign", () -> new WallSignBlock(ModBlockProperties.WHISTLECANE_WALL_SIGN, ModWoodTypes.WHISTLECANE));

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

    private static RegistryObject<StairBlock> stairs(String name, Supplier<? extends Block> baseBlock, BlockBehaviour.Properties properties) {
        return BLOCKS.register(name, () -> new StairBlock(() -> baseBlock.get().defaultBlockState(), properties));
    }

    private static RegistryObject<Block> block(String name, BlockBehaviour.Properties properties) {
        return BLOCKS.register(name, () -> new Block(properties));
    }
}
