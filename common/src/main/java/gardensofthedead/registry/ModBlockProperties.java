package gardensofthedead.registry;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.platform.PlatformServices;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModBlockProperties {

    private static final int GLOWING_SOUL_SPORE_LIGHT = 7;
    private static final int BLISTERCROWN_LIGHT = 5;

    public static BlockBehaviour.Properties SOUL_SPORE = of()
            .mapColor(MapColor.COLOR_BROWN)
            .sound(SoundType.WEEPING_VINES)
            .noCollission()
            .instabreak()
            .pushReaction(PushReaction.DESTROY);

    public static BlockBehaviour.Properties GLOWING_SOUL_SPORE = copy(SOUL_SPORE)
            .lightLevel(state -> GLOWING_SOUL_SPORE_LIGHT);

    public static BlockBehaviour.Properties SOULBLIGHT_FUNGUS = of()
            .mapColor(MapColor.COLOR_BROWN)
            .instabreak()
            .noCollission()
            .sound(SoundType.FUNGUS)
            .pushReaction(PushReaction.DESTROY);

    public static BlockBehaviour.Properties SOULBLIGHT_SPROUTS = of()
            .mapColor(MapColor.COLOR_BROWN)
            .replaceable()
            .noCollission()
            .instabreak()
            .sound(SoundType.NETHER_SPROUTS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY);

    public static BlockBehaviour.Properties BLISTERCROWN = of()
            .mapColor(MapColor.COLOR_RED)
            .replaceable()
            .noCollission()
            .instabreak()
            .sound(SoundType.NETHER_SPROUTS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .lightLevel(state -> BLISTERCROWN_LIGHT)
            .pushReaction(PushReaction.DESTROY);

    public static BlockBehaviour.Properties WHISTLECANE = of()
            .mapColor(MapColor.CRIMSON_NYLIUM)
            .forceSolidOn()
            .randomTicks()
            .instabreak()
            .strength(1)
            .sound(ModSoundTypes.WHISTLECANE)
            .noOcclusion()
            .dynamicShape()
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor((blockState, level, pos) -> false);

    public static BlockBehaviour.Properties SOULBLIGHT_STEM = of()
            .mapColor(MapColor.COLOR_BROWN)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2)
            .sound(SoundType.STEM);

    public static BlockBehaviour.Properties BLIGHTWART_BLOCK = of()
            .mapColor(MapColor.COLOR_YELLOW)
            .strength(1)
            .sound(SoundType.WART_BLOCK);

    public static BlockBehaviour.Properties POTTED_GLOWING_SOUL_SPORE = pottedPlant()
            .lightLevel(state -> GLOWING_SOUL_SPORE_LIGHT);

    public static BlockBehaviour.Properties SOULBLIGHT_PLANKS = of()
            .mapColor(MapColor.COLOR_BROWN)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2, 3)
            .sound(SoundType.WOOD);

    public static BlockBehaviour.Properties SOULBLIGHT_DOOR = copy(SOULBLIGHT_PLANKS)
            .strength(3)
            .noOcclusion();

    public static BlockBehaviour.Properties SOULBLIGHT_TRAPDOOR = copy(SOULBLIGHT_DOOR)
            .isValidSpawn(ModBlockProperties::never);

    public static BlockBehaviour.Properties SOULBLIGHT_SIGN = copy(SOULBLIGHT_PLANKS)
            .noCollission()
            .strength(1);

    public static BlockBehaviour.Properties WHISTLECANE_BLOCK = of()
            .mapColor(MapColor.CRIMSON_NYLIUM)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1, 2)
            .sound(SoundType.BAMBOO_WOOD);

    public static BlockBehaviour.Properties WHISTLECANE_DOOR = copy(WHISTLECANE_BLOCK)
            .strength(2)
            .noOcclusion();

    public static BlockBehaviour.Properties WHISTLECANE_TRAPDOOR = copy(WHISTLECANE_DOOR)
            .isValidSpawn(ModBlockProperties::never);

    public static BlockBehaviour.Properties WHISTLECANE_SIGN = copy(WHISTLECANE_BLOCK)
            .noCollission()
            .strength(1);

    public static BlockBehaviour.Properties WHISTLECANE_WALL_SIGN = copyWithLoot(
            WHISTLECANE_SIGN,
            GardensOfTheDead.id("whistlecane_sign")
    );

    public static BlockBehaviour.Properties pottedPlant() {
        return of()
                .instabreak()
                .noOcclusion();
    }

    public static BlockBehaviour.Properties of() {
        return PlatformServices.platformHelper.createBlockProperties();
    }

    public static BlockBehaviour.Properties copy(BlockBehaviour.Properties properties) {
        return PlatformServices.platformHelper.copyBlockProperties(properties);
    }

    public static BlockBehaviour.Properties copyWithLoot(BlockBehaviour.Properties properties, ResourceLocation id) {
        return PlatformServices.platformHelper.copyBlockPropertiesWithLoot(properties, id);
    }

    public static Boolean never(BlockState state, BlockGetter level, BlockPos pos, EntityType<?> entityType) {
        return false;
    }
}
