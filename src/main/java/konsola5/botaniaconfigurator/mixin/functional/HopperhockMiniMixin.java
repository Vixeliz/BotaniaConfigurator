package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.HopperhockBlockEntity;

@Mixin(HopperhockBlockEntity.Mini.class)
public class HopperhockMiniMixin {

    @Inject(method = "getRange", at = @At("RETURN"), remap = false, cancellable = true)
    private void configRange(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(((HopperhockBlockEntity.Mini)(Object)this).getMana() > ConfigFile.HANDLER.instance().getFunctional().getHopperhock().manaCostPerPull - 1 ?
                ConfigFile.HANDLER.instance().getFunctional().getHopperhock().rangeManaMini :
                ConfigFile.HANDLER.instance().getFunctional().getHopperhock().rangeMini);
    }


}