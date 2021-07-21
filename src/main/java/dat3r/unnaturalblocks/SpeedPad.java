package dat3r.unnaturalblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.PistonBlock;
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

public class SpeedPad extends FacingBlock {
    public SpeedPad(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.isClient()) { // if it's on the server side
            if (entity instanceof LivingEntity) { // check if this entity is a living entity (as opposed to a armour stand or arrow or something)
                LivingEntity le = (LivingEntity) entity; // this just creates another variable to access the "entity" parameter, but allowing us to use the functions in LivingEntity
                if (!le.hasStatusEffect(StatusEffects.SPEED)) { // if it doesn't already have speed
                    world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_NOTE_BLOCK_PLING, SoundCategory.BLOCKS, 1.0f, 1.0f); // play a sound. pling!
                }

                StatusEffectInstance SpeedpadStatusEffectInstance = new StatusEffectInstance(StatusEffects.SPEED, 50, 2, true, false); // create a status effect instance of speed
                le.addStatusEffect(SpeedpadStatusEffectInstance); // add the status effect to the entity
            }
        }
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite().getOpposite());
    }
}
