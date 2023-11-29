package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.OrechidBlockEntity;

@Mixin(OrechidBlockEntity.class)
public class OrechidMixin {
    /**
     * @author KonSola5
     * @reason Make mana capacity configurable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.HANDLER.instance().getFunctional().getOrechid().manaCapacity;
    }

    /**
     * @author KonSola5
     * @reason Make mana cost configurable.
     */
    @Overwrite(remap = false)
    public int getCost() {
        return ConfigFile.HANDLER.instance().getFunctional().getOrechid().manaCost;
    }

    /**
     * @author KonSola5
     * @reason Make mana cost configurable.
     */
    @Overwrite(remap = false)
    public int getDelay() {
        return ConfigFile.HANDLER.instance().getFunctional().getOrechid().delay;
    }

    /**
     * @author KonSola5
     * @reason Make range (X/Z) configurable.
     */
    @Overwrite(remap = false)
    public int getRange() {
        return ConfigFile.HANDLER.instance().getFunctional().getOrechid().rangeXZ;
    }

    /**
     * @author KonSola5
     * @reason Make range (Y) configurable.
     */
    @Overwrite(remap = false)
    public int getRangeY() {
        return ConfigFile.HANDLER.instance().getFunctional().getOrechid().rangeY;
    }
}
