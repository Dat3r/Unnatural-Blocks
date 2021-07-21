package dat3r.unnaturalblocks.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity { // code in here will be as if it were in the LivingEntity class instead of here because of mixin
    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Shadow public abstract void equipStack(EquipmentSlot slot, ItemStack stack);

    @Inject(method = "fall", at = @At("RETURN")) // inject code to the method "fall" when the method ends
    private void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition, CallbackInfo info) { // same parameters but with CallbackInfo at the end
        if (onGround) { // check that you actually fell and it's not the game bugging out or something
            ItemStack equippedStack = this.getEquippedStack(EquipmentSlot.CHEST);

            if (equippedStack.isOf(Items.ELYTRA)) { // checking if they are wearing an elytra
                if (equippedStack.getOrCreateNbt().getBoolean("TemporaryElytra")) { // checking if it contains TemporaryElytra with a value of true
                    this.equipStack(EquipmentSlot.CHEST, ItemStack.EMPTY); // set to wear nothing
                }
            }
        }
    }
}
