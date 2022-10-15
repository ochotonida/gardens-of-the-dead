package gardensofthedead.common.init;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ModBlockProperties {

    private static final int GLOWING_SOUL_SPORE_LIGHT = 7;
    private static final int BLISTERCROWN_LIGHT = 5;

    public static BlockBehaviour.Properties SOUL_SPORE = BlockBehaviour.Properties.of(Material.PLANT)
            .color(MaterialColor.COLOR_BROWN)
            .sound(SoundType.WEEPING_VINES)
            .noCollission()
            .instabreak();

    public static BlockBehaviour.Properties GLOWING_SOUL_SPORE = copy(SOUL_SPORE)
            .lightLevel(state -> GLOWING_SOUL_SPORE_LIGHT);

    public static BlockBehaviour.Properties SOULBLIGHT_FUNGUS = BlockBehaviour.Properties.of(Material.PLANT)
            .color(MaterialColor.COLOR_BROWN)
            .instabreak()
            .noCollission()
            .sound(SoundType.FUNGUS);

    public static BlockBehaviour.Properties SOULBLIGHT_SPROUTS = BlockBehaviour.Properties.of(Material.REPLACEABLE_FIREPROOF_PLANT)
            .color(MaterialColor.COLOR_BROWN)
            .noCollission()
            .instabreak()
            .sound(SoundType.NETHER_SPROUTS)
            .offsetType(BlockBehaviour.OffsetType.XZ);

    public static BlockBehaviour.Properties BLISTERCROWN = BlockBehaviour.Properties.of(Material.PLANT)
            .color(MaterialColor.COLOR_RED)
            .noCollission()
            .instabreak()
            .sound(SoundType.NETHER_SPROUTS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .lightLevel(state -> BLISTERCROWN_LIGHT);

    public static BlockBehaviour.Properties WHISTLECANE_BASE = BlockBehaviour.Properties.of(Material.VEGETABLE)
            .color(MaterialColor.CRIMSON_NYLIUM)
            .randomTicks()
            .instabreak()
            .strength(1)
            .sound(ModSoundTypes.WHISTLECANE)
            .noOcclusion()
            .dynamicShape()
            .offsetType(BlockBehaviour.OffsetType.XZ);

    public static BlockBehaviour.Properties WHISTLECANE = copy(WHISTLECANE_BASE)
            .randomTicks();

    public static BlockBehaviour.Properties SOULBLIGHT_STEM = BlockBehaviour.Properties.of(Material.NETHER_WOOD)
            .color(MaterialColor.COLOR_BROWN)
            .strength(2)
            .sound(SoundType.STEM);

    public static BlockBehaviour.Properties BLIGHTWART_BLOCK = BlockBehaviour.Properties.of(Material.GRASS)
            .color(MaterialColor.COLOR_YELLOW)
            .strength(1)
            .sound(SoundType.WART_BLOCK);

    public static BlockBehaviour.Properties POTTED_GLOWING_SOUL_SPORE = pottedPlant()
            .lightLevel(state -> GLOWING_SOUL_SPORE_LIGHT);

    public static BlockBehaviour.Properties SOULBLIGHT_PLANKS = BlockBehaviour.Properties.of(Material.NETHER_WOOD)
            .color(MaterialColor.COLOR_BROWN)
            .strength(2, 3)
            .sound(SoundType.WOOD);

    public static BlockBehaviour.Properties SOULBLIGHT_BUTTONS = copy(SOULBLIGHT_PLANKS)
            .noCollission()
            .strength(0.5F);

    public static BlockBehaviour.Properties SOULBLIGHT_DOOR = copy(SOULBLIGHT_PLANKS)
            .strength(3)
            .noOcclusion();

    public static BlockBehaviour.Properties SOULBLIGHT_TRAPDOOR = copy(SOULBLIGHT_DOOR)
            .isValidSpawn(ModBlockProperties::never);

    public static BlockBehaviour.Properties SOULBLIGHT_SIGN = copy(SOULBLIGHT_PLANKS)
            .noCollission()
            .strength(1);

    @SuppressWarnings({"Convert2MethodRef", "FunctionalExpressionCanBeFolded"})
    public static BlockBehaviour.Properties SOULBLIGHT_WALL_SIGN = copy(SOULBLIGHT_SIGN)
            .lootFrom(() -> ModBlocks.SOULBLIGHT_SIGN.get());

    public static BlockBehaviour.Properties pottedPlant() {
        return BlockBehaviour.Properties.of(Material.DECORATION)
                .instabreak()
                .noOcclusion();
    }

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

    private static Boolean never(BlockState state, BlockGetter level, BlockPos pos, EntityType<?> entityType) {
        return false;
    }
}
