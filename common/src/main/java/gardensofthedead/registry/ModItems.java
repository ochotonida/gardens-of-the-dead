package gardensofthedead.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import gardensofthedead.GardensOfTheDead;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(GardensOfTheDead.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> SOUL_SPORE = blockItem(ModBlocks.SOUL_SPORE);
    public static final RegistrySupplier<Item> GLOWING_SOUL_SPORE = blockItem(ModBlocks.GLOWING_SOUL_SPORE);
    public static final RegistrySupplier<Item> SOULBLIGHT_FUNGUS = blockItem(ModBlocks.SOULBLIGHT_FUNGUS);
    public static final RegistrySupplier<Item> SOULBLIGHT_SPROUTS = blockItem(ModBlocks.SOULBLIGHT_SPROUTS);
    public static final RegistrySupplier<Item> BLISTERCROWN = blockItem(ModBlocks.BLISTERCROWN);
    public static final RegistrySupplier<Item> TALL_BLISTERCROWN = blockItem(ModBlocks.TALL_BLISTERCROWN);
    public static final RegistrySupplier<Item> WHISTLECANE = blockItem(ModBlocks.WHISTLECANE);

    public static final RegistrySupplier<Item> SOULBLIGHT_STEM = blockItem(ModBlocks.SOULBLIGHT_STEM);
    public static final RegistrySupplier<Item> STRIPPED_SOULBLIGHT_STEM = blockItem(ModBlocks.STRIPPED_SOULBLIGHT_STEM);
    public static final RegistrySupplier<Item> SOULBLIGHT_HYPHAE = blockItem(ModBlocks.SOULBLIGHT_HYPHAE);
    public static final RegistrySupplier<Item> STRIPPED_SOULBLIGHT_HYPHAE = blockItem(ModBlocks.STRIPPED_SOULBLIGHT_HYPHAE);
    public static final RegistrySupplier<Item> BLIGHTWART_BLOCK = blockItem(ModBlocks.BLIGHTWART_BLOCK);

    public static final RegistrySupplier<Item> SOULBLIGHT_PLANKS = blockItem(ModBlocks.SOULBLIGHT_PLANKS);
    public static final RegistrySupplier<Item> SOULBLIGHT_SLAB = blockItem(ModBlocks.SOULBLIGHT_SLAB);
    public static final RegistrySupplier<Item> SOULBLIGHT_STAIRS = blockItem(ModBlocks.SOULBLIGHT_STAIRS);
    public static final RegistrySupplier<Item> SOULBLIGHT_FENCE = blockItem(ModBlocks.SOULBLIGHT_FENCE);
    public static final RegistrySupplier<Item> SOULBLIGHT_FENCE_GATE = blockItem(ModBlocks.SOULBLIGHT_FENCE_GATE);
    public static final RegistrySupplier<Item> SOULBLIGHT_BUTTON = blockItem(ModBlocks.SOULBLIGHT_BUTTON);
    public static final RegistrySupplier<Item> SOULBLIGHT_PRESSURE_PLATE = blockItem(ModBlocks.SOULBLIGHT_PRESSURE_PLATE);
    public static final RegistrySupplier<Item> SOULBLIGHT_DOOR = ITEMS.register("soulblight_door", () -> new DoubleHighBlockItem(ModBlocks.SOULBLIGHT_DOOR.get(), new Item.Properties()));
    public static final RegistrySupplier<Item> SOULBLIGHT_TRAPDOOR = blockItem(ModBlocks.SOULBLIGHT_TRAPDOOR);
    public static final RegistrySupplier<Item> SOULBLIGHT_SIGN = ITEMS.register("soulblight_sign", () -> sign(ModBlocks.SOULBLIGHT_SIGN, ModBlocks.SOULBLIGHT_WALL_SIGN));
    public static final RegistrySupplier<Item> SOULBLIGHT_HANGING_SIGN = ITEMS.register("soulblight_hanging_sign", () -> hangingSign(ModBlocks.SOULBLIGHT_HANGING_SIGN, ModBlocks.SOULBLIGHT_WALL_HANGING_SIGN));

    public static final RegistrySupplier<Item> WHISTLECANE_BLOCK = blockItem(ModBlocks.WHISTLECANE_BLOCK);
    public static final RegistrySupplier<Item> WHISTLECANE_SLAB = blockItem(ModBlocks.WHISTLECANE_SLAB);
    public static final RegistrySupplier<Item> WHISTLECANE_STAIRS = blockItem(ModBlocks.WHISTLECANE_STAIRS);
    public static final RegistrySupplier<Item> WHISTLECANE_FENCE = blockItem(ModBlocks.WHISTLECANE_FENCE);
    public static final RegistrySupplier<Item> WHISTLECANE_FENCE_GATE = blockItem(ModBlocks.WHISTLECANE_FENCE_GATE);
    public static final RegistrySupplier<Item> WHISTLECANE_BUTTON = blockItem(ModBlocks.WHISTLECANE_BUTTON);
    public static final RegistrySupplier<Item> WHISTLECANE_PRESSURE_PLATE = blockItem(ModBlocks.WHISTLECANE_PRESSURE_PLATE);
    public static final RegistrySupplier<Item> WHISTLECANE_DOOR = ITEMS.register("whistlecane_door", () -> new DoubleHighBlockItem(ModBlocks.WHISTLECANE_DOOR.get(), new Item.Properties()));
    public static final RegistrySupplier<Item> WHISTLECANE_TRAPDOOR = blockItem(ModBlocks.WHISTLECANE_TRAPDOOR);
    public static final RegistrySupplier<Item> WHISTLECANE_SIGN = ITEMS.register("whistlecane_sign", () -> sign(ModBlocks.WHISTLECANE_SIGN, ModBlocks.WHISTLECANE_WALL_SIGN));
    public static final RegistrySupplier<Item> WHISTLECANE_HANGING_SIGN = ITEMS.register("whistlecane_hanging_sign", () -> hangingSign(ModBlocks.WHISTLECANE_HANGING_SIGN, ModBlocks.WHISTLECANE_WALL_HANGING_SIGN));

    @SuppressWarnings("UnstableApiUsage")
    public static final CreativeTabRegistry.TabSupplier CREATIVE_TAB = CreativeTabRegistry.create(GardensOfTheDead.id("main"),
            builder -> builder
                    .icon(() -> new ItemStack(ModItems.GLOWING_SOUL_SPORE.get()))
                    .title(Component.translatable("%s.creative_tab".formatted(GardensOfTheDead.MOD_ID)))
                    .displayItems(
                            (featureFlagSet, output, hasOp) -> BuiltInRegistries.ITEM.keySet()
                                    .stream()
                                    .filter(key -> key.getNamespace().equals(GardensOfTheDead.MOD_ID))
                                    .sorted()
                                    .map(BuiltInRegistries.ITEM::get)
                                    .filter(item -> item.requiredFeatures().isSubsetOf(featureFlagSet))
                                    .forEach(output::accept)
                    )
    );

    private static RegistrySupplier<Item> blockItem(RegistrySupplier<? extends Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static SignItem sign(RegistrySupplier<? extends Block> standingSign, RegistrySupplier<? extends Block> wallSign) {
        return new SignItem(new Item.Properties().stacksTo(16), standingSign.get(), wallSign.get());
    }

    private static HangingSignItem hangingSign(RegistrySupplier<? extends Block> standingSign, RegistrySupplier<? extends Block> wallSign) {
        return new HangingSignItem(standingSign.get(), wallSign.get(), new Item.Properties().stacksTo(16).requiredFeatures(FeatureFlags.UPDATE_1_20));
    }

    public static void addCompostables(BiConsumer<ItemLike, Float> consumer) {
        addCompostables(
                consumer,
                0.3F,
                SOUL_SPORE.get(),
                SOULBLIGHT_SPROUTS.get(),
                BLISTERCROWN.get(),
                TALL_BLISTERCROWN.get()
        );
        addCompostables(
                consumer,
                0.5F,
                WHISTLECANE.get()
        );
        addCompostables(
                consumer,
                0.65F,
                SOULBLIGHT_FUNGUS.get()
        );
        addCompostables(
                consumer,
                0.85F,
                GLOWING_SOUL_SPORE.get(),
                BLIGHTWART_BLOCK.get()
        );
    }

    private static void addCompostables(BiConsumer<ItemLike, Float> consumer, double chance, ItemLike... items) {
        for (ItemLike item : items) {
            consumer.accept(item, (float) chance);
        }
    }
}
