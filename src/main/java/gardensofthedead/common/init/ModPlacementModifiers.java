package gardensofthedead.common.init;

import com.mojang.serialization.Codec;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.placementmodifier.CountOnEveryCeilingPlacement;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModPlacementModifiers {

    public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIER_TYPES = DeferredRegister.create(Registry.PLACEMENT_MODIFIER_REGISTRY, GardensOfTheDead.MODID);

    public static final RegistryObject<PlacementModifierType<CountOnEveryCeilingPlacement>> COUNT_ON_EVERY_CEILING = register("count_on_every_ceiling", CountOnEveryCeilingPlacement.CODEC);

    public static <T extends PlacementModifier> RegistryObject<PlacementModifierType<T>> register(String name, Codec<T> codec) {
        return PLACEMENT_MODIFIER_TYPES.register(name, () -> (PlacementModifierType<T>) () -> codec);
    }
}
