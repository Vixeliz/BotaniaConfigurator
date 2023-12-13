package konsola5.botaniaconfigurator.mixin.misc;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import vazkii.botania.common.item.ManaTabletItem;
import vazkii.botania.common.item.equipment.bauble.GreaterBandOfManaItem;

@Debug(export = true)
@Mixin(GreaterBandOfManaItem.class)
public class GreaterBandOfManaMixin {
    private static final int NEW_MAX_MANA = ConfigFile.manaBandGreaterCapacity;

    @ModifyConstant(method = "addToCreativeTab", constant = @Constant(intValue = 2000000), remap = false)
    private int modifyGreaterBandOfManaCapacity(int original) {
        return NEW_MAX_MANA;
    }
}
