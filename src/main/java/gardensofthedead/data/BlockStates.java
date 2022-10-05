package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.blocks.SoulSporeBaseBlock;
import gardensofthedead.common.blocks.SoulSporeBlock;
import gardensofthedead.common.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
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
        createCrossModels();

        getVariantBuilder(ModBlocks.SOUL_SPORE.get())
                .partialState()
                        .with(SoulSporeBlock.TOP, true)
                        .with(SoulSporeBlock.DIRECTION, Direction.UP)
                        .addModels(cross("soul_spore_top"), crossMirrored("soul_spore_top"))
                .partialState()
                        .with(SoulSporeBlock.TOP, true)
                        .with(SoulSporeBlock.DIRECTION, Direction.DOWN)
                        .addModels(crossFlipped("soul_spore_top"), crossMirroredFlipped("soul_spore_top"))
                .partialState()
                        .with(SoulSporeBlock.TOP, false)
                        .addModels(cross("soul_spore"), crossMirrored("soul_spore"));

        getVariantBuilder(ModBlocks.GLOWING_SOUL_SPORE.get())
                .partialState()
                        .with(SoulSporeBaseBlock.DIRECTION, Direction.UP)
                        .addModels(cross("glowing_soul_spore"), crossMirrored("glowing_soul_spore"))
                .partialState()
                        .with(SoulSporeBaseBlock.DIRECTION, Direction.DOWN)
                        .addModels(crossFlipped("glowing_soul_spore"), crossMirroredFlipped("glowing_soul_spore"));

        simpleBlock(ModBlocks.SOULBLIGHT_FUNGUS.get(),
                cross("soulblight_fungus"),
                cross("soulblight_fungus_tall"),
                cross("soulblight_fungus_short")
        );

        log(ModBlocks.SOULBLIGHT_STEM.get());
        log(ModBlocks.STRIPPED_SOULBLIGHT_STEM.get());
        wood(ModBlocks.SOULBLIGHT_HYPHAE.get());
        wood(ModBlocks.STRIPPED_SOULBLIGHT_HYPHAE.get());

        pottedPlant(ModBlocks.POTTED_SOUL_SPORE.get(), "potted_soul_spore");
        pottedPlant(ModBlocks.POTTED_GLOWING_SOUL_SPORE.get(), "potted_glowing_soul_spore");
        pottedPlant(ModBlocks.POTTED_SOULBLIGHT_FUNGUS.get());
    }

    private void createCrossModels() {
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

        models().getBuilder("cross_flipped")
                .ao(false)
                .texture("particle", "#cross")
                .element().from(0.8F, 0, 8).to(15.2F, 16, 8).shade(false)
                .rotation().origin(8, 8, 8).axis(Direction.Axis.Y).angle(45).rescale(true).end()
                .face(Direction.NORTH).uvs(0, 16, 16, 0).texture("#cross").end()
                .face(Direction.SOUTH).uvs(0, 16, 16, 0).texture("#cross").end()
                .end()
                .element().from(8, 0, 0.8F).to(8, 16, 15.2F).shade(false)
                .rotation().origin(8, 8, 8).axis(Direction.Axis.Y).angle(45).rescale(true).end()
                .face(Direction.WEST).uvs(0, 16, 16, 0).texture("#cross").end()
                .face(Direction.EAST).uvs(0, 16, 16, 0).texture("#cross").end()
                .end();

        models().getBuilder("cross_mirrored_flipped")
                .ao(false)
                .texture("particle", "#cross")
                .element().from(0.8F, 0, 8).to(15.2F, 16, 8).shade(false)
                .rotation().origin(8, 8, 8).axis(Direction.Axis.Y).angle(45).rescale(true).end()
                .face(Direction.NORTH).uvs(16, 16, 0, 0).texture("#cross").end()
                .face(Direction.SOUTH).uvs(16, 16, 0, 0).texture("#cross").end()
                .end()
                .element().from(8, 0, 0.8F).to(8, 16, 15.2F).shade(false)
                .rotation().origin(8, 8, 8).axis(Direction.Axis.Y).angle(45).rescale(true).end()
                .face(Direction.WEST).uvs(16, 16, 0, 0).texture("#cross").end()
                .face(Direction.EAST).uvs(16, 16, 0, 0).texture("#cross").end()
                .end();
    }

    private void log(RotatedPillarBlock wood) {
        // noinspection ConstantConditions
        String name = ForgeRegistries.BLOCKS.getKey(wood).getPath();
        ResourceLocation side = blockTexture(name);
        ResourceLocation top = blockTexture(name + "_top");
        axisBlock(wood, side, top);
    }

    private void wood(RotatedPillarBlock wood) {
        // noinspection ConstantConditions
        String name = ForgeRegistries.BLOCKS.getKey(wood).getPath()
                .replace("wood", "log")
                .replace("hyphae", "stem");
        ResourceLocation side = blockTexture(name);
        axisBlock(wood, side, side);
    }

    private void pottedPlant(Block pottedPlant) {
        // noinspection ConstantConditions
        String id = ForgeRegistries.BLOCKS.getKey(pottedPlant).getPath().replace("potted_", "");
        pottedPlant(pottedPlant, id);
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

    private ConfiguredModel crossFlipped(String textureName) {
        ResourceLocation cross = blockTexture(textureName);
        return new ConfiguredModel(models().singleTexture(textureName + "_flipped", modLoc(BLOCK_FOLDER + "/cross_flipped"), "cross", cross).renderType(CUTOUT));
    }

    private ConfiguredModel crossMirroredFlipped(String textureName) {
        ResourceLocation cross = blockTexture(textureName);
        return new ConfiguredModel(models().singleTexture(textureName + "_mirrored_flipped", modLoc(BLOCK_FOLDER + "/cross_mirrored_flipped"), "cross", cross).renderType(CUTOUT));
    }

    private ResourceLocation blockTexture(String textureName) {
        return modLoc(BLOCK_FOLDER + "/" + textureName);
    }
}
