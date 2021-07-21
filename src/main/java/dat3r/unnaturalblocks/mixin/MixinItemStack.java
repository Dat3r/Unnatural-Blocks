package dat3r.unnaturalblocks.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin (ItemStack.class)
public abstract class MixinItemStack {
    @Shadow public abstract NbtCompound getOrCreateNbt();

    @Inject(method = "hasGlint", at = @At("HEAD"), cancellable = true)
    private void hasGlint(CallbackInfoReturnable<Boolean> Info){
        if (this.getOrCreateNbt().getBoolean("TemporaryElytra")) {
            Info.setReturnValue(false);
        }
    }

}
