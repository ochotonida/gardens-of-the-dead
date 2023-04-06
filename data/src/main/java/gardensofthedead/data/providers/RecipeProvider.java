package gardensofthedead.data.providers;

import gardensofthedead.data.registry.ModBlockFamilies;
import gardensofthedead.registry.ModBlocks;
import gardensofthedead.registry.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {

    public RecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        generateForEnabledBlockFamilies(consumer, FeatureFlags.VANILLA_SET);
        addCraftingRecipes(consumer);
    }

    @Override
    protected void generateForEnabledBlockFamilies(Consumer<FinishedRecipe> consumer, FeatureFlagSet enabledFeatures) {
        ModBlockFamilies.getAllFamilies()
                .filter(family -> family.shouldGenerateRecipe(enabledFeatures))
                .forEach(family -> generateRecipes(consumer, family));
    }

    private void addCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        woodFromLogs(consumer, ModBlocks.SOULBLIGHT_HYPHAE.get(), ModBlocks.SOULBLIGHT_STEM.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_SOULBLIGHT_HYPHAE.get(), ModBlocks.STRIPPED_SOULBLIGHT_STEM.get());

        planksFromLogs(consumer, ModBlocks.SOULBLIGHT_PLANKS.get(), ModTags.Items.SOULBLIGHT_STEMS, 4);
        planksFromLogs(consumer, ModBlocks.WHISTLECANE_PLANKS.get(), ModTags.Items.WHISTLECANE_BLOCKS, 2);

        whistlecaneBlock(consumer);
        blistercrown(consumer);
    }

    protected void whistlecaneBlock(Consumer<FinishedRecipe> consumer) {
        Block whistlecane = ModBlocks.WHISTLECANE.get();
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHISTLECANE_BLOCK.get())
                .define('#', whistlecane)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_whistlecane", has(whistlecane))
                .save(consumer);
    }

    protected static void blistercrown(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.BLISTERCROWN.get(), 3)
                .requires(ModBlocks.TALL_BLISTERCROWN.get())
                .unlockedBy("has_blistercrown", has(ModBlocks.TALL_BLISTERCROWN.get()))
                .save(consumer);
    }
}
