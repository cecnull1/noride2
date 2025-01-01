package a.b.noride2.mixin;

import a.b.noride2.Utils.EUtils;
import a.b.noride2.enchantment.QiangZhiJiPao;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static a.b.noride2.EEE.EEEEE;
import static a.b.noride2.Utils.MixinUtils.creturn;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "isSprinting", at = @At("HEAD"), cancellable = true)
    public void isSprinting(CallbackInfoReturnable<Boolean> cir) {
        if ((Entity) (Object) this instanceof Player player && EUtils.hasSpecificEnchantment(player, QiangZhiJiPao.QIANGZHI_JI_PAO.get())) {
            creturn(cir, true);
        }
    }

    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    public void isSwimming(CallbackInfoReturnable<Boolean> cir) {
        if ((Entity) (Object) this instanceof Player player && EEEEE) {
            creturn(cir, true);
        }
    }
}
