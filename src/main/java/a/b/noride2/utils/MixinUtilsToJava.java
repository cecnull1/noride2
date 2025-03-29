package a.b.noride2.utils;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class MixinUtilsToJava {
    // 私有构造函数，防止外部实例化
    private MixinUtilsToJava() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
    static public <T> void creturn(CallbackInfoReturnable<T> cir, T value) {
        MixinUtils.INSTANCE.creturn(cir, value);
    }

    static public void creturn(CallbackInfo ci) {
        MixinUtils.INSTANCE.creturn(ci);
    }
}
