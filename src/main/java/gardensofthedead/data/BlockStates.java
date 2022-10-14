package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.block.SoulSporeBaseBlock;
import gardensofthedead.common.block.SoulSporeBlock;
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

import java.util.Objects;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public class BlockStates extends BlockStateProvider {

    private static final String CUTOUT = "cutout";

    public BlockStates(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, GardensOfTheDead.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        createCrossModels();

        addSoulSpore();
        addWhistleCane();

        simplePlantWithItem(ModBlocks.SOULBLIGHT_FUNGUS.get());
        simplePlantWithItem(ModBlocks.SOULBLIGHT_SPROUTS.get());
        simplePlantWithItem(ModBlocks.BLISTERCROWN.get());
        randomlyMirroredDoublePlantWithItem(ModBlocks.TALL_BLISTERCROWN.get());

        logWithItem(ModBlocks.SOULBLIGHT_STEM.get());
        logWithItem(ModBlocks.STRIPPED_SOULBLIGHT_STEM.get());
        woodWithItem(ModBlocks.SOULBLIGHT_HYPHAE.get());
        woodWithItem(ModBlocks.STRIPPED_SOULBLIGHT_HYPHAE.get());

        simpleBlockWithItem(ModBlocks.BLIGHTWART_BLOCK.get());

        pottedPlant(ModBlocks.POTTED_SOUL_SPORE.get(), "potted_soul_spore");
        pottedPlant(ModBlocks.POTTED_GLOWING_SOUL_SPORE.get(), "potted_glowing_soul_spore");
        pottedPlant(ModBlocks.POTTED_SOULBLIGHT_FUNGUS.get());
        pottedPlant(ModBlocks.POTTED_SOULBLIGHT_SPROUTS.get(), "potted_soulblight_sprouts");
        pottedPlant(ModBlocks.POTTED_BLISTERCROWN.get());

        ResourceLocation soulblightPlanksTexture = blockTexture("soulblight_planks");
        simpleBlockWithItem(ModBlocks.SOULBLIGHT_PLANKS.get());
        slabWithItem(ModBlocks.SOULBLIGHT_SLAB.get(), soulblightPlanksTexture);
        stairsWithItem(ModBlocks.SOULBLIGHT_STAIRS.get(), soulblightPlanksTexture);
        buttonWithItem(ModBlocks.SOULBLIGHT_BUTTON.get(), soulblightPlanksTexture);
        pressurePlateWithItem(ModBlocks.SOULBLIGHT_PRESSURE_PLATE.get(), soulblightPlanksTexture);
        fenceWithItem(ModBlocks.SOULBLIGHT_FENCE.get(), soulblightPlanksTexture);
        fenceGateWithItem(ModBlocks.SOULBLIGHT_FENCE_GATE.get(), soulblightPlanksTexture);
        signWithItem(ModBlocks.SOULBLIGHT_SIGN.get(), ModBlocks.SOULBLIGHT_WALL_SIGN.get(), soulblightPlanksTexture);
        doorWithItem(ModBlocks.SOULBLIGHT_DOOR.get());
        trapdoorWithItem(ModBlocks.SOULBLIGHT_TRAPDOOR.get());
    }

    private void addSoulSpore() {
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
        generatedItem(ModBlocks.SOUL_SPORE.get());

        getVariantBuilder(ModBlocks.GLOWING_SOUL_SPORE.get())
                .partialState()
                .with(SoulSporeBaseBlock.DIRECTION, Direction.UP)
                .addModels(cross("glowing_soul_spore"), crossMirrored("glowing_soul_spore"))
                .partialState()
                .with(SoulSporeBaseBlock.DIRECTION, Direction.DOWN)
                .addModels(crossFlipped("glowing_soul_spore"), crossMirroredFlipped("glowing_soul_spore"));
        generatedItem(ModBlocks.GLOWING_SOUL_SPORE.get());
    }

    private void addWhistleCane() {
        ModelFile builder = models().withExistingParent(getName(ModBlocks.WHISTLECANE.get()), BLOCK_FOLDER + "/block")
                .texture("cane", blockTexture(ModBlocks.WHISTLECANE.get()).toString())
                .texture("particle", "#cane")
                .element()
                .from(5, 0, 5).to(11, 16, 11)
                .allFaces((direction, faceBuilder) -> {
                    if (direction.getAxis() == Direction.Axis.Y) {
                        faceBuilder.uvs(6, 0, 12, 6);
                    } else {
                        faceBuilder.uvs(0, 0, 6, 16);
                    }
                    faceBuilder.texture("#cane");
                }).end();

        simpleBlock(ModBlocks.WHISTLECANE.get(), builder);
        itemModels().basicItem(ModBlocks.WHISTLECANE.get().asItem());
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

    public void slabWithItem(SlabBlock block, ResourceLocation texture) {
        slabBlock(block, texture, texture);
        simpleBlockItem(block);
    }

    public void stairsWithItem(StairBlock block, ResourceLocation texture) {
        stairsBlock(block, texture);
        simpleBlockItem(block);
    }

    public void doorWithItem(DoorBlock block) {
        doorWithItem(block, CUTOUT);
    }

    public void doorWithItem(DoorBlock block, String renderType) {
        doorBlockWithRenderType(block, blockTexture(getName(block) + "_bottom"), blockTexture(getName(block) + "_top"), renderType);
        itemModels().basicItem(block.asItem());
    }

    public void trapdoorWithItem(TrapDoorBlock block) {
        trapdoorWithItem(block, CUTOUT);
    }

    public void trapdoorWithItem(TrapDoorBlock trapDoor, String renderType) {
        trapdoorBlockWithRenderType(trapDoor, blockTexture(getName(trapDoor)), true, renderType);

        itemModels().withExistingParent(getName(trapDoor), modLoc("%s/%s_bottom".formatted(BLOCK_FOLDER, getName(trapDoor))));
    }

    public void buttonWithItem(ButtonBlock block, ResourceLocation texture) {
        buttonBlock(block, texture);
        itemModels().buttonInventory(getName(block), texture);
    }

    public void pressurePlateWithItem(PressurePlateBlock block, ResourceLocation texture) {
        pressurePlateBlock(block, texture);
        simpleBlockItem(block);
    }

    public void fenceWithItem(FenceBlock block, ResourceLocation texture) {
        fenceBlock(block, texture);
        itemModels().fenceInventory(getName(block), texture);
    }

    public void fenceGateWithItem(FenceGateBlock block, ResourceLocation texture) {
        fenceGateBlock(block, texture);
        simpleBlockItem(block);
    }

    public void signWithItem(StandingSignBlock signBlock, WallSignBlock wallSignBlock, ResourceLocation texture) {
        signBlock(signBlock, wallSignBlock, texture);
        itemModels().basicItem(signBlock.asItem());
    }

    private void logWithItem(RotatedPillarBlock wood) {
        String name = getName(wood);
        ResourceLocation side = blockTexture(name);
        ResourceLocation top = blockTexture(name + "_top");
        axisBlock(wood, side, top);
        simpleBlockItem(wood);
    }

    private void woodWithItem(RotatedPillarBlock wood) {
        String name = getName(wood)
                .replace("wood", "log")
                .replace("hyphae", "stem");
        ResourceLocation side = blockTexture(name);
        axisBlock(wood, side, side);
        simpleBlockItem(wood);
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

    private void simplePlantWithItem(Block block) {
        simpleBlock(block, cross(getName(block)));
        generatedItem(block);
    }

    private void randomlyMirroredDoublePlantWithItem(Block block) {
        String topTexture = getName(block) + "_top";
        String bottomTexture = getName(block) + "_bottom";
        getVariantBuilder(block)
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
                .addModels(cross(topTexture), crossMirrored(topTexture))
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
                .addModels(cross(bottomTexture), crossMirrored(bottomTexture));
        generatedItem(block.asItem(), GardensOfTheDead.id(BLOCK_FOLDER + "/" + topTexture));
    }

    private void simpleBlockWithItem(Block block) {
        simpleBlock(block);
        simpleBlockItem(block);
    }

    private void simpleBlockWithItem(Block block, ModelFile modelFile) {
        simpleBlock(block, modelFile);
        simpleBlockItem(block);
    }

    private void simpleBlockItem(Block block) {
        ResourceLocation id = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(block.asItem()));

        itemModels().withExistingParent(id.getPath(), modLoc(BLOCK_FOLDER + '/' + id.getPath()));
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
