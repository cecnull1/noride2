package a.b.noride2.mixin.Entity;

import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static a.b.noride2.logic.Noride2LogicKt.noride2BoatEntityTickLogic;

@Mixin(Boat.class)
public class BoatEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        final Boat BoatEntity = (Boat) (Object) this;
        noride2BoatEntityTickLogic(BoatEntity);
    }
}
