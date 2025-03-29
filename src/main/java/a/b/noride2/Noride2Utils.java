package a.b.noride2;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class Noride2Utils {
    public static void applySpeedAdjustment(@NotNull Entity entity, @NotNull Vec3 motionVector, @NotNull Vec3 rotationVector, double speed, boolean adjustY) {
        Vec3 newMotion = new Vec3(
                motionVector.x + speed * Math.sin(rotationVector.x),
                adjustY ? (motionVector.y + speed * Math.sin(rotationVector.y)) : motionVector.y,
                motionVector.z + speed * Math.sin(rotationVector.z)
        );
        entity.setDeltaMovement(newMotion);
    }

    static public void JH_Code(@NotNull Entity entity) {
        Vec3 MotionVector = entity.getDeltaMovement();
        if (!(entity instanceof Player player) || !player.isFallFlying()) {
            if (entity.isSwimming()) {
                VectorCalc1(1.05, entity, MotionVector);
            } else if (!entity.isInWaterOrBubble() && entity.isOnGround()) {
                VectorCalc1(1.35, entity, MotionVector);
            } else if (!entity.isInWaterOrBubble()) {
                VectorCalc1(1.05, entity, MotionVector);
            } else {
                VectorCalc1(1.1175, entity, MotionVector);
            }
        }
    }

    public static void VectorCalc1(double speed, @NotNull Entity entity, Vec3 motionVector) {
        double Speed_y = 1;
        entity.setDeltaMovement(new Vec3(
                motionVector.x * speed,
                motionVector.y * Speed_y,
                motionVector.z * speed
        ));
    }
}
