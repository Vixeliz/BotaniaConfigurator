package konsola5.botaniaconfigurator.mixin.misc;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import vazkii.botania.common.item.ManaTabletItem;

@Mixin(ManaTabletItem.ManaItemImpl.class)
public class ManaItemImplMixin {
    private static final int NEW_MAX_MANA = ConfigFile.manaTabletCapacity;

    @ModifyConstant(method = "getMaxMana", constant = @Constant(intValue = 500000), remap = false)
    private int modifyManaTabletCapacity (int original) {
        return NEW_MAX_MANA;
    }

    @ModifyConstant(method = "getMaxMana", constant = @Constant(intValue = 501000), remap = false)
    private int modifyCreativeManaTabletCapacity(int original) {
        return (int) (NEW_MAX_MANA * 1.002); // :thinking:
    }
}
