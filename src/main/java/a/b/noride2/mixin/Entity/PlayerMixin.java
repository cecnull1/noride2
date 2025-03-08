package a.b.noride2.mixin.Entity;

import a.b.noride2.Utils.EUtils;
import a.b.noride2.enchantment.*;
import a.b.noride2_changed_compatible.enchantment.QiangZhiTransfur;
import constant.Constant;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        // 常量声明
        final Player player = (Player) (Object) this;
        final CompoundTag PersistentData = player.getPersistentData();

        // 变量声明
        CompoundTag Mod_PersistentData = PersistentData.getCompound(Constant.MODDatas.MOD_ID);

        // 鞘翅驱动
        final boolean isQiaoChiQuDong = EUtils.hasSpecificEnchantment(player, Qiaochiqudong.QIAOCHI_QU_DONG.get());
        Mod_PersistentData.putBoolean(Constant.NBTKeys.IS_QIAOCHI_QU_DONG, isQiaoChiQuDong);
        final int QiaoChiQuDong_LEVEL = EUtils.getHighestEnchantmentLevel(player, Qiaochiqudong.QIAOCHI_QU_DONG.get());
        Mod_PersistentData.putInt(Constant.NBTKeys.IS_QIAOCHI_QU_DONG_LEVEL, QiaoChiQuDong_LEVEL);

        // 自动移动
        final boolean isAUTO_MOVE = EUtils.hasSpecificEnchantment(player, Zidongyidong.ZIDONG_YI_DONG.get());
        Mod_PersistentData.putBoolean(Constant.NBTKeys.IS_AUTO_MOVE, isAUTO_MOVE);
        final int AUTO_MOVE_LEVEL = EUtils.getHighestEnchantmentLevel(player, Zidongyidong.ZIDONG_YI_DONG.get());
        Mod_PersistentData.putInt(Constant.NBTKeys.IS_AUTO_MOVE_LEVEL, AUTO_MOVE_LEVEL);

        // 脚滑
        final boolean isJIAO_HUA = EUtils.hasSpecificEnchantment(player, Jiaohua.JIAOHUA.get());
        Mod_PersistentData.putBoolean(Constant.NBTKeys.IS_JIAO_HUA, isJIAO_HUA);

        // 不下沉
        final boolean isBUXIA_CHENG = EUtils.hasSpecificEnchantment(player, BuXiaCheng.BUXIA_CHENG.get());
        Mod_PersistentData.putBoolean(Constant.NBTKeys.IS_BUXIA_CHENG, isBUXIA_CHENG);

        // 只能下沉
        final boolean isZHI_NENG_XIA_CHENG = EUtils.hasSpecificEnchantment(player, ZhiNengXiaCheng.ZHINENG_XIA_CHENG.get());
        Mod_PersistentData.putBoolean(Constant.NBTKeys.IS_ZHI_NENG_XIA_CHENG, isZHI_NENG_XIA_CHENG);

        // 飘浮（不是漂浮）
        final boolean isNoGravity = EUtils.hasSpecificEnchantment(player, PiaoFu.PIAOFU.get());
        Mod_PersistentData.putBoolean(Constant.NBTKeys.IS_NO_GRAVITY, isNoGravity);

        // 强制爬梯
        final boolean isQiangZhiPaTi = EUtils.hasSpecificEnchantment(player, QiangZhiPaTi.QIANGZHI_PA_TI.get());
        Mod_PersistentData.putBoolean(Constant.NBTKeys.IS_QIANGZHI_PA_TI, isQiangZhiPaTi);

        // 强制鞘翅飞行
        final boolean isFallFly = EUtils.hasSpecificEnchantment(player, Qiangzhifeixing.QIANGZHI_FEI_XING.get());
        Mod_PersistentData.putBoolean(Constant.NBTKeys.IS_FALL_FLY, isFallFly);

        // 强制游泳
        final boolean isSwimming = EUtils.hasSpecificEnchantment(player, QiangZhiYouYong.QIANGZHI_YOU_YONG.get());
        Mod_PersistentData.putBoolean(Constant.NBTKeys.IS_SWIMMING, isSwimming);

        // 自由穿行
        final boolean isZiYouChuangXing = EUtils.hasSpecificEnchantment(player, ZiYouChuangXing.ZIYOU_CHUANGXING.get());
        Mod_PersistentData.putBoolean(Constant.NBTKeys.IS_ZIYOU_CHUANXING, isZiYouChuangXing);

        // 强制TRANSFUR
        final boolean isTransfur = EUtils.hasSpecificEnchantment(player, QiangZhiTransfur.QIANG_ZHI_TRANSFUR.get());
        Mod_PersistentData.putBoolean(Constant.NBTKeys.IS_TRANSFUR, isTransfur);

        // 收尾
        PersistentData.put(Constant.MODDatas.MOD_ID, Mod_PersistentData);
    }
}
