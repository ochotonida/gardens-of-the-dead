package gardensofthedead.registry;

import gardensofthedead.GardensOfTheDead;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> SOULBLIGHT_STEMS = tag("soulblight_stems");

        public static final TagKey<Block> FENCES = tag("fences");
        public static final TagKey<Block> WOODEN_FENCES = tag("fences/wooden");
        public static final TagKey<Block> FENCE_GATES = tag("fence_gates");
        public static final TagKey<Block> WOODEN_FENCE_GATES = tag("fence_gates/wooden");

        public static final TagKey<Block> MUSHROOMS = tag("mushrooms");

        private static TagKey<Block> tag(String path) {
            return TagKey.create(Registry.BLOCK_REGISTRY, GardensOfTheDead.id(path));
        }
    }

    public static class Items {

        public static final TagKey<Item> SOULBLIGHT_STEMS = tag("soulblight_stems");

        public static final TagKey<Item> FENCES = tag("fences");
        public static final TagKey<Item> WOODEN_FENCES = tag("fences/wooden");
        public static final TagKey<Item> FENCE_GATES = tag("fence_gates");
        public static final TagKey<Item> WOODEN_FENCE_GATES = tag("fence_gates/wooden");

        public static final TagKey<Item> MUSHROOMS = tag("mushrooms");

        private static TagKey<Item> tag(String path) {
            return TagKey.create(Registry.ITEM_REGISTRY, GardensOfTheDead.id(path));
        }
    }

    public static class Biomes {

        public static final TagKey<Biome> IS_DRY = tag("is_dry/nether");
        public static final TagKey<Biome> IS_HOT = tag("is_hot/nether");

        private static TagKey<Biome> tag(String path) {
            return TagKey.create(Registry.BIOME_REGISTRY, GardensOfTheDead.id(path));
        }
    }
}
