package a.b.noride2.utils.extension_properties

import com.github.cecnull1.cecnull1lib.utils.nbt.getModData
import constant.Constant.MODDatas.MOD_ID
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.Entity

object extension_properties {
    @JvmStatic
    fun eGetPersistentData(entity: Entity): CompoundTag {
        return entity.getModData(MOD_ID)
    }
}