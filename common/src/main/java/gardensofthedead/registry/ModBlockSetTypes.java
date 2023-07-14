package gardensofthedead.registry;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.mixin.BlockSetTypeInvoker;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModBlockSetTypes {

    public static final BlockSetType SOULBLIGHT = registerNetherWood("soulblight");
    public static final BlockSetType WHISTLECANE = registerBambooWood("whistlecane");

    private static BlockSetType registerNetherWood(String name) {
        return register(name,
                SoundType.NETHER_WOOD,
                SoundEvents.NETHER_WOOD_DOOR_CLOSE,
                SoundEvents.NETHER_WOOD_DOOR_OPEN,
                SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE,
                SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN,
                SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF,
                SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON,
                SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF,
                SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON
        );
    }

    private static BlockSetType registerBambooWood(String name) {
        return register(name,
                SoundType.BAMBOO_WOOD,
                SoundEvents.BAMBOO_WOOD_DOOR_CLOSE,
                SoundEvents.BAMBOO_WOOD_DOOR_OPEN,
                SoundEvents.BAMBOO_WOOD_TRAPDOOR_CLOSE,
                SoundEvents.BAMBOO_WOOD_TRAPDOOR_OPEN,
                SoundEvents.BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF,
                SoundEvents.BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON,
                SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_OFF,
                SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_ON
        );
    }

    private static BlockSetType register(String name, SoundType soundType, SoundEvent doorClose, SoundEvent doorOpen, SoundEvent trapdoorClose, SoundEvent trapdoorOpen, SoundEvent pressurePlateClickOff, SoundEvent pressurePlateClickOn, SoundEvent buttonClickOff, SoundEvent buttonClickOn) {
        BlockSetType result = new BlockSetType(GardensOfTheDead.id(name).toString(),
                true,
                soundType,
                doorClose,
                doorOpen,
                trapdoorClose,
                trapdoorOpen,
                pressurePlateClickOff,
                pressurePlateClickOn,
                buttonClickOff,
                buttonClickOn
        );
        BlockSetTypeInvoker.invokerRegister(result);
        return result;
    }
}
