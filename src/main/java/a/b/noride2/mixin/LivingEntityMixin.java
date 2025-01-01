package a.b.noride2.mixin;

import a.b.noride2.enchantment.QiangZhiPaTi;
import a.b.noride2.Utils.EUtils;
import a.b.noride2.enchantment.Qiangzhifeixing;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import static a.b.noride2.Utils.MixinUtils.creturn;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "onClimbable", at = @At("HEAD"), cancellable = true)
    private void onClimbable(CallbackInfoReturnable<Boolean> cir) {
        // 获取实体实例
        LivingEntity entity = (LivingEntity) (Object) this;

        // 强制爬梯
        if (entity instanceof Player player && EUtils.hasSpecificEnchantment(player, QiangZhiPaTi.QIANGZHI_PA_TI.get())) {
            creturn(cir, true);
        }
    }

    @Inject(method = "isFallFlying", at = @At("HEAD"), cancellable = true)
    private void isFallFlying(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        // 强制鞘翅飞行
        if (entity instanceof Player player && EUtils.hasSpecificEnchantment(player, Qiangzhifeixing.QIANGZHI_FEI_XING.get())) {
            creturn(cir, true);
        }
    }
}
