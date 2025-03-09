package a.b.noride2_bn_compatible.eventLogic

import a.b.noride2.utils.compoundTag.nbt
import a.b.noride2.utils.function.inEntityNbtAndRemove
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
        if (inEntityNbtAndRemove(entity, Constant.NBTKeys.BetterNeonMod.NORIDE2_BN_C_JH_TF)) {
            bnCLogic1(entity, persistentData)
        }
    }
}

fun bnCLogic1(entity: Entity, persistentData: CompoundTag) {
    if (!entity.level.isClientSide()) {
        val transfurType: String =
            when (persistentData.getDouble(Constant.NBTKeys.BetterNeonMod.NORIDE2_BN_C_JH_TF_TYPE).toInt()) {
                0 -> Constant.TransfurVariantType.Changed.LATEX_DARK_LATEX_YUFENG
                1 -> Constant.TransfurVariantType.Changed.LATEX_PINK_YUIN_DRAGON
                2 -> Constant.TransfurVariantType.Changed.GAS_WOLF
                else -> Constant.TransfurVariantType.Changed.LATEX_DARK_LATEX_YUFENG
            }
        val nbtData = entity.nbt
        if (nbtData.getString(Constant.NBTKeys.TRANSFUR_VARIANT) == Constant.TransfurVariantType.NONE_TRANSFUR_VARIANT) {
            nbtData.putString(Constant.NBTKeys.TRANSFUR_VARIANT, transfurType)
            entity.nbt = nbtData
        }
    }
}