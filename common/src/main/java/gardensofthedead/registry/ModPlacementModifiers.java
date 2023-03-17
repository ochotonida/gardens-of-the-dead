package gardensofthedead.registry;

import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.placementmodifier.CountOnEveryCeilingPlacement;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public class ModPlacementModifiers {

    public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIER_TYPES = DeferredRegister.create(GardensOfTheDead.MOD_ID, Registries.PLACEMENT_MODIFIER_TYPE);

    public static final RegistrySupplier<PlacementModifierType<CountOnEveryCeilingPlacement>> COUNT_ON_EVERY_CEILING = register("count_on_every_ceiling", CountOnEveryCeilingPlacement.CODEC);

    public static <T extends PlacementModifier> RegistrySupplier<PlacementModifierType<T>> register(String name, Codec<T> codec) {
        return PLACEMENT_MODIFIER_TYPES.register(name, () -> (PlacementModifierType<T>) () -> codec);
    }
}
