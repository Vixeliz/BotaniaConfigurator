package konsola5.botaniaconfigurator.mixin.misc;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import vazkii.botania.common.entity.ManaPoolMinecartEntity;

@Mixin(ManaPoolMinecartEntity.class)
public class ManaPoolCartMixin {
    @ModifyConstant(method = "tick", constant = @Constant(doubleValue = 1000000.0), remap = false)
    private double modifyPoolCartSize1(double original) {
        return ConfigFile.manaCartCapacity;
    }

    @ModifyConstant(method = "moveAlongTrack", constant = @Constant(doubleValue = 1000000.0), remap = false)
    private double modifyPoolCartSize2(double original) {
        return ConfigFile.manaCartCapacity;
    }

    @ModifyConstant(method = "moveAlongTrack", constant = @Constant(intValue = 1000000), remap = false)
    private int modifyPoolCartSize3(int original) {
        return ConfigFile.manaCartCapacity;
    }

    @ModifyConstant(method = "getComparatorLevel", constant = @Constant(intValue = 1000000), remap = false)
    private int modifyPoolCartSize4(int original) {
        return ConfigFile.manaCartCapacity;
    }

    @ModifyConstant(method = "moveAlongTrack", constant = @Constant(intValue = 10000), remap = false)
    private int modifyPumpTransferRate(int original) {
        return ConfigFile.manaPumpTransferRate;
    }
}
