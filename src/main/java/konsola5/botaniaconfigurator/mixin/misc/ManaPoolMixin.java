package konsola5.botaniaconfigurator.mixin.misc;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.block_entity.BotaniaBlockEntity;
import vazkii.botania.common.block.block_entity.mana.ManaPoolBlockEntity;
import vazkii.botania.common.block.mana.ManaPoolBlock;

@Mixin(ManaPoolBlockEntity.class)
public class ManaPoolMixin extends BotaniaBlockEntity {
    @Shadow(remap = false) private int manaCap;

    public ManaPoolMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Redirect(method = "initManaCapAndNetwork", at = @At(value = "FIELD", target = "Lvazkii/botania/common/block/block_entity/mana/ManaPoolBlockEntity;manaCap:I", opcode = Opcodes.PUTFIELD))
    private void modifyPoolSize(ManaPoolBlockEntity instance, int value) {
        var manaPoolBlock = ((ManaPoolBlock)getBlockState().getBlock()).variant;
        switch (manaPoolBlock){
            case DEFAULT -> manaCap = ConfigFile.manaPoolCapacity;
            case DILUTED -> manaCap = ConfigFile.dilutedManaPoolCapacity;
            case FABULOUS -> manaCap = ConfigFile.fabulousManaPoolCapacity;
            case CREATIVE -> manaCap = ConfigFile.guiltyPoolCapacity;
            default -> manaCap = 1000000; // Shouldn't happen
        }
    }

    @ModifyConstant(method = "getCurrentMana", constant = @Constant(intValue = 1000000), remap = false)
    private int modifyCreativePoolSize(int original) {
        return ConfigFile.guiltyPoolCapacity; // Why would you want to modify this?
    }
}
