package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.DreadthornBlockEntity;

@Mixin(DreadthornBlockEntity.class)
public class DreadthorneMixin {
    /**
     * @author KonSola5
     * @reason Make Dreadthorne Mana Cost modifiable.
     */
    @Overwrite(remap = false)
    public int getManaCost() {
        return ConfigFile.dreadthorneManaCost;
    }
}
