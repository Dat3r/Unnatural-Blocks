package dat3r.unnaturalblocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GlowBlock extends Block {
    public GlowBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.isClient()) {
            if (entity instanceof LivingEntity) {
                LivingEntity le = (LivingEntity) entity;
                if (!le.hasStatusEffect(StatusEffects.GLOWING)) {
                    world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_NOTE_BLOCK_BASEDRUM, SoundCategory.BLOCKS, 1.0f, 1.0f);
                }
                StatusEffectInstance JumpPadStatusEffectInstance = new StatusEffectInstance(StatusEffects.GLOWING, 160, 1, true, false);
                le.addStatusEffect(JumpPadStatusEffectInstance);

            }
        }
    }

}
