package dat3r.unnaturalblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PushPad extends Block {
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        Vec3d Look = entity.getRotationVector();
        entity.addVelocity(Look.x*7,3,Look.z*7);
    }
    public PushPad(Settings settings) {
        super(settings);
    }
}
