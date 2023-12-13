package konsola5.botaniaconfigurator.mixin.misc;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import vazkii.botania.common.item.equipment.bauble.BandOfManaItem;

@Debug(export = true)
@Mixin(BandOfManaItem.class)
public class BandOfManaMixin {
    private static final int NEW_MAX_MANA = ConfigFile.manaBandCapacity;

    @ModifyConstant(method = "addToCreativeTab", constant = @Constant(intValue = 500000), remap = false)
    private int modifyBandOfManaCapacity(int original) {
        return NEW_MAX_MANA;
    }
}
