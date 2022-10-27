package gardensofthedead.forge.data;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.block.StandingSignBlock;
import gardensofthedead.block.WallSignBlock;
import gardensofthedead.registry.ModBlocks;
import gardensofthedead.registry.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;

public class BlockTags extends BlockTagsProvider {

    public BlockTags(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, GardensOfTheDead.MOD_ID, existingFileHelper);
    }

    protected static final List<Block> NON_FLAMMABLE_WOOD_ITEMS = List.of(
            ModBlocks.SOULBLIGHT_PLANKS.get(),
            ModBlocks.SOULBLIGHT_SLAB.get(),
            ModBlocks.SOULBLIGHT_STAIRS.get(),
            ModBlocks.SOULBLIGHT_FENCE.get(),
            ModBlocks.SOULBLIGHT_FENCE_GATE.get(),
            ModBlocks.SOULBLIGHT_BUTTON.get(),
            ModBlocks.SOULBLIGHT_PRESSURE_PLATE.get(),
            ModBlocks.SOULBLIGHT_DOOR.get(),
            ModBlocks.SOULBLIGHT_TRAPDOOR.get(),
            ModBlocks.SOULBLIGHT_SIGN.get(),
            ModBlocks.WHISTLECANE_BLOCK.get(),
            ModBlocks.WHISTLECANE_SLAB.get(),
            ModBlocks.WHISTLECANE_STAIRS.get(),
            ModBlocks.WHISTLECANE_FENCE.get(),
            ModBlocks.WHISTLECANE_FENCE_GATE.get(),
            ModBlocks.WHISTLECANE_BUTTON.get(),
            ModBlocks.WHISTLECANE_PRESSURE_PLATE.get(),
            ModBlocks.WHISTLECANE_DOOR.get(),
            ModBlocks.WHISTLECANE_TRAPDOOR.get(),
            ModBlocks.WHISTLECANE_SIGN.get()
    );

    @Override
    protected void addTags() {
        ForgeRegistries.BLOCKS.getValues()
                .stream()
                .filter(block -> Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getNamespace().equals(GardensOfTheDead.MOD_ID))
                .forEach(block -> {
                    if (block instanceof FlowerPotBlock) {
                        tag(net.minecraft.tags.BlockTags.FLOWER_POTS).add(block);
                    } else if (block instanceof StandingSignBlock) {
                        tag(net.minecraft.tags.BlockTags.STANDING_SIGNS).add(block);
                    } else if (block instanceof WallSignBlock) {
                        tag(net.minecraft.tags.BlockTags.WALL_SIGNS).add(block);
                    } else if (block instanceof FenceGateBlock) {
                        tag(net.minecraft.tags.BlockTags.FENCE_GATES).add(block);
                    }
                });

        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE).addTag(
                ModTags.Blocks.SOULBLIGHT_STEMS
        ).add(
                ModBlocks.SOULBLIGHT_WALL_SIGN.get(),
                ModBlocks.WHISTLECANE_WALL_SIGN.get()
        ).add(
                NON_FLAMMABLE_WOOD_ITEMS.toArray(new Block[]{})
        );

        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_HOE).add(
                ModBlocks.BLIGHTWART_BLOCK.get()
        );

        tag(net.minecraft.tags.BlockTags.ENDERMAN_HOLDABLE).add(
                ModBlocks.SOULBLIGHT_FUNGUS.get(),
                ModBlocks.SOULBLIGHT_SPROUTS.get(),
                ModBlocks.BLISTERCROWN.get()
        );

        tag(ModTags.Blocks.SOULBLIGHT_STEMS).add(
                ModBlocks.SOULBLIGHT_STEM.get(),
                ModBlocks.STRIPPED_SOULBLIGHT_STEM.get(),
                ModBlocks.SOULBLIGHT_HYPHAE.get(),
                ModBlocks.STRIPPED_SOULBLIGHT_HYPHAE.get()
        );

        tag(net.minecraft.tags.BlockTags.NON_FLAMMABLE_WOOD).addTag(
                ModTags.Blocks.SOULBLIGHT_STEMS
        ).add(
                ModBlocks.SOULBLIGHT_WALL_SIGN.get(),
                ModBlocks.WHISTLECANE_WALL_SIGN.get()
        ).add(
                NON_FLAMMABLE_WOOD_ITEMS.toArray(new Block[]{})
        );

        tag(net.minecraft.tags.BlockTags.LOGS).addTag(
                ModTags.Blocks.SOULBLIGHT_STEMS
        );

        tag(net.minecraft.tags.BlockTags.WART_BLOCKS).add(
                ModBlocks.BLIGHTWART_BLOCK.get()
        );

        tag(net.minecraft.tags.BlockTags.WOODEN_FENCES).add(
                ModBlocks.SOULBLIGHT_FENCE.get(),
                ModBlocks.WHISTLECANE_FENCE.get()
        );

        tag(Tags.Blocks.FENCES_WOODEN).add(
                ModBlocks.SOULBLIGHT_FENCE.get(),
                ModBlocks.WHISTLECANE_FENCE.get()
        );

        tag(Tags.Blocks.FENCE_GATES_WOODEN).add(
                ModBlocks.SOULBLIGHT_FENCE_GATE.get(),
                ModBlocks.WHISTLECANE_FENCE_GATE.get()
        );

        tag(net.minecraft.tags.BlockTags.WOODEN_BUTTONS).add(
                ModBlocks.SOULBLIGHT_BUTTON.get(),
                ModBlocks.WHISTLECANE_BUTTON.get()
        );

        tag(net.minecraft.tags.BlockTags.PLANKS).add(
                ModBlocks.SOULBLIGHT_PLANKS.get(),
                ModBlocks.WHISTLECANE_BLOCK.get()
        );

        tag(net.minecraft.tags.BlockTags.WOODEN_PRESSURE_PLATES).add(
                ModBlocks.SOULBLIGHT_PRESSURE_PLATE.get(),
                ModBlocks.WHISTLECANE_PRESSURE_PLATE.get()
        );

        tag(net.minecraft.tags.BlockTags.WOODEN_SLABS).add(
                ModBlocks.SOULBLIGHT_SLAB.get(),
                ModBlocks.WHISTLECANE_SLAB.get()
        );

        tag(net.minecraft.tags.BlockTags.WOODEN_DOORS).add(
                ModBlocks.SOULBLIGHT_DOOR.get(),
                ModBlocks.WHISTLECANE_DOOR.get()
        );

        tag(net.minecraft.tags.BlockTags.WOODEN_TRAPDOORS).add(
                ModBlocks.SOULBLIGHT_TRAPDOOR.get(),
                ModBlocks.WHISTLECANE_TRAPDOOR.get()
        );

        tag(net.minecraft.tags.BlockTags.WOODEN_STAIRS).add(
                ModBlocks.SOULBLIGHT_STAIRS.get(),
                ModBlocks.WHISTLECANE_STAIRS.get()
        );
    }
}
