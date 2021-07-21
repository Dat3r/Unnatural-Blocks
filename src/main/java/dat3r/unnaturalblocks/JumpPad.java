package dat3r.unnaturalblocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class JumpPad extends FacingBlock {

    public JumpPad(FabricBlockSettings settings, int jumpBoost, int speedBoost) {
        super(settings);
        this.speedBoost = speedBoost;
        this.jumpBoost = jumpBoost;
    }

    int jumpBoost;
    int speedBoost;

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.isClient()) {
            if (entity instanceof LivingEntity) {
                LivingEntity le = (LivingEntity) entity;
                if (!le.hasStatusEffect(StatusEffects.JUMP_BOOST)) {
                    world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_NOTE_BLOCK_BASEDRUM, SoundCategory.BLOCKS, 1.0f, 1.0f);
                }
                StatusEffectInstance JumpPadStatusEffectInstance = new StatusEffectInstance(StatusEffects.JUMP_BOOST, 10, this.jumpBoost, true, false);
                StatusEffectInstance JumpPadStatusEffectInstance2 = new StatusEffectInstance(StatusEffects.SPEED, 20, this.speedBoost, true, false);
                le.addStatusEffect(JumpPadStatusEffectInstance);
                le.addStatusEffect(JumpPadStatusEffectInstance2);
            }
        }
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite().getOpposite());
    }
}

