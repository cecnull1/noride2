package a.b.noride2

import a.b.noride2.enchantment.Enchantments
import constant.Constant
import constant.Constant.FML.modEventBus
import net.minecraftforge.fml.common.Mod

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Constant.MODDatas.MOD_ID)
class Noride2 {
    init {
        // 注册附魔
        Enchantments.Wufaxiacheng.ENCHANTMENTS.register(modEventBus)
        Enchantments.Qiangzhifeixing.ENCHANTMENTS.register(modEventBus)
        Enchantments.Qiaochiqudong.ENCHANTMENTS.register(modEventBus)
        Enchantments.Zidongyidong.ENCHANTMENTS.register(modEventBus)
        Enchantments.Jiaohua.ENCHANTMENTS.register(modEventBus)
        Enchantments.BuXiaCheng.ENCHANTMENTS.register(modEventBus)
        Enchantments.ZhiNengXiaCheng.ENCHANTMENTS.register(modEventBus)
        Enchantments.PiaoFu.ENCHANTMENTS.register(modEventBus)
        Enchantments.QiangZhiPaTi.ENCHANTMENTS.register(modEventBus)
        Enchantments.QiangZhiJiPao.ENCHANTMENTS.register(modEventBus)
        Enchantments.QiangZhiYouYong.ENCHANTMENTS.register(modEventBus)
        Enchantments.ChuangYi.ENCHANTMENTS.register(modEventBus)
        Enchantments.ChuangHua.ENCHANTMENTS.register(modEventBus)
        Enchantments.ZiYouChuangXing.ENCHANTMENTS.register(modEventBus)
        Enchantments.NotInWater.ENCHANTMENTS.register(modEventBus)
        Enchantments.NotInLava.ENCHANTMENTS.register(modEventBus)
        Enchantments.InWater.ENCHANTMENTS.register(modEventBus)
        Enchantments.InLava.ENCHANTMENTS.register(modEventBus)
    }
}