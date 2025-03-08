package a.b.noride2;

import constant.Constant;
import a.b.noride2.enchantment.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Constant.MODDatas.MOD_ID)
public class Noride2 {

    public Noride2() {
        // 注册附魔
        Wufaxiacheng.ENCHANTMENTS.register(Constant.FML.INSTANCE.getModEventBus());
        Qiangzhifeixing.ENCHANTMENTS.register(Constant.FML.INSTANCE.getModEventBus());
        Qiaochiqudong.ENCHANTMENTS.register(Constant.FML.INSTANCE.getModEventBus());
        Zidongyidong.ENCHANTMENTS.register(Constant.FML.INSTANCE.getModEventBus());
        Jiaohua.ENCHANTMENTS.register(Constant.FML.INSTANCE.getModEventBus());
        BuXiaCheng.ENCHANTMENTS.register(Constant.FML.INSTANCE.getModEventBus());
        ZhiNengXiaCheng.ENCHANTMENTS.register(Constant.FML.INSTANCE.getModEventBus());
        PiaoFu.ENCHANTMENTS.register(Constant.FML.INSTANCE.getModEventBus());
        QiangZhiPaTi.ENCHANTMENTS.register(Constant.FML.INSTANCE.getModEventBus());
        QiangZhiJiPao.ENCHANTMENTS.register(Constant.FML.INSTANCE.getModEventBus());
        QiangZhiYouYong.ENCHANTMENTS.register(Constant.FML.INSTANCE.getModEventBus());
        ChuangYi.ENCHANTMENTS.register(Constant.FML.INSTANCE.getModEventBus());
        ChuangHua.ENCHANTMENTS.register(Constant.FML.INSTANCE.getModEventBus());
        ZiYouChuangXing.ENCHANTMENTS.register(Constant.FML.INSTANCE.getModEventBus());
    }
}