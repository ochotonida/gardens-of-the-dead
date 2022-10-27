package gardensofthedead.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.block.entity.SignBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;

@SuppressWarnings("ConstantConditions")
public class ModBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(GardensOfTheDead.MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

    public static final RegistrySupplier<BlockEntityType<SignBlockEntity>> SIGN = BLOCK_ENTITY_TYPES.register("sign", () -> BlockEntityType.Builder.of(
            SignBlockEntity::new,
            ModBlocks.SOULBLIGHT_SIGN.get(),
            ModBlocks.SOULBLIGHT_WALL_SIGN.get(),
            ModBlocks.WHISTLECANE_SIGN.get(),
            ModBlocks.WHISTLECANE_WALL_SIGN.get()
    ).build(null));
}
