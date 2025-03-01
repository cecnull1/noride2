package a.b.noride2.mixin.Entity;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static a.b.noride2_bn_compatible.eventLogic.Eee2Kt.tickLogic1;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        final Player player = (Player) (Object) this;
        tickLogic1(player, player.getPersistentData());
    }
}
