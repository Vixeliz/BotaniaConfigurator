package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.OrechidBlockEntity;
import vazkii.botania.common.block.flower.functional.OrechidIgnemBlockEntity;

@Mixin(OrechidIgnemBlockEntity.class)
public class OrechidIgnemMixin extends OrechidBlockEntity {
    protected OrechidIgnemMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public int getMaxMana() {
        return ConfigFile.orechidIgnemManaCapacity;
    }

    /**
     * @author KonSola5
     * @reason Make mana cost configurable.
     */
    @Overwrite(remap = false)
    public int getCost() {
        return ConfigFile.orechidIgnemManaCost;
    }

    @Override
    public int getDelay() {
        return ConfigFile.orechidIgnemDelay;
    }
    @Override
    public int getRange() {
        return ConfigFile.orechidIgnemRangeXZ;
    }
    @Override
    public int getRangeY() {
        return ConfigFile.orechidIgnemRangeY;
    }

    @Inject(method = "canOperate", at = @At("RETURN"), remap = false, cancellable = true)
    private void allowFlowerOutsideNether(CallbackInfoReturnable<Boolean> cir) {
        if (!ConfigFile.orechidIgnemOnlyWorksInNether) {
            cir.setReturnValue(true);
        }
    }
}
