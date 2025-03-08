package a.b.noride2_changed_compatible

import a.b.noride2_changed_compatible.enchantment.QiangZhiTransfur
import constant.Constant
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.common.Mod

@Mod(Constant.MODDatas.Noride2Changed.MOD_ID)
class Noride2Changed {
    init {
        if (ModList.get().isLoaded(Constant.MODDatas.BetterNeonMod.MOD_ID) && Constant.FML.betterNeonIsLoaded) {
            QiangZhiTransfur.ENCHANTMENTS.register(Constant.FML.modEventBus)
        }
    }
}