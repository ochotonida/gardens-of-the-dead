package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> SOULBLIGHT_STEMS = tag("soulblight_stems");

        private static TagKey<Block> tag(String path) {
            return TagKey.create(Registry.BLOCK_REGISTRY, GardensOfTheDead.id(path));
        }
    }

    public static class Items {

        public static final TagKey<Item> SOULBlIGHT_STEMS = tag("soulblight_stems");

        private static TagKey<Item> tag(String path) {
            return TagKey.create(Registry.ITEM_REGISTRY, GardensOfTheDead.id(path));
        }
    }
}
