package a.b.noride2;

import a.b.noride2.constant.Constant;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constant.MODDatas.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class NE {
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        // 注册aEntity的属性供应商
        event.put(aEntity.A_ENTITY.get(), aEntity.createAttributes().build());
    }
}
