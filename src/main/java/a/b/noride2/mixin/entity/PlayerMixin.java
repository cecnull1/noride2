package a.b.noride2.mixin.entity;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static a.b.noride2.logic.Noride2LogicKt.noride2PlayerTickLogic;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        // 常量声明
        final Player player = (Player) (Object) this;
        noride2PlayerTickLogic(player);
    }
}
