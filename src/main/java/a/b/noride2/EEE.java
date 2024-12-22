package a.b.noride2;

import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Noride2.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EEE {
    @SubscribeEvent
    public static void eee(EntityMountEvent event){
        if (event.getEntityMounting() == null || event.getEntityMounting().isRemoved()) {
            return;
        }

        if (!event.getEntityMounting().isAlive()) {
            return;
        }

        if (event.isDismounting()) {
            event.setCanceled(true);
        }
    }
}
