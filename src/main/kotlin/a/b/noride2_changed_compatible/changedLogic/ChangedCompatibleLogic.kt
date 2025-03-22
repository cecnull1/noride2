package a.b.noride2_changed_compatible.changedLogic

import a.b.noride2.logic.eGetPersistentData
import a.b.noride2.Utils.compoundTag.nbt
import a.b.noride2_bn_compatible.eventLogic.bnCLogic1
import constant.Constant
import net.minecraft.world.entity.Entity

fun changedCompatibleLogic(entity: Entity) {
    if (Constant.FML.betterNeonIsLoaded) {

        // 强制TRANSFUR
        fun qiangZhiTransfur(entity: Entity) {
            if (eGetPersistentData(entity).getBoolean(Constant.NBTKeys.IS_TRANSFUR)) {
                bnCLogic1(entity, entity.persistentData)
            }
        }
        qiangZhiTransfur(entity)
    }
}

fun transfur(entity: Entity, transfurType: String) {
    val nbtData = entity.nbt
    if (nbtData.getString(Constant.NBTKeys.TRANSFUR_VARIANT) == Constant.TransfurVariantType.NONE_TRANSFUR_VARIANT) {
        nbtData.putString(Constant.NBTKeys.TRANSFUR_VARIANT, transfurType)
        entity.nbt = nbtData
    }
}