package gardensofthedead.data.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CommonTags {

    public static class Blocks {

        public static final TagKey<Block> FENCE_GATES = tag("fence_gates");
        public static final TagKey<Block> MUSHROOMS = tag("mushrooms");

        private static TagKey<Block> tag(String path) {
            return TagKey.create(Registry.BLOCK_REGISTRY, id(path));
        }
    }

    public static class Items {

        public static final TagKey<Item> FENCE_GATES = tag("fence_gates");
        public static final TagKey<Item> MUSHROOMS = tag("mushrooms");

        private static TagKey<Item> tag(String path) {
            return TagKey.create(Registry.ITEM_REGISTRY, id(path));
        }
    }

    private static ResourceLocation id(String path) {
        return new ResourceLocation("c", path);
    }
}
