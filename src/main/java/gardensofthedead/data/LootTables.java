package gardensofthedead.data;

import com.mojang.datafixers.util.Pair;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.loot.CanToolPerformAction;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LootTables extends LootTableProvider {

    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> lootTables = new ArrayList<>();

    private final Set<Block> blocksWithLootAdded = new HashSet<>();

    public LootTables(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        lootTables.clear();
        blocksWithLootAdded.clear();

        noLoot(
                ModBlocks.SOULBLIGHT_WALL_SIGN.get()
        );

        addShearHarvestables(
                ModBlocks.SOUL_SPORE.get(),
                ModBlocks.GLOWING_SOUL_SPORE.get(),
                ModBlocks.SOULBLIGHT_SPROUTS.get(),
                ModBlocks.BLISTERCROWN.get(),
                ModBlocks.TALL_BLISTERCROWN.get()
        );

        for (Block block : ForgeRegistries.BLOCKS.getValues()) {
            // noinspection ConstantConditions
            if (!blocksWithLootAdded.contains(block) && ForgeRegistries.BLOCKS.getKey(block).getNamespace().equals(GardensOfTheDead.MODID)) {
                if (block instanceof FlowerPotBlock pottedPlant) {
                    addPottedPlants(pottedPlant);
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
                                    .when(CanToolPerformAction.canToolPerformAction(ToolActions.SHEARS_DIG))
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

    private void addDefaultDrops(Block... blocks) {
        for (Block block : blocks) {
            addBlockLootTable(block, LootTable.lootTable().withPool(defaultDrops(block)));
        }
    }

    private LootPool.Builder defaultDrops(ItemLike itemProvider) {
        return LootPool.lootPool()
                .when(ExplosionCondition.survivesExplosion())
                .add(LootItem.lootTableItem(itemProvider));
    }

    private void addBlockLootTable(Block block, LootTable.Builder lootTable) {
        blocksWithLootAdded.add(block);
        lootTables.add(Pair.of(() -> lootBuilder -> lootBuilder.accept(block.getLootTable(), lootTable), LootContextParamSets.BLOCK));
    }

    private void noLoot(Block... blocks) {
        blocksWithLootAdded.addAll(Set.of(blocks));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
        map.forEach((location, lootTable) -> net.minecraft.world.level.storage.loot.LootTables.validate(validationContext, location, lootTable));
    }
}
