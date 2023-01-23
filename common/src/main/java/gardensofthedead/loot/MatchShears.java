package gardensofthedead.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import dev.architectury.injectables.annotations.ExpectPlatform;
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

    @ExpectPlatform
    public static boolean test(ItemStack tool) {
        throw new AssertionError();
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
