package gardensofthedead.registry;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.mixin.WoodTypeInvoker;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.HashSet;
import java.util.Set;

import static net.minecraft.client.renderer.Sheets.SIGN_SHEET;

public class ModWoodTypes {

    public static final Set<WoodType> VALUES = new HashSet<>();

    public static final WoodType SOULBLIGHT = createNetherWoodType("soulblight", ModBlockSetTypes.SOULBLIGHT);
    public static final WoodType WHISTLECANE = createBambooWoodType("whistlecane", ModBlockSetTypes.WHISTLECANE);

    private static WoodType createNetherWoodType(String name, BlockSetType blockSetType) {
        return create(name, blockSetType,
                SoundType.NETHER_WOOD,
                SoundType.NETHER_WOOD_HANGING_SIGN,
                SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE,
                SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN
        );
    }

    private static WoodType createBambooWoodType(String name, BlockSetType blockSetType) {
        return create(name, blockSetType,
                SoundType.BAMBOO_WOOD,
                SoundType.BAMBOO_WOOD_HANGING_SIGN,
                SoundEvents.BAMBOO_WOOD_FENCE_GATE_CLOSE,
                SoundEvents.BAMBOO_WOOD_FENCE_GATE_OPEN
        );
    }

    private static WoodType create(String id, BlockSetType blockSetType, SoundType soundType, SoundType hangingSignSoundType, SoundEvent fenceGateCloseSound, SoundEvent fenceGateOpenSound) {
        String name = GardensOfTheDead.id(id).toString();
        WoodType result = new WoodType(name, blockSetType, soundType, hangingSignSoundType, fenceGateCloseSound, fenceGateOpenSound);
        WoodTypeInvoker.invokerRegister(result);
        VALUES.add(result);
        return result;
    }

    public static void register() {
        for (WoodType woodType : VALUES) {
            String name = new ResourceLocation(woodType.name()).getPath();
            Sheets.SIGN_MATERIALS.put(woodType, new Material(SIGN_SHEET, GardensOfTheDead.id("entity/signs/" + name)));
            Sheets.HANGING_SIGN_MATERIALS.put(woodType, new Material(SIGN_SHEET, GardensOfTheDead.id("entity/signs/hanging/" + name)));
        }
    }
}
