package a.b.noride2.Utils;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class MixinUtils {

    /**
     * 使用自定义名称 creturn 来设置返回值并取消原方法的执行。
     *
     * @param <T>  返回值的类型
     * @param cir  CallbackInfoReturnable 对象
     * @param value 要设置的返回值
     */
    public static <T> void creturn(CallbackInfoReturnable<T> cir, T value) {
        if (cir != null) {
            cir.setReturnValue(value);
            cir.cancel();
        }
    }

    // 如果你需要处理不返回任何值的方法，可以重载这个方法以接受 CallbackInfo
    public static void creturn(CallbackInfo cir) {
        if (cir != null) {
            cir.cancel();
        }
    }
}