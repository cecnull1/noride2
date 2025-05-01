package a.b.noride2_changed_compatible

import a.b.noride2_changed_compatible.enchantment.QiangZhiTransfur
import a.b.noride2_changed_compatible.enchantment.YongJiuShouHua
import constant.Constant
import net.minecraftforge.fml.common.Mod
import com.github.cecnull1.cecnull1lib.utils.InfixFunction.addTo

@Mod(Constant.MODDatas.Noride2Changed.MOD_ID)
class Noride2Changed {
    init {
        QiangZhiTransfur.ENCHANTMENTS addTo Constant.FML.modEventBus
        YongJiuShouHua.ENCHANTMENTS addTo Constant.FML.modEventBus
    }
}