package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.api.block_entity.FunctionalFlowerBlockEntity;
import vazkii.botania.common.block.flower.functional.HopperhockBlockEntity;

@Mixin(HopperhockBlockEntity.class)
public class HopperhockMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.HANDLER.instance().getFunctional().getHopperhock().manaCapacity;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/HopperhockBlockEntity;getMana()I"), remap = false)
    public int modifyCost1(HopperhockBlockEntity instance) {
        return instance.getMana() - ConfigFile.HANDLER.instance().getFunctional().getHopperhock().manaCostPerPull;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/HopperhockBlockEntity;addMana(I)V"), remap = false)
    public void modifyCost2(HopperhockBlockEntity instance, int mana) {
        instance.addMana(-ConfigFile.HANDLER.instance().getFunctional().getHopperhock().manaCostPerPull);
    }

    @Inject(method = "getRange", at = @At("RETURN"), remap = false, cancellable = true)
    private void configRange(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(((HopperhockBlockEntity)(Object)this).getMana() > ConfigFile.HANDLER.instance().getFunctional().getHopperhock().manaCostPerPull - 1 ?
                ConfigFile.HANDLER.instance().getFunctional().getHopperhock().rangeMana :
                ConfigFile.HANDLER.instance().getFunctional().getHopperhock().range);
    }
}
