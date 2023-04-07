package gardensofthedead.data.registry;

import com.google.common.collect.Maps;
import gardensofthedead.registry.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class ModBlockFamilies {

    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();
    private static final String RECIPE_GROUP_PREFIX_WOODEN = "wooden";
    private static final String RECIPE_UNLOCKED_BY_HAS_PLANKS = "has_planks";

    public static final BlockFamily SOULBLIGHT_PLANKS = familyBuilder(ModBlocks.SOULBLIGHT_PLANKS.get())
            .button(ModBlocks.SOULBLIGHT_BUTTON.get())
            .fence(ModBlocks.SOULBLIGHT_FENCE.get())
            .fenceGate(ModBlocks.SOULBLIGHT_FENCE_GATE.get())
            .pressurePlate(ModBlocks.SOULBLIGHT_PRESSURE_PLATE.get())
            .sign(ModBlocks.SOULBLIGHT_SIGN.get(), ModBlocks.SOULBLIGHT_WALL_SIGN.get())
            .slab(ModBlocks.SOULBLIGHT_SLAB.get())
            .stairs(ModBlocks.SOULBLIGHT_STAIRS.get())
            .door(ModBlocks.SOULBLIGHT_DOOR.get())
            .trapdoor(ModBlocks.SOULBLIGHT_TRAPDOOR.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily BAMBOO_PLANKS = familyBuilder(ModBlocks.WHISTLECANE_PLANKS.get())
            .button(ModBlocks.WHISTLECANE_BUTTON.get())
            .slab(ModBlocks.WHISTLECANE_SLAB.get())
            .stairs(ModBlocks.WHISTLECANE_STAIRS.get())
            .customFence(ModBlocks.WHISTLECANE_FENCE.get())
            .customFenceGate(ModBlocks.WHISTLECANE_FENCE_GATE.get())
            .pressurePlate(ModBlocks.WHISTLECANE_PRESSURE_PLATE.get())
            .sign(ModBlocks.WHISTLECANE_SIGN.get(), ModBlocks.WHISTLECANE_WALL_SIGN.get())
            .door(ModBlocks.WHISTLECANE_DOOR.get())
            .trapdoor(ModBlocks.WHISTLECANE_TRAPDOOR.get())
            .mosaic(ModBlocks.WHISTLECANE_MOSAIC.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily WHISTLECANE_MOSAIC = familyBuilder(ModBlocks.WHISTLECANE_MOSAIC.get())
            .slab(ModBlocks.WHISTLECANE_MOSAIC_SLAB.get())
            .stairs(ModBlocks.WHISTLECANE_MOSAIC_STAIRS.get())
            .getFamily();

    @SuppressWarnings("deprecation")
    private static BlockFamily.Builder familyBuilder(Block block) {
        BlockFamily.Builder builder = new BlockFamily.Builder(block);
        BlockFamily blockfamily = MAP.put(block, builder.getFamily());
        if (blockfamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(block));
        } else {
            return builder;
        }
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }
}
