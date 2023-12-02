package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import vazkii.botania.common.block.flower.functional.LabelliaBlockEntity;

@Mixin(LabelliaBlockEntity.class)
public class LabelliaMixin {
    /**
     * @author KonSola5
     * @reason Make mana capacity configurable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.labelliaManaCapacity;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 500),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/LabelliaBlockEntity;getMana()I"),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/LabelliaBlockEntity;getEffectivePos()Lnet/minecraft/core/BlockPos;")))
    private int configureCost1(int original) {
        return ConfigFile.labelliaManaCost;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/LabelliaBlockEntity;addMana(I)V"))
    private void configureCost2(LabelliaBlockEntity instance, int i) {
        instance.addMana(-ConfigFile.labelliaManaCost);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 0))
    private BlockPos configurePickupRange1(BlockPos instance, int dx, int dy, int dz) {
        return instance.offset(-ConfigFile.labelliaPickupRange, 0, -ConfigFile.labelliaPickupRange);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 1))
    private BlockPos configurePickupRange2(BlockPos instance, int dx, int dy, int dz) {
        return instance.offset(ConfigFile.labelliaPickupRange + 1, 1, ConfigFile.labelliaPickupRange + 1);
    }

    @ModifyArgs(method = "tickFlower", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/phys/AABB;<init>(DDDDDD)V"
    ))
    private void configureRenameRange1(Args args) {
        final int RENAME_RANGE = 2;
        double x1 = args.get(0);
        double y1 = args.get(1);
        double z1 = args.get(2);
        double x2 = args.get(3);
        double y2 = args.get(4);
        double z2 = args.get(5);
        args.set(0, x1 + RENAME_RANGE - ConfigFile.labelliaRenameRange);
        args.set(1, y1);
        args.set(2, z1 + RENAME_RANGE - ConfigFile.labelliaRenameRange);
        args.set(3, x2 - RENAME_RANGE + ConfigFile.labelliaRenameRange);
        args.set(4, y2);
        args.set(5, z2 - RENAME_RANGE + ConfigFile.labelliaRenameRange);
    }

    @ModifyArg(method = "getSecondaryRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configurePickupRange3(int range) {
        return ConfigFile.labelliaPickupRange;
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configureRenameRange2(int range) {
        return ConfigFile.labelliaRenameRange;
    }
}
