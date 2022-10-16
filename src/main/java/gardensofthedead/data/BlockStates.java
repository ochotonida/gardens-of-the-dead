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
import net.minecraftforge.client.model.generators.*;
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
        addWhistlecane();

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
        addPottedWhistleCane();

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

    private void addWhistlecane() {
        String name = getName(ModBlocks.WHISTLECANE.get());
        ResourceLocation texture0 = GardensOfTheDead.id(ModelProvider.BLOCK_FOLDER + "/" + name + "0");
        ResourceLocation texture1 = GardensOfTheDead.id(ModelProvider.BLOCK_FOLDER + "/" + name + "1");
        ResourceLocation leafTexture0 = GardensOfTheDead.id(ModelProvider.BLOCK_FOLDER + "/" + name + "_leaves" + "0");
        ResourceLocation leafTexture1 = GardensOfTheDead.id(ModelProvider.BLOCK_FOLDER + "/" + name + "_leaves" + "1");

        String model0Name = name + "0";
        String model1Name = name + "1";
        String model2Name = name + "2";
        BlockModelBuilder model0 = whistlecaneModel(model0Name, 0, texture0, texture0);
        BlockModelBuilder model1 = whistlecaneModel(model1Name, 0, texture0, texture1);
        BlockModelBuilder model2 = whistlecaneModel(model2Name, 6, texture0, texture1);

        model0.texture("leaf", leafTexture0);
        addWhistlecaneLeaf(model0, Direction.SOUTH, 5, 0);
        model1.texture("leaf", leafTexture0);
        addWhistlecaneLeaf(model1, Direction.NORTH, 9, 0);
        addWhistlecaneLeaf(model1, Direction.SOUTH, 15, 8);
        model2.texture("leaf", leafTexture1);
        addWhistlecaneLeaf(model2, Direction.NORTH, 8, 0);
        addWhistlecaneLeaf(model2, Direction.EAST, 11, 8);

        ConfiguredModel[] models = ConfiguredModel.builder()
                .modelFile(model0).rotationY(0).nextModel()
                .modelFile(model0).rotationY(90).nextModel()
                .modelFile(model0).rotationY(180).nextModel()
                .modelFile(model0).rotationY(270).nextModel()
                .modelFile(model1).rotationY(0).nextModel()
                .modelFile(model1).rotationY(90).nextModel()
                .modelFile(model1).rotationY(180).nextModel()
                .modelFile(model1).rotationY(270).nextModel()
                .modelFile(model2).rotationY(0).nextModel()
                .modelFile(model2).rotationY(90).nextModel()
                .modelFile(model2).rotationY(180).nextModel()
                .modelFile(model2).rotationY(270).build();

        simpleBlock(ModBlocks.WHISTLECANE.get(), models);
        simpleBlock(ModBlocks.WHISTLECANE_PLANT.get(), models);

        itemModels().withExistingParent(name, modLoc(BLOCK_FOLDER + '/' + model0Name));
    }

    private void addPottedWhistleCane() {
        String pottedWhistleCane = getName(ModBlocks.POTTED_WHISTLECANE.get());
        ResourceLocation whistlecaneTexture = blockTexture(pottedWhistleCane);
        ResourceLocation flowerpotTexture = blockTexture(Blocks.FLOWER_POT);

        BlockModelBuilder model = models().getBuilder(pottedWhistleCane)
                .texture("whistlecane", whistlecaneTexture)
                .texture("flowerpot", flowerpotTexture)
                .ao(false)
                .element().from(5, 0, 5).to(11, 6, 11)
                .face(Direction.DOWN).uvs(5, 5, 6, 11).cullface(Direction.DOWN).end()
                .textureAll("#flowerpot")
                .end()
                .element().from(6, 6, 6).to(10, 16, 10)
                .face(Direction.NORTH).end()
                .face(Direction.EAST).end()
                .face(Direction.SOUTH).end()
                .face(Direction.WEST).end()
                .faces((direction, faceBuilder) -> faceBuilder.uvs(6, 0, 10, 10))
                .face(Direction.UP).uvs(10, 0, 14, 4).cullface(Direction.UP).end()
                .faces((direction, faceBuilder) -> faceBuilder.texture("#whistlecane"))
                .end();

        model.texture("leaf", "#whistlecane");
        addWhistlecaneLeaf(model, Direction.NORTH, 9, 0, 2);
        model.renderType(CUTOUT);

        simpleBlock(ModBlocks.POTTED_WHISTLECANE.get(), model);
    }

    private BlockModelBuilder whistlecaneModel(String modelName, int u1, ResourceLocation topTexture, ResourceLocation sideTexture) {
        return models().withExistingParent(modelName, BLOCK_FOLDER + "/block")
                .texture("top", topTexture)
                .texture("side", sideTexture)
                .texture("particle", "#side")
                .element()
                .from(5, 0, 5).to(11, 16, 11)
                .allFaces((direction, faceBuilder) -> {
                    if (direction.getAxis() == Direction.Axis.Y) {
                        faceBuilder.uvs(6, 0, 12, 6).cullface(direction).texture("#top");
                    } else {
                        faceBuilder.uvs(u1, 0, u1 + 6, 16).texture("#side");
                    }
                }).end()
                .renderType(CUTOUT);
    }

    private void addWhistlecaneLeaf(BlockModelBuilder model, Direction direction, int height, int u1) {
        addWhistlecaneLeaf(model, direction, height, u1, 3);
    }

    private void addWhistlecaneLeaf(BlockModelBuilder model, Direction direction, int height, int u1, int caneWidth) {
        Direction.Axis axis = direction.getAxis();
        Direction.AxisDirection axisDirection = direction.getAxisDirection();
        float angle = 22.5F;
        int textureWidth = caneWidth + 1;

        int pivot = 8 + axisDirection.getStep() * caneWidth;
        int x1 = axis == Direction.Axis.Z ? 8 - textureWidth : pivot;
        int z1 = axis == Direction.Axis.X ? 8 - textureWidth : pivot;
        int x2 = axis == Direction.Axis.Z ? 8 + textureWidth : pivot;
        int z2 = axis == Direction.Axis.X ? 8 + textureWidth : pivot;
        model.element()
                .from(x1, height, z1)
                .to(x2, height + 16, z2)
                .face(direction).texture("#leaf").uvs(u1, 0, u1 + textureWidth * 2, 16).ao(false).end()
                .face(direction.getOpposite()).texture("#leaf").uvs(u1 + textureWidth * 2, 0, u1, 16).ao(false).end()
                .rotation().origin(x1, height, z1).axis(direction.getClockWise().getAxis()).angle(axisDirection.getStep() * (axis == Direction.Axis.X ? -1 : 1) * angle).end()
                .end();
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
