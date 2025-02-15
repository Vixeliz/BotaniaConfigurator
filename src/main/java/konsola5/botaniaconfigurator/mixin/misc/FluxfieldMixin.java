package konsola5.botaniaconfigurator.mixin.misc;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vazkii.botania.common.block.block_entity.mana.PowerGeneratorBlockEntity;

@Mixin(PowerGeneratorBlockEntity.class)
public class FluxfieldMixin {
    @Shadow(remap = false) private int energy;

    /**
     * @author KonSola5
     * @reason Make mana to energy ratio modifiable.
     */
    @Overwrite(remap = false)
    public int getCurrentMana() {
        return (int) (energy / ConfigFile.manaFluxfieldRatio);
    }

    /**
     * @author KonSola5
     * @reason Make mana to energy ratio modifiable.
     */
    @Overwrite(remap = false)
    public void receiveMana(int mana) {
        this.energy = (int) Math.min(ConfigFile.manaFluxfieldCapacity, energy + mana * ConfigFile.manaFluxfieldRatio);
    }

    /**
     * @author KonSola5
     * @reason Make mana capacity modifiable
     */
    @Overwrite(remap = false)
    public boolean isFull() {
        return energy >= ConfigFile.manaFluxfieldCapacity;
    }

    @ModifyArg(method = "serverTick", at = @At(
            value = "INVOKE",
            target = "Ljava/lang/Math;min(II)I"
    ), index = 1)
    private static int configureTransferRate(int range) {
        return (int) (160 * ConfigFile.manaFluxfieldRatio);
    }
}
