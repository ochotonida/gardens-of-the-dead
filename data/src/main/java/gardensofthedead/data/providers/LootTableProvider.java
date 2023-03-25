package gardensofthedead.data.providers;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.block.WallHangingSignBlock;
import gardensofthedead.block.WallSignBlock;
import gardensofthedead.loot.MatchShears;
import gardensofthedead.registry.ModBlocks;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class LootTableProvider extends net.minecraft.data.loot.LootTableProvider {

    private final List<SubProviderEntry> lootTables = new ArrayList<>();

    private final Set<Block> blocksWithLootAdded = new HashSet<>();

    public LootTableProvider(PackOutput packOutput) {
        super(packOutput, Set.of(), List.of());
    }

    @Override
    public List<SubProviderEntry> getTables() {
        lootTables.clear();
        blocksWithLootAdded.clear();

        ForgeRegistries.BLOCKS.getKeys()
                .stream()
                .filter(k -> k.getNamespace().equals(GardensOfTheDead.MOD_ID))
                .map(ForgeRegistries.BLOCKS::getValue)
                .filter(block -> block instanceof WallSignBlock || block instanceof WallHangingSignBlock)
                .forEach(this::noLoot);

        addShearHarvestables(
                ModBlocks.SOUL_SPORE.get(),
                ModBlocks.GLOWING_SOUL_SPORE.get(),
                ModBlocks.SOULBLIGHT_SPROUTS.get(),
                ModBlocks.BLISTERCROWN.get(),
                ModBlocks.TALL_BLISTERCROWN.get()
        );

        addDefaultDrops(ModBlocks.WHISTLECANE.get());

        for (Block block : ForgeRegistries.BLOCKS.getValues()) {
            // noinspection ConstantConditions
            if (!blocksWithLootAdded.contains(block) && ForgeRegistries.BLOCKS.getKey(block).getNamespace().equals(GardensOfTheDead.MOD_ID)) {
                if (block instanceof FlowerPotBlock pottedPlant) {
                    addPottedPlants(pottedPlant);
                } else if (block instanceof DoorBlock doorBlock) {
                    addDoor(doorBlock);
                } else {
                    addDefaultDrops(block);
                }
            }
        }

        return lootTables;
    }

    private void addShearHarvestables(Block... blocks) {
        for (Block block : blocks) {
            addBlockLootTable(block, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .add(LootItem.lootTableItem(block)
                                    .when(MatchShears.matchShears())
                            )
                    )
            );
        }
    }

    private void addPottedPlants(FlowerPotBlock... blocks) {
        for (FlowerPotBlock block : blocks) {
            Block emptyPot = block.getEmptyPot();
            Block content = block.getContent();

            addBlockLootTable(block, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .add(LootItem.lootTableItem(emptyPot))
                            .when(ExplosionCondition.survivesExplosion())
                    ).withPool(LootPool.lootPool()
                            .add(LootItem.lootTableItem(content))
                            .when(ExplosionCondition.survivesExplosion())
                    )
            );
        }
    }

    private void addDoor(DoorBlock block) {
        addBlockLootTable(block, LootTable
                .lootTable()
                .withPool(defaultDrops(block)
                        .when(LootItemBlockStatePropertyCondition
                                .hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder
                                        .properties()
                                        .hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER)
                                )
                        )
                )
        );
    }

    private void addDefaultDrops(Block block) {
        addDefaultDrops(block, block);
    }

    private void addDefaultDrops(Block block, ItemLike loot) {
        addBlockLootTable(block, LootTable.lootTable().withPool(defaultDrops(loot)));
    }

    private LootPool.Builder defaultDrops(ItemLike itemProvider) {
        return LootPool.lootPool()
                .when(ExplosionCondition.survivesExplosion())
                .add(LootItem.lootTableItem(itemProvider));
    }

    private void addBlockLootTable(Block block, LootTable.Builder lootTable) {
        blocksWithLootAdded.add(block);
        lootTables.add(new SubProviderEntry(() -> lootBuilder -> lootBuilder.accept(block.getLootTable(), lootTable), LootContextParamSets.BLOCK));
    }

    private void noLoot(Block... blocks) {
        blocksWithLootAdded.addAll(Set.of(blocks));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
        map.forEach((location, lootTable) -> net.minecraft.world.level.storage.loot.LootTables.validate(validationContext, location, lootTable));
    }
}
