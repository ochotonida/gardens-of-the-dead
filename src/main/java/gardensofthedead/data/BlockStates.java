package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.blocks.SoulSporeBaseBlock;
import gardensofthedead.common.blocks.SoulSporeBlock;
import gardensofthedead.common.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
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

        simplePlant(ModBlocks.SOULBLIGHT_SPROUTS.get());
        simplePlant(ModBlocks.BLISTERCROWN.get());
        randomlyMirroredDoublePlant(ModBlocks.TALL_BLISTERCROWN.get());

        log(ModBlocks.SOULBLIGHT_STEM.get());
        log(ModBlocks.STRIPPED_SOULBLIGHT_STEM.get());
        wood(ModBlocks.SOULBLIGHT_HYPHAE.get());
        wood(ModBlocks.STRIPPED_SOULBLIGHT_HYPHAE.get());

        simpleBlock(ModBlocks.BLIGHTWART_BLOCK.get());

        pottedPlant(ModBlocks.POTTED_SOUL_SPORE.get(), "potted_soul_spore");
        pottedPlant(ModBlocks.POTTED_GLOWING_SOUL_SPORE.get(), "potted_glowing_soul_spore");
        pottedPlant(ModBlocks.POTTED_SOULBLIGHT_FUNGUS.get());
        pottedPlant(ModBlocks.POTTED_SOULBLIGHT_SPROUTS.get(), "potted_soulblight_sprouts");
        pottedPlant(ModBlocks.POTTED_BLISTERCROWN.get());

        ResourceLocation soulblightPlanksTexture = blockTexture("soulblight_planks");
        simpleBlock(ModBlocks.SOULBLIGHT_PLANKS.get());
        slabBlock(ModBlocks.SOULBLIGHT_SLAB.get(), soulblightPlanksTexture, soulblightPlanksTexture);
        stairsBlock(ModBlocks.SOULBLIGHT_STAIRS.get(), soulblightPlanksTexture);
        buttonBlock(ModBlocks.SOULBLIGHT_BUTTON.get(), soulblightPlanksTexture);
        pressurePlateBlock(ModBlocks.SOULBLIGHT_PRESSURE_PLATE.get(), soulblightPlanksTexture);
        fenceBlock(ModBlocks.SOULBLIGHT_FENCE.get(), soulblightPlanksTexture);
        fenceGateBlock(ModBlocks.SOULBLIGHT_FENCE_GATE.get(), soulblightPlanksTexture);
        signBlock(ModBlocks.SOULBLIGHT_SIGN.get(), ModBlocks.SOULBLIGHT_WALL_SIGN.get(), soulblightPlanksTexture);
        doorBlock(ModBlocks.SOULBLIGHT_DOOR.get());
        trapdoorBlock(ModBlocks.SOULBLIGHT_TRAPDOOR.get());
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

    public void doorBlock(DoorBlock block) {
        doorBlock(block, CUTOUT);
    }

    public void doorBlock(DoorBlock block, String renderType) {
        doorBlockWithRenderType(block, blockTexture(getName(block) + "_bottom"), blockTexture(getName(block) + "_top"), renderType);
        itemModels().basicItem(block.asItem());
    }

    public void trapdoorBlock(TrapDoorBlock block) {
        trapdoorBlock(block, CUTOUT);
    }

    public void trapdoorBlock(TrapDoorBlock block, String renderType) {
        trapdoorBlockWithRenderType(block, blockTexture(getName(block)), true, renderType);

        itemModels().withExistingParent(getName(block), modLoc("%s/%s_bottom".formatted(BLOCK_FOLDER, getName(block))));
    }

    @Override
    public void buttonBlock(ButtonBlock block, ResourceLocation texture) {
        super.buttonBlock(block, texture);
        itemModels().buttonInventory(getName(block), texture);
    }

    @Override
    public void fenceBlock(FenceBlock block, ResourceLocation texture) {
        super.fenceBlock(block, texture);
        itemModels().fenceInventory(getName(block), texture);
    }

    @Override
    public void signBlock(StandingSignBlock signBlock, WallSignBlock wallSignBlock, ResourceLocation texture) {
        super.signBlock(signBlock, wallSignBlock, texture);
        itemModels().basicItem(signBlock.asItem());
    }

    private void log(RotatedPillarBlock wood) {
        String name = getName(wood);
        ResourceLocation side = blockTexture(name);
        ResourceLocation top = blockTexture(name + "_top");
        axisBlock(wood, side, top);
    }

    private void wood(RotatedPillarBlock wood) {
        String name = getName(wood)
                .replace("wood", "log")
                .replace("hyphae", "stem");
        ResourceLocation side = blockTexture(name);
        axisBlock(wood, side, side);
    }

    private void pottedPlant(Block pottedPlant) {
        String id = getName(pottedPlant).replace("potted_", "");
        pottedPlant(pottedPlant, id);
    }

    private void pottedPlant(Block pottedPlant, String textureName) {
        simpleBlock(pottedPlant, models()
                .withExistingParent(getName(pottedPlant), "flower_pot_cross")
                .texture("plant", blockTexture(textureName))
                .renderType(CUTOUT)
        );
    }

    private void simplePlant(Block block) {
        simpleBlock(block, cross(getName(block)));
        generatedItem(block);
    }

    private void randomlyMirroredDoublePlant(Block block) {
        String topTexture = getName(block) + "_top";
        String bottomTexture = getName(block) + "_bottom";
        getVariantBuilder(block)
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
                .addModels(cross(topTexture), crossMirrored(topTexture))
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
                .addModels(cross(bottomTexture), crossMirrored(bottomTexture));
        generatedItem(block.asItem(), GardensOfTheDead.id(BLOCK_FOLDER + "/" + topTexture));
    }

    private void generatedItem(Block block) {
        generatedItem(block.asItem(), GardensOfTheDead.id(BLOCK_FOLDER + "/" + getName(block)));
    }

    private void generatedItem(Item item, ResourceLocation texture) {
        // noinspection ConstantConditions
        itemModels().getBuilder(ForgeRegistries.ITEMS.getKey(item).toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", texture);
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

    private String getName(Block block) {
        // noinspection ConstantConditions
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }
}
