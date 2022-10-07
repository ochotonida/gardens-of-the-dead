package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.init.ModBlocks;
import gardensofthedead.common.init.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {

    private static final String CRAFTING_SHAPED = "crafting_shaped";
    private static final String CRAFTING_SHAPELESS = "crafting_shapeless";

    public Recipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        addCraftingRecipes(consumer);
    }

    private void addCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        woodFromLogs(consumer, ModBlocks.SOULBLIGHT_HYPHAE.get(), ModBlocks.SOULBLIGHT_STEM.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_SOULBLIGHT_HYPHAE.get(), ModBlocks.STRIPPED_SOULBLIGHT_STEM.get());

        planksFromLogs(consumer, ModBlocks.SOULBLIGHT_PLANKS.get(), ModTags.Items.SOULBlIGHT_STEMS);

        woodenSlab(consumer, ModBlocks.SOULBLIGHT_SLAB.get(), ModBlocks.SOULBLIGHT_PLANKS.get());
        woodenStairs(consumer, ModBlocks.SOULBLIGHT_STAIRS.get(), ModBlocks.SOULBLIGHT_PLANKS.get());
        door(consumer, ModBlocks.SOULBLIGHT_DOOR.get(), ModBlocks.SOULBLIGHT_PLANKS.get());
        trapdoor(consumer, ModBlocks.SOULBLIGHT_TRAPDOOR.get(), ModBlocks.SOULBLIGHT_PLANKS.get());
        fence(consumer, ModBlocks.SOULBLIGHT_FENCE.get(), ModBlocks.SOULBLIGHT_PLANKS.get());
        fenceGate(consumer, ModBlocks.SOULBLIGHT_FENCE_GATE.get(), ModBlocks.SOULBLIGHT_PLANKS.get());
        button(consumer, ModBlocks.SOULBLIGHT_BUTTON.get(), ModBlocks.SOULBLIGHT_PLANKS.get());
        pressurePlate(consumer, ModBlocks.SOULBLIGHT_PRESSURE_PLATE.get(), ModBlocks.SOULBLIGHT_PLANKS.get());
        sign(consumer, ModBlocks.SOULBLIGHT_SIGN.get(), ModBlocks.SOULBLIGHT_PLANKS.get());
    }

    protected static void woodFromLogs(Consumer<FinishedRecipe> consumer, ItemLike wood, ItemLike log) {
        ShapedRecipeBuilder.shaped(wood, 3)
                .define('#', log)
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", has(log))
                .save(consumer, getRecipeLocation(wood, CRAFTING_SHAPED));
    }

    protected static void planksFromLogs(Consumer<FinishedRecipe> consumer, ItemLike planks, TagKey<Item> tag) {
        ShapelessRecipeBuilder.shapeless(planks, 4)
                .requires(tag)
                .group("planks")
                .unlockedBy("has_logs", has(tag))
                .save(consumer, getRecipeLocation(planks, CRAFTING_SHAPELESS));
    }

    protected static void woodenSlab(Consumer<FinishedRecipe> consumer, ItemLike slab, ItemLike planks) {
        slabBuilder(slab, Ingredient.of(planks))
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
        pressurePlateBuilder(pressurePlate, Ingredient.of(planks))
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

    private static ResourceLocation getRecipeLocation(ItemLike result, String location) {
        // noinspection ConstantConditions
        return GardensOfTheDead.id(location + "/" + ForgeRegistries.ITEMS.getKey(result.asItem()).getPath());
    }
}
