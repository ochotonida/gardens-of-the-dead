package gardensofthedead.data.providers;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.registry.ModBlocks;
import gardensofthedead.registry.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {

    private static final String CRAFTING_SHAPED = "crafting_shaped";
    private static final String CRAFTING_SHAPELESS = "crafting_shapeless";

    public RecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        addCraftingRecipes(consumer);
    }

    private void addCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        woodFromLogs(consumer, ModBlocks.SOULBLIGHT_HYPHAE.get(), ModBlocks.SOULBLIGHT_STEM.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_SOULBLIGHT_HYPHAE.get(), ModBlocks.STRIPPED_SOULBLIGHT_STEM.get());

        Block soulblightPlanks = ModBlocks.SOULBLIGHT_PLANKS.get();
        planksFromLogs(consumer, soulblightPlanks, ModTags.Items.SOULBLIGHT_STEMS);

        woodenSlab(consumer, ModBlocks.SOULBLIGHT_SLAB.get(), soulblightPlanks);
        woodenStairs(consumer, ModBlocks.SOULBLIGHT_STAIRS.get(), soulblightPlanks);
        fence(consumer, ModBlocks.SOULBLIGHT_FENCE.get(), soulblightPlanks);
        fenceGate(consumer, ModBlocks.SOULBLIGHT_FENCE_GATE.get(), soulblightPlanks);
        button(consumer, ModBlocks.SOULBLIGHT_BUTTON.get(), soulblightPlanks);
        pressurePlate(consumer, ModBlocks.SOULBLIGHT_PRESSURE_PLATE.get(), soulblightPlanks);
        door(consumer, ModBlocks.SOULBLIGHT_DOOR.get(), soulblightPlanks);
        trapdoor(consumer, ModBlocks.SOULBLIGHT_TRAPDOOR.get(), soulblightPlanks);
        sign(consumer, ModBlocks.SOULBLIGHT_SIGN.get(), soulblightPlanks);

        whistleCaneBlock(consumer);

        Block whistleCaneBlock = ModBlocks.WHISTLECANE_BLOCK.get();
        woodenSlab(consumer, ModBlocks.WHISTLECANE_SLAB.get(), whistleCaneBlock);
        woodenStairs(consumer, ModBlocks.WHISTLECANE_STAIRS.get(), whistleCaneBlock);
        fence(consumer, ModBlocks.WHISTLECANE_FENCE.get(), whistleCaneBlock);
        fenceGate(consumer, ModBlocks.WHISTLECANE_FENCE_GATE.get(), whistleCaneBlock);
        button(consumer, ModBlocks.WHISTLECANE_BUTTON.get(), whistleCaneBlock);
        pressurePlate(consumer, ModBlocks.WHISTLECANE_PRESSURE_PLATE.get(), whistleCaneBlock);
        door(consumer, ModBlocks.WHISTLECANE_DOOR.get(), whistleCaneBlock);
        trapdoor(consumer, ModBlocks.WHISTLECANE_TRAPDOOR.get(), whistleCaneBlock);
        sign(consumer, ModBlocks.WHISTLECANE_SIGN.get(), whistleCaneBlock);

        blistercrown(consumer);
    }

    protected static void whistleCaneBlock(Consumer<FinishedRecipe> consumer) {
        Block whistleCane = ModBlocks.WHISTLECANE.get();
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHISTLECANE_BLOCK.get(), 2)
                .define('#', whistleCane)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_whistlecane", has(whistleCane))
                .save(consumer, getRecipeLocation(whistleCane, CRAFTING_SHAPED));
    }

    protected static void woodFromLogs(Consumer<FinishedRecipe> consumer, ItemLike wood, ItemLike log) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wood, 3)
                .define('#', log)
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", has(log))
                .save(consumer, getRecipeLocation(wood, CRAFTING_SHAPED));
    }

    protected static void planksFromLogs(Consumer<FinishedRecipe> consumer, ItemLike planks, TagKey<Item> tag) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks, 4)
                .requires(tag)
                .group("planks")
                .unlockedBy("has_logs", has(tag))
                .save(consumer, getRecipeLocation(planks, CRAFTING_SHAPELESS));
    }

    protected static void woodenSlab(Consumer<FinishedRecipe> consumer, ItemLike slab, ItemLike planks) {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab, Ingredient.of(planks))
                .group("wooden_slab")
                .unlockedBy("has_planks", has(planks))
                .save(consumer, getRecipeLocation(slab, CRAFTING_SHAPED));
    }

    protected static void woodenStairs(Consumer<FinishedRecipe> consumer, ItemLike stairs, ItemLike planks) {
        stairBuilder(stairs, Ingredient.of(planks))
                .group("wooden_stairs")
                .unlockedBy("has_planks", has(planks))
                .save(consumer, getRecipeLocation(stairs, CRAFTING_SHAPED));
    }

    protected static void door(Consumer<FinishedRecipe> consumer, ItemLike door, ItemLike planks) {
        doorBuilder(door, Ingredient.of(planks))
                .group("wooden_door")
                .unlockedBy("has_planks", has(planks))
                .save(consumer, getRecipeLocation(door, CRAFTING_SHAPED));
    }

    protected static void trapdoor(Consumer<FinishedRecipe> consumer, ItemLike trapdoor, ItemLike planks) {
        trapdoorBuilder(trapdoor, Ingredient.of(planks))
                .group("wooden_trapdoor")
                .unlockedBy("has_planks", has(planks))
                .save(consumer, getRecipeLocation(trapdoor, CRAFTING_SHAPED));
    }

    protected static void fence(Consumer<FinishedRecipe> consumer, ItemLike fence, ItemLike planks) {
        fenceBuilder(fence, Ingredient.of(planks))
                .group("wooden_fence")
                .unlockedBy("has_planks", has(planks))
                .save(consumer, getRecipeLocation(fence, CRAFTING_SHAPED));
    }

    protected static void fenceGate(Consumer<FinishedRecipe> consumer, ItemLike fenceGate, ItemLike planks) {
        fenceGateBuilder(fenceGate, Ingredient.of(planks))
                .group("wooden_fence_gate")
                .unlockedBy("has_planks", has(planks))
                .save(consumer, getRecipeLocation(fenceGate, CRAFTING_SHAPED));
    }

    protected static void button(Consumer<FinishedRecipe> consumer, ItemLike button, ItemLike planks) {
        buttonBuilder(button, Ingredient.of(planks))
                .group("wooden_button")
                .unlockedBy("has_planks", has(planks))
                .save(consumer, getRecipeLocation(button, CRAFTING_SHAPELESS));
    }

    protected static void pressurePlate(Consumer<FinishedRecipe> consumer, ItemLike pressurePlate, ItemLike planks) {
        pressurePlateBuilder(RecipeCategory.REDSTONE, pressurePlate, Ingredient.of(planks))
                .group("wooden_pressure_plate")
                .unlockedBy("has_planks", has(planks))
                .save(consumer, getRecipeLocation(pressurePlate, CRAFTING_SHAPED));
    }

    protected static void sign(Consumer<FinishedRecipe> consumer, ItemLike sign, ItemLike planks) {
        signBuilder(sign, Ingredient.of(planks))
                .group("wooden_sign")
                .unlockedBy("has_planks", has(planks))
                .save(consumer, getRecipeLocation(sign, CRAFTING_SHAPED));
    }

    protected static void blistercrown(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.BLISTERCROWN.get(), 3)
                .requires(ModBlocks.TALL_BLISTERCROWN.get())
                .unlockedBy("has_blistercrown", has(ModBlocks.TALL_BLISTERCROWN.get()))
                .save(consumer, getRecipeLocation(ModBlocks.BLISTERCROWN.get(), CRAFTING_SHAPELESS));
    }

    private static ResourceLocation getRecipeLocation(ItemLike result, String location) {
        // noinspection ConstantConditions
        return GardensOfTheDead.id(location + "/" + ForgeRegistries.ITEMS.getKey(result.asItem()).getPath());
    }
}
