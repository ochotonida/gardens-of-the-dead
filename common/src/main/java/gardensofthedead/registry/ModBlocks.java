package gardensofthedead.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.block.*;
import gardensofthedead.block.CeilingHangingSignBlock;
import gardensofthedead.block.StandingSignBlock;
import gardensofthedead.block.WallHangingSignBlock;
import gardensofthedead.block.WallSignBlock;
import gardensofthedead.platform.PlatformServices;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(GardensOfTheDead.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<SoulSporeBlock> SOUL_SPORE = BLOCKS.register("soul_spore", () -> new SoulSporeBlock(ModBlockProperties.SOUL_SPORE));
    public static final RegistrySupplier<SoulSporeBaseBlock> GLOWING_SOUL_SPORE = BLOCKS.register("glowing_soul_spore", () -> new SoulSporeBaseBlock(ModBlockProperties.GLOWING_SOUL_SPORE));

    public static final RegistrySupplier<Block> SOULBLIGHT_FUNGUS = BLOCKS.register("soulblight_fungus", () -> new SoulblightFungusBlock(ModBlockProperties.SOULBLIGHT_FUNGUS));
    public static final RegistrySupplier<Block> SOULBLIGHT_SPROUTS = BLOCKS.register("soulblight_sprouts", () -> new SoulblightSproutsBlock(ModBlockProperties.SOULBLIGHT_SPROUTS));

    public static final RegistrySupplier<RotatedPillarBlock> STRIPPED_SOULBLIGHT_STEM = strippedSoulblightStem("stripped_soulblight_stem");
    public static final RegistrySupplier<RotatedPillarBlock> SOULBLIGHT_STEM = soulblightStem("soulblight_stem", STRIPPED_SOULBLIGHT_STEM);
    public static final RegistrySupplier<RotatedPillarBlock> STRIPPED_SOULBLIGHT_HYPHAE = strippedSoulblightStem("stripped_soulblight_hyphae");
    public static final RegistrySupplier<RotatedPillarBlock> SOULBLIGHT_HYPHAE = soulblightStem("soulblight_hyphae", STRIPPED_SOULBLIGHT_HYPHAE);
    public static final RegistrySupplier<Block> BLIGHTWART_BLOCK = block("blightwart_block", ModBlockProperties.BLIGHTWART_BLOCK);

    public static final RegistrySupplier<Block> BLISTERCROWN = BLOCKS.register("blistercrown", () -> new BlistercrownBlock(ModBlockProperties.BLISTERCROWN));
    public static final RegistrySupplier<DoublePlantBlock> TALL_BLISTERCROWN = BLOCKS.register("tall_blistercrown", () -> new TallBlistercrownBlock(ModBlockProperties.BLISTERCROWN));

    public static final RegistrySupplier<Block> WHISTLECANE = BLOCKS.register("whistlecane", () -> new WhistlecaneBlock(ModBlockProperties.WHISTLECANE));

    public static final RegistrySupplier<FlowerPotBlock> POTTED_SOUL_SPORE = flowerPot(SOUL_SPORE);
    public static final RegistrySupplier<FlowerPotBlock> POTTED_GLOWING_SOUL_SPORE = flowerPot(GLOWING_SOUL_SPORE, ModBlockProperties.POTTED_GLOWING_SOUL_SPORE);
    public static final RegistrySupplier<FlowerPotBlock> POTTED_SOULBLIGHT_FUNGUS = flowerPot(SOULBLIGHT_FUNGUS);
    public static final RegistrySupplier<FlowerPotBlock> POTTED_SOULBLIGHT_SPROUTS = flowerPot(SOULBLIGHT_SPROUTS);
    public static final RegistrySupplier<FlowerPotBlock> POTTED_BLISTERCROWN = flowerPot(BLISTERCROWN);
    public static final RegistrySupplier<FlowerPotBlock> POTTED_WHISTLECANE = flowerPot(WHISTLECANE);

    public static final RegistrySupplier<Block> SOULBLIGHT_PLANKS = block("soulblight_planks", ModBlockProperties.SOULBLIGHT_PLANKS);
    public static final RegistrySupplier<SlabBlock> SOULBLIGHT_SLAB = BLOCKS.register("soulblight_slab", () -> new SlabBlock(ModBlockProperties.SOULBLIGHT_PLANKS));
    public static final RegistrySupplier<StairBlock> SOULBLIGHT_STAIRS = stairs("soulblight_stairs", SOULBLIGHT_PLANKS, ModBlockProperties.SOULBLIGHT_PLANKS);
    public static final RegistrySupplier<FenceBlock> SOULBLIGHT_FENCE = BLOCKS.register("soulblight_fence", () -> new FenceBlock(ModBlockProperties.SOULBLIGHT_PLANKS));
    public static final RegistrySupplier<FenceGateBlock> SOULBLIGHT_FENCE_GATE = BLOCKS.register("soulblight_fence_gate", () -> fenceGate(ModBlockProperties.SOULBLIGHT_PLANKS, ModWoodTypes.SOULBLIGHT));
    public static final RegistrySupplier<ButtonBlock> SOULBLIGHT_BUTTON = BLOCKS.register("soulblight_button", () -> woodenButton(ModBlockSetTypes.SOULBLIGHT));
    public static final RegistrySupplier<PressurePlateBlock> SOULBLIGHT_PRESSURE_PLATE = BLOCKS.register("soulblight_pressure_plate", () -> pressurePlate(ModBlockProperties.SOULBLIGHT_PLANKS, ModBlockSetTypes.SOULBLIGHT));
    public static final RegistrySupplier<DoorBlock> SOULBLIGHT_DOOR = BLOCKS.register("soulblight_door", () -> door(ModBlockProperties.SOULBLIGHT_DOOR, ModBlockSetTypes.SOULBLIGHT));
    public static final RegistrySupplier<TrapDoorBlock> SOULBLIGHT_TRAPDOOR = BLOCKS.register("soulblight_trapdoor", () -> trapDoor(ModBlockProperties.SOULBLIGHT_TRAPDOOR, ModBlockSetTypes.SOULBLIGHT));
    public static final RegistrySupplier<StandingSignBlock> SOULBLIGHT_SIGN = BLOCKS.register("soulblight_sign", () -> sign(ModBlockProperties.SOULBLIGHT_PLANKS, ModWoodTypes.SOULBLIGHT));
    public static final RegistrySupplier<WallSignBlock> SOULBLIGHT_WALL_SIGN = BLOCKS.register("soulblight_wall_sign", () -> wallSign(ModBlockProperties.SOULBLIGHT_PLANKS, SOULBLIGHT_SIGN, ModWoodTypes.SOULBLIGHT));
    public static final RegistrySupplier<CeilingHangingSignBlock> SOULBLIGHT_HANGING_SIGN = BLOCKS.register("soulblight_hanging_sign", () -> hangingSign(ModBlockProperties.SOULBLIGHT_SIGN, SoundType.NETHER_WOOD_HANGING_SIGN, ModWoodTypes.SOULBLIGHT));
    public static final RegistrySupplier<WallHangingSignBlock> SOULBLIGHT_WALL_HANGING_SIGN = BLOCKS.register("soulblight_wall_hanging_sign", () -> wallHangingSign(ModBlockProperties.SOULBLIGHT_SIGN, SOULBLIGHT_HANGING_SIGN, SoundType.NETHER_WOOD_HANGING_SIGN, ModWoodTypes.SOULBLIGHT));

    public static final RegistrySupplier<RotatedPillarBlock> WHISTLECANE_BLOCK = BLOCKS.register("whistlecane_block", () -> new RotatedPillarBlock(ModBlockProperties.WHISTLECANE_BLOCK));
    public static final RegistrySupplier<Block> WHISTLECANE_PLANKS = block("whistlecane_planks", ModBlockProperties.WHISTLECANE_BLOCK);
    public static final RegistrySupplier<SlabBlock> WHISTLECANE_SLAB = BLOCKS.register("whistlecane_slab", () -> new SlabBlock(ModBlockProperties.WHISTLECANE_BLOCK));
    public static final RegistrySupplier<StairBlock> WHISTLECANE_STAIRS = stairs("whistlecane_stairs", WHISTLECANE_PLANKS, ModBlockProperties.WHISTLECANE_BLOCK);
    public static final RegistrySupplier<WhistlecaneFenceBlock> WHISTLECANE_FENCE = BLOCKS.register("whistlecane_fence", () -> new WhistlecaneFenceBlock(ModBlockProperties.WHISTLECANE_BLOCK));
    public static final RegistrySupplier<FenceGateBlock> WHISTLECANE_FENCE_GATE = BLOCKS.register("whistlecane_fence_gate", () -> fenceGate(ModBlockProperties.WHISTLECANE_BLOCK, ModWoodTypes.WHISTLECANE));
    public static final RegistrySupplier<ButtonBlock> WHISTLECANE_BUTTON = BLOCKS.register("whistlecane_button", () -> woodenButton(ModBlockSetTypes.WHISTLECANE));
    public static final RegistrySupplier<PressurePlateBlock> WHISTLECANE_PRESSURE_PLATE = BLOCKS.register("whistlecane_pressure_plate", () -> pressurePlate(ModBlockProperties.WHISTLECANE_BLOCK, ModBlockSetTypes.WHISTLECANE));
    public static final RegistrySupplier<DoorBlock> WHISTLECANE_DOOR = BLOCKS.register("whistlecane_door", () -> door(ModBlockProperties.WHISTLECANE_DOOR, ModBlockSetTypes.WHISTLECANE));
    public static final RegistrySupplier<TrapDoorBlock> WHISTLECANE_TRAPDOOR = BLOCKS.register("whistlecane_trapdoor", () -> trapDoor(ModBlockProperties.WHISTLECANE_TRAPDOOR, ModBlockSetTypes.WHISTLECANE));
    public static final RegistrySupplier<StandingSignBlock> WHISTLECANE_SIGN = BLOCKS.register("whistlecane_sign", () -> sign(ModBlockProperties.WHISTLECANE_BLOCK, ModWoodTypes.WHISTLECANE));
    public static final RegistrySupplier<WallSignBlock> WHISTLECANE_WALL_SIGN = BLOCKS.register("whistlecane_wall_sign", () -> wallSign(ModBlockProperties.WHISTLECANE_BLOCK, WHISTLECANE_SIGN, ModWoodTypes.WHISTLECANE));
    public static final RegistrySupplier<CeilingHangingSignBlock> WHISTLECANE_HANGING_SIGN = BLOCKS.register("whistlecane_hanging_sign", () -> hangingSign(ModBlockProperties.WHISTLECANE_SIGN, SoundType.BAMBOO_WOOD_HANGING_SIGN, ModWoodTypes.WHISTLECANE));
    public static final RegistrySupplier<WallHangingSignBlock> WHISTLECANE_WALL_HANGING_SIGN = BLOCKS.register("whistlecane_wall_hanging_sign", () -> wallHangingSign(ModBlockProperties.WHISTLECANE_WALL_SIGN, WHISTLECANE_HANGING_SIGN, SoundType.BAMBOO_WOOD_HANGING_SIGN, ModWoodTypes.WHISTLECANE));

    public static final RegistrySupplier<Block> WHISTLECANE_MOSAIC = block("whistlecane_mosaic", ModBlockProperties.WHISTLECANE_BLOCK);
    public static final RegistrySupplier<SlabBlock> WHISTLECANE_MOSAIC_SLAB = BLOCKS.register("whistlecane_mosaic_slab", () -> new SlabBlock(ModBlockProperties.WHISTLECANE_BLOCK));
    public static final RegistrySupplier<StairBlock> WHISTLECANE_MOSAIC_STAIRS = stairs("whistlecane_mosaic_stairs", WHISTLECANE_MOSAIC, ModBlockProperties.WHISTLECANE_BLOCK);

    private static Supplier<RotatedPillarBlock> createStrippableBlock(Properties properties, RegistrySupplier<? extends Block> strippedBlock) {
        return () -> PlatformServices.platformHelper.createStrippableBlock(strippedBlock, properties);
    }

    private static RegistrySupplier<RotatedPillarBlock> soulblightStem(String name, RegistrySupplier<? extends Block> strippedLogBlock) {
        return BLOCKS.register(name, createStrippableBlock(ModBlockProperties.SOULBLIGHT_STEM, strippedLogBlock));
    }

    private static RegistrySupplier<RotatedPillarBlock> strippedSoulblightStem(String name) {
        return BLOCKS.register(name, () -> new RotatedPillarBlock(ModBlockProperties.SOULBLIGHT_STEM));
    }

    private static RegistrySupplier<FlowerPotBlock> flowerPot(RegistrySupplier<? extends Block> plant) {
        return flowerPot(plant, ModBlockProperties.pottedPlant());
    }

    private static RegistrySupplier<FlowerPotBlock> flowerPot(RegistrySupplier<? extends Block> plant, Properties properties) {
        return BLOCKS.register("potted_" + plant.getId().getPath(), () -> PlatformServices.platformHelper.createFlowerPot(plant, properties));
    }

    private static RegistrySupplier<StairBlock> stairs(String name, Supplier<? extends Block> baseBlock, Properties properties) {
        return BLOCKS.register(name, () -> new StairBlock(baseBlock.get().defaultBlockState(), properties));
    }

    private static RegistrySupplier<Block> block(String name, Properties properties) {
        return BLOCKS.register(name, () -> new Block(properties));
    }

    private static ButtonBlock woodenButton(BlockSetType blockSetType) {
        int ticksToStayPressed = 30;
        boolean arrowsCanPress = true;
        return button(ticksToStayPressed, arrowsCanPress, blockSetType);
    }

    private static ButtonBlock button(int ticksToStayPressed, boolean arrowsCanPress, BlockSetType blockSetType) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties
                .of()
                .noCollission()
                .strength(0.5F)
                .pushReaction(PushReaction.DESTROY)
                .sound(blockSetType.soundType());

        return new ButtonBlock(properties, blockSetType, ticksToStayPressed, arrowsCanPress);
    }

    private static PressurePlateBlock pressurePlate(Properties baseProperties, BlockSetType blockSetType) {
        Properties properties = ModBlockProperties.copy(baseProperties)
                .noCollission()
                .strength(0.5F);

        return new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, properties, blockSetType);
    }

    private static FenceGateBlock fenceGate(Properties baseProperties, WoodType woodType) {
        Properties properties = ModBlockProperties.copy(baseProperties)
                .strength(2, 3);

        return new FenceGateBlock(properties, woodType);
    }

    private static DoorBlock door(Properties baseProperties, BlockSetType blockSetType) {
        Properties properties = ModBlockProperties.copy(baseProperties)
                .strength(3)
                .noOcclusion();

        return new DoorBlock(properties, blockSetType);
    }

    private static TrapDoorBlock trapDoor(Properties baseProperties, BlockSetType blockSetType) {
        Properties properties = ModBlockProperties.copy(baseProperties)
                .strength(3)
                .noOcclusion()
                .isValidSpawn(ModBlockProperties::never);

        return new TrapDoorBlock(properties, blockSetType);
    }

    private static StandingSignBlock sign(Properties baseProperties, WoodType woodType) {
        Properties properties = ModBlockProperties.copy(baseProperties)
                .strength(1)
                .noCollission();
        return new StandingSignBlock(properties, woodType);
    }

    private static WallSignBlock wallSign(Properties baseProperties, RegistrySupplier<?> signBlock, WoodType woodType) {
        Properties properties = ModBlockProperties.copyWithLoot(baseProperties, signBlock.getId())
                .strength(1)
                .noCollission();
        return new WallSignBlock(properties, woodType);
    }

    private static CeilingHangingSignBlock hangingSign(Properties baseProperties, SoundType soundType, WoodType woodType) {
        Properties properties = ModBlockProperties.copy(baseProperties)
                .sound(soundType)
                .strength(1)
                .noCollission();
        return new CeilingHangingSignBlock(properties, woodType);
    }

    private static WallHangingSignBlock wallHangingSign(Properties baseProperties, RegistrySupplier<?> signBlock, SoundType soundType, WoodType woodType) {
        Properties properties = ModBlockProperties.copyWithLoot(baseProperties, signBlock.getId())
                .sound(soundType)
                .strength(1)
                .noCollission();
        return new WallHangingSignBlock(properties, woodType);
    }
}
