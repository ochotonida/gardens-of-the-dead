package gardensofthedead.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.loot.MatchShears;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class ModLootConditions {

    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITIONS = DeferredRegister.create(GardensOfTheDead.MOD_ID, Registry.LOOT_ITEM_REGISTRY);

    public static final RegistrySupplier<LootItemConditionType> MATCH_SHEARS = LOOT_CONDITIONS.register("match_shears", () -> new LootItemConditionType(new MatchShears.Serializer()));

}
