package konsola5.botaniaconfigurator.mixin.misc;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import vazkii.botania.common.item.equipment.bauble.GreaterBandOfManaItem;

@Mixin(GreaterBandOfManaItem.GreaterManaItemImpl.class)
public class GreaterBandOfManaImplMixin {
    private static final int NEW_MAX_MANA = ConfigFile.manaBandGreaterCapacity;

    @ModifyConstant(method = "getMaxMana", constant = @Constant(intValue = 2000000), remap = false)
    private int modifyGreaterBandOfManaCapacity (int original) {
        return NEW_MAX_MANA;
    }
}
