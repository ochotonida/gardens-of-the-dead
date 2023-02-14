package gardensofthedead.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;import gardensofthedead.platform.PlatformServices;
import gardensofthedead.registry.ModLootConditions;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class MatchShears implements LootItemCondition {

    @Override
    public LootItemConditionType getType() {
        return ModLootConditions.MATCH_SHEARS.get();
    }

    public static Builder matchShears() {
        return MatchShears::new;
    }

    @Override
    public boolean test(LootContext context) {
        if (context.hasParam(LootContextParams.TOOL)) {
            return test(context.getParam(LootContextParams.TOOL));
        }
        return false;
    }

    public static boolean test(ItemStack tool) {
        return PlatformServices.platformHelper.isShears(tool);
    }

    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<MatchShears> {

        @Override
        public void serialize(JsonObject jsonObject, MatchShears object, JsonSerializationContext jsonSerializationContext) {

        }

        @Override
        public MatchShears deserialize(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return new MatchShears();
        }
    }
}
