package a.b.noride2;

import a.b.noride2.enchantment.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Noride2.MODID)
public class Noride2 {

    public static final String MODID = "noride2";

    public Noride2() {
        // 注册附魔
        Wufaxiacheng.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        Qiangzhifeixing.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        Qiaochiqudong.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        Zidongyidong.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        Jiaohua.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BuXiaCheng.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ZhiNengXiaCheng.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        PiaoFu.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        QiangZhiPaTi.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        QiangZhiJiPao.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        QiangZhiYouYong.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}