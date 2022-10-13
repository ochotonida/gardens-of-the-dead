package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import net.minecraft.core.Registry;
import net.minecraft.world.item.*;
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
    public static final RegistryObject<Item> SOULBLIGHT_FUNGUS = blockItem(ModBlocks.SOULBLIGHT_FUNGUS);
    public static final RegistryObject<Item> SOULBLIGHT_SPROUTS = blockItem(ModBlocks.SOULBLIGHT_SPROUTS);
    public static final RegistryObject<Item> BLISTERCROWN = blockItem(ModBlocks.BLISTERCROWN);
    public static final RegistryObject<Item> TALL_BLISTERCROWN = blockItem(ModBlocks.TALL_BLISTERCROWN);
    public static final RegistryObject<Item> WHISTLECANE = blockItem(ModBlocks.WHISTLECANE);

    public static final RegistryObject<Item> SOULBLIGHT_STEM = blockItem(ModBlocks.SOULBLIGHT_STEM);
    public static final RegistryObject<Item> STRIPPED_SOULBLIGHT_STEM = blockItem(ModBlocks.STRIPPED_SOULBLIGHT_STEM);
    public static final RegistryObject<Item> SOULBLIGHT_HYPHAE = blockItem(ModBlocks.SOULBLIGHT_HYPHAE);
    public static final RegistryObject<Item> STRIPPED_SOULBLIGHT_HYPHAE = blockItem(ModBlocks.STRIPPED_SOULBLIGHT_HYPHAE);
    public static final RegistryObject<Item> BLIGHTWART_BLOCK = blockItem(ModBlocks.BLIGHTWART_BLOCK);

    public static final RegistryObject<Item> SOULBLIGHT_PLANKS = blockItem(ModBlocks.SOULBLIGHT_PLANKS);
    public static final RegistryObject<Item> SOULBLIGHT_SLAB = blockItem(ModBlocks.SOULBLIGHT_SLAB);
    public static final RegistryObject<Item> SOULBLIGHT_FENCE = blockItem(ModBlocks.SOULBLIGHT_FENCE);
    public static final RegistryObject<Item> SOULBLIGHT_STAIRS = blockItem(ModBlocks.SOULBLIGHT_STAIRS);
    public static final RegistryObject<Item> SOULBLIGHT_BUTTON = blockItem(ModBlocks.SOULBLIGHT_BUTTON);
    public static final RegistryObject<Item> SOULBLIGHT_PRESSURE_PLATE = blockItem(ModBlocks.SOULBLIGHT_PRESSURE_PLATE);
    public static final RegistryObject<Item> SOULBLIGHT_DOOR = ITEMS.register("soulblight_door", () -> new DoubleHighBlockItem(ModBlocks.SOULBLIGHT_DOOR.get(), properties()));
    public static final RegistryObject<Item> SOULBLIGHT_TRAPDOOR = blockItem(ModBlocks.SOULBLIGHT_TRAPDOOR);
    public static final RegistryObject<Item> SOULBLIGHT_FENCE_GATE = blockItem(ModBlocks.SOULBLIGHT_FENCE_GATE);
    public static final RegistryObject<Item> SOULBLIGHT_SIGN = ITEMS.register("soulblight_sign", () -> new SignItem(properties().stacksTo(16), ModBlocks.SOULBLIGHT_SIGN.get(), ModBlocks.SOULBLIGHT_WALL_SIGN.get()));

    private static RegistryObject<Item> blockItem(RegistryObject<? extends Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties()));
    }

    private static Item.Properties properties() {
        return new Item.Properties().tab(CREATIVE_TAB);
    }

    public static void addCompostables() {
        addCompostables(0.3F,
                SOUL_SPORE.get(),
                SOULBLIGHT_SPROUTS.get(),
                BLISTERCROWN.get(),
                TALL_BLISTERCROWN.get()
        );
        addCompostables(0.5F,
                WHISTLECANE.get()
        );
        addCompostables(0.65F,
                SOULBLIGHT_FUNGUS.get()
        );
        addCompostables(0.85F,
                GLOWING_SOUL_SPORE.get(),
                BLIGHTWART_BLOCK.get()
        );
    }

    private static void addCompostables(double chance, ItemLike... items) {
        for (ItemLike item : items) {
            ComposterBlock.COMPOSTABLES.put(item, (float) chance);
        }
    }
}
