package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.block.entity.SignBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("ConstantConditions")
public class ModBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registry.BLOCK_ENTITY_TYPE_REGISTRY, GardensOfTheDead.MODID);

    public static final RegistryObject<BlockEntityType<SignBlockEntity>> SIGN = BLOCK_ENTITY_TYPES.register("sign", () -> BlockEntityType.Builder.of(
            SignBlockEntity::new,
            ModBlocks.SOULBLIGHT_SIGN.get(),
            ModBlocks.SOULBLIGHT_WALL_SIGN.get(),
            ModBlocks.WHISTLECANE_SIGN.get(),
            ModBlocks.WHISTLECANE_WALL_SIGN.get()
    ).build(null));
}
