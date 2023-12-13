package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import vazkii.botania.common.block.flower.functional.BellethornBlockEntity;

@Mixin(BellethornBlockEntity.class)
public class BellethorneMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.bellethorneManaCapacity;
    }

    /**
     * @author KonSola5
     * @reason Make Bellethorne Range modifiable.
     */
    @Overwrite(remap = false)
    public int getManaCost() {
        return ConfigFile.bellethorneManaCost;
    }

    /**
     * @author KonSola5
     * @reason Make Bellethorne Range modifiable.
     */
    @Overwrite(remap = false)
    public int getRange() {
        return ConfigFile.bellethorneRange;
    }
}
