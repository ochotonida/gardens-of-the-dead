package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registry.ITEM_REGISTRY, GardensOfTheDead.MODID);

    public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab(GardensOfTheDead.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(GLOWING_SOUL_SPORE.get());
        }
    };

    public static final RegistryObject<Item> SOUL_SPORE = blockItem(ModBlocks.SOUL_SPORE);
    public static final RegistryObject<Item> GLOWING_SOUL_SPORE = blockItem(ModBlocks.GLOWING_SOUL_SPORE);

    private static RegistryObject<Item> blockItem(RegistryObject<? extends Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties()));
    }

    private static Item.Properties properties() {
        return new Item.Properties().tab(CREATIVE_TAB);
    }

    public static void addCompostables() {
        addCompostable(0.3F, SOUL_SPORE.get());
        addCompostable(0.85F, GLOWING_SOUL_SPORE.get());
    }

    private static void addCompostable(double chance, ItemLike item) {
        ComposterBlock.COMPOSTABLES.put(item, (float) chance);
    }
}
