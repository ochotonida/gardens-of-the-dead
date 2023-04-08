package gardensofthedead.data.providers;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.block.SoulSporeBaseBlock;
import gardensofthedead.block.SoulSporeBlock;
import gardensofthedead.data.registry.ModBlockFamilies;
import gardensofthedead.registry.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;
import static net.minecraftforge.client.model.generators.ModelProvider.ITEM_FOLDER;

public class BlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {

    private static final String CUTOUT = "cutout";

    private static final String BLOCK_MODEL = BLOCK_FOLDER + "/block";
    private static final ResourceLocation WHISTLECANE_0 = GardensOfTheDead.id(ModelProvider.BLOCK_FOLDER + "/" + getName(ModBlocks.WHISTLECANE.get()) + "0");
    private static final ResourceLocation WHISTLECANE_1 = GardensOfTheDead.id(ModelProvider.BLOCK_FOLDER + "/" + getName(ModBlocks.WHISTLECANE.get()) + "1");

    public BlockStateProvider(PackOutput generator, ExistingFileHelper existingFileHelper) {
        super(generator, GardensOfTheDead.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModBlockFamilies.getAllFamilies().forEach(this::generateModels);

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

        pottedPlantWithCustomTexture(ModBlocks.POTTED_SOUL_SPORE.get());
        pottedPlantWithCustomTexture(ModBlocks.POTTED_GLOWING_SOUL_SPORE.get());
        pottedPlant(ModBlocks.POTTED_SOULBLIGHT_FUNGUS.get());
        pottedPlantWithCustomTexture(ModBlocks.POTTED_SOULBLIGHT_SPROUTS.get());
        pottedPlant(ModBlocks.POTTED_BLISTERCROWN.get());
        addPottedWhistleCane();

        axisBlockUvLocked(ModBlocks.WHISTLECANE_BLOCK.get());

        addWhistlecaneFence();

        signWithItem(ModBlocks.SOULBLIGHT_HANGING_SIGN.get(), ModBlocks.SOULBLIGHT_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.STRIPPED_SOULBLIGHT_STEM.get()));
        signWithItem(ModBlocks.WHISTLECANE_HANGING_SIGN.get(), ModBlocks.WHISTLECANE_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.WHISTLECANE_BLOCK.get()));
    }

    private void generateModels(BlockFamily family) {
        Map<BlockFamily.Variant, Block> variants = family.getVariants();
        ResourceLocation texture = blockTexture(family.getBaseBlock());

        simpleBlockWithItem(family.getBaseBlock());

        if (variants.containsKey(BlockFamily.Variant.SLAB)) {
            slabWithItem((SlabBlock) variants.get(BlockFamily.Variant.SLAB), texture);
        }
        if (variants.containsKey(BlockFamily.Variant.STAIRS)) {
            stairsWithItem((StairBlock) variants.get(BlockFamily.Variant.STAIRS), texture);
        }
        if (variants.containsKey(BlockFamily.Variant.PRESSURE_PLATE)) {
            pressurePlateWithItem((PressurePlateBlock) variants.get(BlockFamily.Variant.PRESSURE_PLATE), texture);
        }
        if (variants.containsKey(BlockFamily.Variant.BUTTON)) {
            buttonWithItem((ButtonBlock) variants.get(BlockFamily.Variant.BUTTON), texture);
        }
        if (variants.containsKey(BlockFamily.Variant.FENCE)) {
            fenceWithItem((FenceBlock) variants.get(BlockFamily.Variant.FENCE), texture);
        }
        if (variants.containsKey(BlockFamily.Variant.FENCE_GATE)) {
            fenceGateWithItem((FenceGateBlock) variants.get(BlockFamily.Variant.FENCE_GATE), texture);
        }
        if (variants.containsKey(BlockFamily.Variant.SIGN) && variants.containsKey(BlockFamily.Variant.WALL_SIGN)) {
            signWithItem(variants.get(BlockFamily.Variant.SIGN), variants.get(BlockFamily.Variant.WALL_SIGN), texture);
        }
        if (variants.containsKey(BlockFamily.Variant.DOOR)) {
            doorWithItem((DoorBlock) variants.get(BlockFamily.Variant.DOOR));
        }
        if (variants.containsKey(BlockFamily.Variant.TRAPDOOR)) {
            trapdoorWithItem((TrapDoorBlock) variants.get(BlockFamily.Variant.TRAPDOOR));
        }
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

    private void addWhistlecaneFence() {
        String whistlecane = getName(ModBlocks.WHISTLECANE.get());
        String whistlecaneFenceBeams = getName(ModBlocks.WHISTLECANE_FENCE.get()) + "_side";
        ResourceLocation beamTexture = GardensOfTheDead.id(BLOCK_FOLDER + "/" + whistlecaneFenceBeams);

        String fenceGate = getName(ModBlocks.WHISTLECANE_FENCE_GATE.get());
        String gateParent = "template_custom_fence_gate";
        String gateTexture = BLOCK_FOLDER + "/" + fenceGate;
        String planksTexture = BLOCK_FOLDER + "/" + getName(ModBlocks.WHISTLECANE_PLANKS.get());

        ModelFile gate = models().withExistingParent(fenceGate, new ResourceLocation(gateParent))
                .texture("texture", gateTexture).texture("particle", planksTexture);
        ModelFile gateOpen = models().withExistingParent(fenceGate + "_open", new ResourceLocation(gateParent + "_open"))
                .texture("texture", gateTexture).texture("particle", planksTexture);
        ModelFile gateWall = models().withExistingParent(fenceGate + "_wall", new ResourceLocation(gateParent + "_wall"))
                .texture("texture", gateTexture).texture("particle", planksTexture);
        ModelFile gateWallOpen = models().withExistingParent(fenceGate + "_wall_open", new ResourceLocation(gateParent + "_wall_open"))
                .texture("texture", gateTexture).texture("particle", planksTexture);

        getVariantBuilder(ModBlocks.WHISTLECANE_FENCE_GATE.get()).forAllStatesExcept(state -> {
            ModelFile model = gate;
            if (state.getValue(FenceGateBlock.IN_WALL)) {
                model = gateWall;
            }
            if (state.getValue(FenceGateBlock.OPEN)) {
                model = model == gateWall ? gateWallOpen : gateOpen;
            }
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY((int) state.getValue(FenceGateBlock.FACING).toYRot())
                    .build();
        }, FenceGateBlock.POWERED);

        simpleBlockItem(ModBlocks.WHISTLECANE_FENCE_GATE.get());

        ModelFile post = whistlecaneModel(whistlecane + "_post", 0, WHISTLECANE_1);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(ModBlocks.WHISTLECANE_FENCE.get())
                .part().modelFile(post).addModel().end();
        PipeBlock.PROPERTY_BY_DIRECTION.forEach((dir, value) -> {
            if (dir.getAxis().isHorizontal()) {
                String directionName = dir.getName().toLowerCase();
                String modelName = whistlecaneFenceBeams + "_" + directionName;
                String parent = "custom_fence_side_" + directionName;
                var model = models().withExistingParent(modelName, parent)
                                .texture("texture", "#beams")
                                .texture("beams", beamTexture);
                builder.part().modelFile(model)
                        .addModel()
                        .condition(value, true);
            }
        });

        createWhistlecaneFenceItemModel();
    }

    private void createWhistlecaneFenceItemModel() {
        ResourceLocation beamTexture = GardensOfTheDead.id(BLOCK_FOLDER + "/" + getName(ModBlocks.WHISTLECANE_FENCE.get()) + "_side");
        ItemModelBuilder builder = itemModels().withExistingParent(getName(ModBlocks.WHISTLECANE_FENCE.get()), BLOCK_MODEL)
                .transforms()
                .transform(ItemDisplayContext.GUI)
                .rotation(30, 135, 0)
                .scale(0.625F)
                .end()
                .transform(ItemDisplayContext.FIXED)
                .rotation(0, 90, 0)
                .translation(0, 0, 0)
                .scale(0.5F)
                .end()
                .end()
                .ao(false)
                .texture("side", WHISTLECANE_1)
                .texture("top", WHISTLECANE_0)
                .texture("beams", beamTexture);

        addWhistlecanePost(builder, 0, -6);
        addWhistlecanePost(builder, 0, 6);
        for (int height : Set.of(6, 12)) {
            builder.element()
                    .from(7, height, 4)
                    .to(9, height + 3, 12)
                    .face(Direction.EAST).end()
                    .face(Direction.WEST).end()
                    .face(Direction.UP).end()
                    .face(Direction.DOWN).end()
                    .faces((direction, faceBuilder) -> {
                        if (direction.getAxis() == Direction.Axis.X) {
                            faceBuilder.uvs(8, 0, 16, 3);
                        } else {
                            int v1 = direction == Direction.UP ? 7 : 15;
                            int v2 = direction == Direction.UP ? 15 : 7;
                            faceBuilder.uvs(11, v1, 13, v2);
                        }
                        faceBuilder.texture("#beams");
                    });

            for (boolean isLeft : Set.of(true, false)) {
                int start = isLeft ? -2 : 16;
                int end = isLeft ? 0 : 18;

                builder.element()
                        .from(7, height, start)
                        .to(9, height + 3, end)
                        .face(Direction.EAST).end()
                        .face(Direction.WEST).end()
                        .face(Direction.UP).end()
                        .face(Direction.DOWN).end()
                        .faces((direction, faceBuilder) -> {
                            if (direction.getAxis() == Direction.Axis.X) {
                                int offSet = isLeft ? 0 : 6;
                                faceBuilder.uvs(8 + offSet, 0, 10 + offSet, 3);
                            } else {
                                faceBuilder.uvs(11, 13, 13, 15);
                            }
                        })
                        .face(isLeft ? Direction.NORTH : Direction.SOUTH).uvs(13, 4, 15, 7).end()
                        .faces((direction, faceBuilder) -> faceBuilder.texture("#beams"));
            }
        }
    }

    private void addWhistlecane() {
        String name = getName(ModBlocks.WHISTLECANE.get());
        ResourceLocation leafTexture0 = GardensOfTheDead.id(ModelProvider.BLOCK_FOLDER + "/" + name + "_leaves" + "0");
        ResourceLocation leafTexture1 = GardensOfTheDead.id(ModelProvider.BLOCK_FOLDER + "/" + name + "_leaves" + "1");

        String model0Name = name + "0";
        String model1Name = name + "1";
        String model2Name = name + "2";
        BlockModelBuilder model0 = whistlecaneModel(model0Name, 0, WHISTLECANE_0);
        BlockModelBuilder model1 = whistlecaneModel(model1Name, 0, WHISTLECANE_1);
        BlockModelBuilder model2 = whistlecaneModel(model2Name, 6, WHISTLECANE_1);

        model0.texture("leaf", leafTexture0);
        addWhistlecaneLeaf(model0, Direction.WEST, 5, 0);
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

        generatedItem(ModBlocks.WHISTLECANE.get().asItem(), GardensOfTheDead.id(ITEM_FOLDER + '/' + name));
    }

    private void addPottedWhistleCane() {
        String pottedWhistleCane = getName(ModBlocks.POTTED_WHISTLECANE.get());
        ResourceLocation whistlecaneTexture = blockTexture(pottedWhistleCane);
        ResourceLocation flowerpotTexture = blockTexture(Blocks.FLOWER_POT);

        BlockModelBuilder model = models().getBuilder(pottedWhistleCane)
                .texture("whistlecane", whistlecaneTexture)
                .texture("flowerpot", flowerpotTexture)
                .texture("particle", "#flowerpot")
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

    private BlockModelBuilder whistlecaneModel(String modelName, int u1, ResourceLocation sideTexture) {
        BlockModelBuilder model = models().withExistingParent(modelName, BLOCK_MODEL)
                .texture("top", WHISTLECANE_0)
                .texture("side", sideTexture)
                .texture("particle", "#side")
                .renderType(CUTOUT);
        addWhistlecanePost(model, u1, 0);
        return model;
    }

    private void addWhistlecanePost(ModelBuilder<?> builder, int u1, int zOffset) {
        builder.element()
                .from(5, 0, 8 - 3 + zOffset).to(11, 16, 8 + 3 + zOffset)
                .allFaces((direction, faceBuilder) -> {
                    if (direction.getAxis() == Direction.Axis.Y) {
                        faceBuilder.uvs(6, 0, 12, 6).cullface(direction).texture("#top");
                    } else {
                        faceBuilder.uvs(u1, 0, u1 + 6, 16).texture("#side");
                    }
                }).end();
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
        slabWithItem(block, texture, texture);
    }

    public void slabWithItem(SlabBlock block, ResourceLocation sideTexture, ResourceLocation topTexture) {
        slabBlock(block, sideTexture, sideTexture, topTexture, topTexture);
        simpleBlockItem(block);
    }

    public void stairsWithItem(StairBlock block, ResourceLocation texture) {
        stairsWithItem(block, texture, texture);
    }

    public void stairsWithItem(StairBlock block, ResourceLocation sideTexture, ResourceLocation topTexture) {
        stairsBlock(block, sideTexture, topTexture, topTexture);
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

    public void signWithItem(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(getName(signBlock), texture);
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
        itemModels().basicItem(signBlock.asItem());
    }

    private void logWithItem(RotatedPillarBlock wood) {
        String name = getName(wood);
        ResourceLocation side = blockTexture(name);
        ResourceLocation top = blockTexture(name + "_top");
        axisBlock(wood, side, top);
        simpleBlockItem(wood);
    }

    private ModelFile cubeColumnUvLocked(RotatedPillarBlock block, Direction.Axis axis) {
        String name = getName(block);
        String axisName = axis.getName().toLowerCase(Locale.ROOT);
        String parent = "%s/cube_column_uv_locked_%s".formatted(BLOCK_FOLDER, axisName);
        String modelName = name + '_' + axisName;
        ResourceLocation side = blockTexture(name);
        ResourceLocation end = blockTexture(name + "_top");

        return models().withExistingParent(modelName, parent)
                .texture("side", side)
                .texture("end", end);
    }

    private void axisBlockUvLocked(RotatedPillarBlock block) {
        String name = getName(block);

        ModelFile modelX = cubeColumnUvLocked(block, Direction.Axis.X);
        ModelFile modelY = cubeColumnUvLocked(block, Direction.Axis.Y);
        ModelFile modelZ = cubeColumnUvLocked(block, Direction.Axis.Z);

        getVariantBuilder(block)
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(modelX).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(modelY).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(modelZ).addModel();

        itemModels().withExistingParent(name, modelY.getLocation());
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

    private void pottedPlantWithCustomTexture(Block pottedPlant) {
        pottedPlant(pottedPlant, getName(pottedPlant));
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
        String name = getName(block);
        itemModels().withExistingParent(name, modLoc(BLOCK_FOLDER + '/' + name));
    }

    private void generatedItem(Block block) {
        String name = getName(block);
        generatedItem(block.asItem(), GardensOfTheDead.id(BLOCK_FOLDER + '/' + name));
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

    private static String getName(Block block) {
        // noinspection ConstantConditions
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }
}
