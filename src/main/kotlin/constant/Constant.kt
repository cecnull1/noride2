package constant

import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

object Constant {

    object NBTKeys {

        const val IS_CHUANG_HUA = "IS_CHUANG_HUA"
        const val IS_CHUANG_YI = "IS_CHUANG_YI"
        const val IS_TRANSFUR = "IS_TRANSFUR"
        const val NUM_QIAOCHI_QU_DONG_LEVEL = "NUM_QIAOCHI_QU_DONG_LEVEL"
        const val IS_QIAOCHI_QU_DONG = "IS_QIAOCHI_QU_DONG"
        const val IS_ZHI_NENG_XIA_CHENG = "IS_ZHI_NENG_XIA_CHENG"
        const val IS_QIANGZHI_PA_TI = "IS_QIANGZHI_PA_TI"
        const val IS_FALL_FLY = "IS_FALL_FLY"
        const val IS_NO_GRAVITY = "IS_NO_GRAVITY"
        const val IS_BUXIA_CHENG = "IS_BUXIA_CHENG"
        const val IS_SWIMMING = "IS_SWIMMING"
        const val IS_ZIYOU_CHUANXING = "IS_ZIYOU_CHUANXING"
        const val IS_JIAO_HUA = "IS_JIAO_HUA"
        const val IS_AUTO_MOVE_LEVEL = "IS_AUTO_MOVE_LEVEL"
        const val IS_AUTO_MOVE = "IS_AUTO_MOVE"

        object BetterNeonMod {
            const val NAI_WU_RAN = "BetterNeon_NaiWuRan"
            const val NORIDE2_BN_C_JH_TF = "Noride2_BN_C_JH_TF"
            const val NORIDE2_BN_C_JH_TF_TYPE = "Noride2_BN_C_JH_TF_TYPE"
            const val BETTERNEON_NAIWURAN = "BetterNeon_NaiWuRan"
        }

        const val TRANSFUR_VARIANT = "TransfurVariant"
    }

    object MODDatas {
        const val MOD_ID = "noride2"
        object BetterNeonMod {
            const val MOD_ID = "noride2_bn_c"
        }
        object Noride2Changed {
            const val MOD_ID = "noride2_changed_c"
        }
    }

    object TransfurVariantType {

        object Changed {
            const val LATEX_DARK_LATEX_YUFENG = "changed:form_dark_latex_yufeng"
            const val LATEX_PINK_YUIN_DRAGON = "changed:form_latex_pink_yuin_dragon"
            const val GAS_WOLF = "changed:form_gas_wolf"
        }

        const val NONE_TRANSFUR_VARIANT = ""
    }

    object FML {
        val modEventBus: IEventBus? = FMLJavaModLoadingContext.get().modEventBus
        val betterNeonIsLoaded: Boolean = ModList.get().isLoaded("better_neon")
    }
}