package gardensofthedead.data;

import com.mojang.datafixers.util.Pair;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LootTables extends LootTableProvider {

    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> tables = new ArrayList<>();

    public LootTables(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        tables.clear();

        addShearHarvestableBlock(ModBlocks.SOUL_SPORE.get());
        addShearHarvestableBlock(ModBlocks.GLOWING_SOUL_SPORE.get());

        addPottedPlant(ModBlocks.POTTED_SOUL_SPORE.get());
        addPottedPlant(ModBlocks.POTTED_GLOWING_SOUL_SPORE.get());

        return tables;
    }

    private void addShearHarvestableBlock(Block block) {
        addBlockLootTable(block, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(block)
                                .when(CanToolPerformAction.canToolPerformAction(ToolActions.SHEARS_DIG))
                        )
                )
        );
    }

    private void addPottedPlant(FlowerPotBlock block) {
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

    private void addStandardDropTable(Block block) {
        addBlockLootTable(block, LootTable.lootTable().withPool(createStandardDrops(block)));
    }

    private LootPool.Builder createStandardDrops(ItemLike itemProvider) {
        return LootPool.lootPool()
                .when(ExplosionCondition.survivesExplosion())
                .add(LootItem.lootTableItem(itemProvider));
    }

    private void addBlockLootTable(Block block, LootTable.Builder lootTable) {
        tables.add(Pair.of(() -> lootBuilder -> lootBuilder.accept(block.getLootTable(), lootTable), LootContextParamSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
        map.forEach((location, lootTable) -> net.minecraft.world.level.storage.loot.LootTables.validate(validationContext, location, lootTable));
    }
}
