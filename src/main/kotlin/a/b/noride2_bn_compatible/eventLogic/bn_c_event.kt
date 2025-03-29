package a.b.noride2_bn_compatible.eventLogic

import a.b.noride2.utils.function.inEntityPersistentNbtAndRemove
import a.b.noride2.utils.infix.InfixFunction.nbtNotIn
import a.b.noride2.utils.infix.InfixFunction.serverRun
import a.b.noride2_changed_compatible.changedLogic.transfur
import constant.Constant
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraftforge.fml.ModList

fun tickLogic1(entity: Entity, persistentData: CompoundTag) {
    if (!ModList.get().isLoaded(Constant.MODDatas.BetterNeonMod.MOD_ID)) {
        return
    }
    if (entity is Player) {
        if (entity inEntityPersistentNbtAndRemove Constant.NBTKeys.BetterNeonMod.NORIDE2_BN_C_JH_TF) {
            bnCLogic1(entity, persistentData)
        }
    }
}

fun bnCLogic1(entity: Entity, persistentData: CompoundTag) {
    entity serverRun {
        if (persistentData nbtNotIn Constant.NBTKeys.BetterNeonMod.NORIDE2_BN_C_JH_TF_TYPE) return
        val transfurType: String =
            when (persistentData.getDouble(Constant.NBTKeys.BetterNeonMod.NORIDE2_BN_C_JH_TF_TYPE).toInt()) {
                0 -> Constant.TransfurVariantType.Changed.LATEX_DARK_LATEX_YUFENG
                1 -> Constant.TransfurVariantType.Changed.LATEX_PINK_YUIN_DRAGON
                2 -> Constant.TransfurVariantType.Changed.GAS_WOLF
                3 -> Constant.TransfurVariantType.Changed.PURE_WHITE_LATEX_WOLF
                4 -> Constant.TransfurVariantType.ChangedAddonPlus.WOLFY
                5 -> Constant.TransfurVariantType.ChangedAddonPlus.EXPERIMENT_10
                6 -> Constant.TransfurVariantType.ChangedAddonPlus.KET_EXPERIMENT_009
                7 -> Constant.TransfurVariantType.ChangedAddonPlus.KET_EXPERIMENT_009_BOSS
                else -> Constant.TransfurVariantType.Changed.LATEX_DARK_LATEX_YUFENG
            }
        entity transfur transfurType
    }
}

