package a.b.noride2

import a.b.noride2.enchantment.Enchantments
import a.b.noride2.utils.infix.InfixFunction.addTo
import constant.Constant
import constant.Constant.FML.modEventBus
import net.minecraftforge.fml.common.Mod

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Constant.MODDatas.MOD_ID)
class Noride2 {
    init {
        // 注册附魔
        Enchantments.Wufaxiacheng.ENCHANTMENTS addTo modEventBus
        Enchantments.Qiangzhifeixing.ENCHANTMENTS addTo modEventBus
        Enchantments.Qiaochiqudong.ENCHANTMENTS addTo modEventBus
        Enchantments.Zidongyidong.ENCHANTMENTS addTo modEventBus
        Enchantments.Jiaohua.ENCHANTMENTS addTo modEventBus
        Enchantments.BuXiaCheng.ENCHANTMENTS addTo modEventBus
        Enchantments.ZhiNengXiaCheng.ENCHANTMENTS addTo modEventBus
        Enchantments.PiaoFu.ENCHANTMENTS addTo modEventBus
        Enchantments.QiangZhiPaTi.ENCHANTMENTS addTo modEventBus
        Enchantments.QiangZhiJiPao.ENCHANTMENTS addTo modEventBus
        Enchantments.QiangZhiYouYong.ENCHANTMENTS addTo modEventBus
        Enchantments.ChuangYi.ENCHANTMENTS addTo modEventBus
        Enchantments.ChuangHua.ENCHANTMENTS addTo modEventBus
        Enchantments.ZiYouChuangXing.ENCHANTMENTS addTo modEventBus
        Enchantments.NotInWater.ENCHANTMENTS addTo modEventBus
        Enchantments.NotInLava.ENCHANTMENTS addTo modEventBus
        Enchantments.InWater.ENCHANTMENTS addTo modEventBus
        Enchantments.InLava.ENCHANTMENTS addTo modEventBus
        Enchantments.QiangZhiCFly.ENCHANTMENTS addTo modEventBus
    }
}