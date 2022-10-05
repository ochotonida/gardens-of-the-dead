package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.blocks.SoulSporeBlock;
import gardensofthedead.common.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public class BlockStates extends BlockStateProvider {

    private static final String CUTOUT = "cutout";

    public BlockStates(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, GardensOfTheDead.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        models().getBuilder("cross_mirrored")
                .ao(false)
                .texture("particle", "#cross")
                .element().from(0.8F, 0, 8).to(15.2F, 16, 8).shade(false)
                .rotation().origin(8, 8, 8).axis(Direction.Axis.Y).angle(45).rescale(true).end()
                .face(Direction.NORTH).uvs(16, 0, 0, 16).texture("#cross").end()
                .face(Direction.SOUTH).uvs(16, 0, 0, 16).texture("#cross").end()
                .end()
                .element().from(8, 0, 0.8F).to(8, 16, 15.2F).shade(false)
                .rotation().origin(8, 8, 8).axis(Direction.Axis.Y).angle(45).rescale(true).end()
                .face(Direction.WEST).uvs(16, 0, 0, 16).texture("#cross").end()
                .face(Direction.EAST).uvs(16, 0, 0, 16).texture("#cross").end()
                .end();

        getVariantBuilder(ModBlocks.SOUL_SPORE.get())
                .partialState().with(SoulSporeBlock.TOP, true).addModels(cross("soul_spore_top"), crossMirrored("soul_spore_top"))
                .partialState().with(SoulSporeBlock.TOP, false).addModels(cross("soul_spore"), crossMirrored("soul_spore"));
        simpleBlock(ModBlocks.GLOWING_SOUL_SPORE.get(), cross("glowing_soul_spore"), crossMirrored("glowing_soul_spore"));

        simpleBlock(ModBlocks.SOULBLIGHT_FUNGUS.get(),
                cross("soulblight_fungus"),
                cross("soulblight_fungus_tall"),
                cross("soulblight_fungus_short")
        );

        pottedPlant(ModBlocks.POTTED_SOUL_SPORE.get(), "potted_soul_spore");
        pottedPlant(ModBlocks.POTTED_GLOWING_SOUL_SPORE.get(), "potted_glowing_soul_spore");
        pottedPlant(ModBlocks.POTTED_SOULBLIGHT_FUNGUS.get(), "soulblight_fungus");
    }

    private void pottedPlant(Block pottedPlant, String textureName) {
        // noinspection ConstantConditions
        simpleBlock(pottedPlant, models()
                .withExistingParent(ForgeRegistries.BLOCKS.getKey(pottedPlant).getPath(), "flower_pot_cross")
                .texture("plant", blockTexture(textureName))
                .renderType(CUTOUT)
        );
    }

    private ConfiguredModel cross(String textureName) {
        return new ConfiguredModel(models().cross(textureName, blockTexture(textureName)).renderType(CUTOUT));
    }

    private ConfiguredModel crossMirrored(String textureName) {
        ResourceLocation cross = blockTexture(textureName);
        return new ConfiguredModel(models().singleTexture(textureName + "_mirrored", modLoc(BLOCK_FOLDER + "/cross_mirrored"), "cross", cross).renderType(CUTOUT));
    }

    private ResourceLocation blockTexture(String textureName) {
        return modLoc(BLOCK_FOLDER + "/" + textureName);
    }
}
