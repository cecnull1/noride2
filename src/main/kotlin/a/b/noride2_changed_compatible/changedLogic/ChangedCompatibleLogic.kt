package a.b.noride2_changed_compatible.changedLogic

import a.b.noride2.logic.eGetPersistentData
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