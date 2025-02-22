package a.b.noride2.mixin.Entity;

import a.b.noride2.Utils.EUtils;
import a.b.noride2.enchantment.ChuangHua;
import a.b.noride2.enchantment.ChuangYi;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Boat.class)
public class BoatEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        final Boat BoatEntity = (Boat) (Object) this;
        final float speed = 0.03f;
        final float speed2 = 1f;
        final float speed3;
        final List<Entity> entities = List.copyOf(BoatEntity.getPassengers());
        if (!entities.isEmpty() && entities.get(0) instanceof Player player) {
            if (EUtils.hasSpecificEnchantment(player, ChuangYi.CHUANG_YI.get())) {
                Vec3 newVel = BoatEntity.getLookAngle().multiply(1.0, 0.0, 1.0).normalize().multiply(speed, speed, speed);
                if (newVel.lengthSqr() <= 64) {
                    BoatEntity.setDeltaMovement(newVel.add(BoatEntity.getDeltaMovement().multiply(speed2, 0, speed2)));
                }
            }
            if (EUtils.hasSpecificEnchantment(player, ChuangHua.CHUANG_HUA.get())) {
                if (BoatEntity.isInWaterOrBubble()) {
                    speed3 = 0.1f;
                } else if (BoatEntity.isOnGround()) {
                    speed3 = 0.7f;
                } else {
                    speed3 = 0.1175f;
                }
                BoatEntity.setDeltaMovement(BoatEntity.getDeltaMovement().multiply(speed3, BoatEntity.getDeltaMovement().y, speed3).add(BoatEntity.getDeltaMovement()));
            }
        }
    }
}
