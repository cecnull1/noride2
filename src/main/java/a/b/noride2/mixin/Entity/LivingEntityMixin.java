package a.b.noride2.mixin.Entity;

import constant.Constant;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static a.b.noride2.utils.MixinUtilsToJava.creturn;
import static a.b.noride2.logic.Noride2LogicKt.eGetPersistentData;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "onClimbable", at = @At("HEAD"), cancellable = true)
    private void onClimbable(CallbackInfoReturnable<Boolean> cir) {
        // 获取实体实例
        LivingEntity entity = (LivingEntity) (Object) this;

        // 强制爬梯
        if (eGetPersistentData(entity).getBoolean(Constant.NBTKeys.IS_QIANGZHI_PA_TI)) {
            creturn(cir, true);
        }
    }

    @Inject(method = "isFallFlying", at = @At("HEAD"), cancellable = true)
    private void isFallFlying(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        // 强制鞘翅飞行
        if (eGetPersistentData(entity).getBoolean(Constant.NBTKeys.IS_FALL_FLY)) {
            creturn(cir, true);
        }
    }
}
