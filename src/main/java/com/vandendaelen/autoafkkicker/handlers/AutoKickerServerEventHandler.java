package com.vandendaelen.autoafkkicker.handlers;

import com.vandendaelen.autoafkkicker.AutoAfkKicker;
import com.vandendaelen.autoafkkicker.objects.Session;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = AutoAfkKicker.MOD_ID)
public class AutoKickerServerEventHandler {

    public static List<Session> sessions;

    @SubscribeEvent
    public static void onServerTickEvent(TickEvent.ServerTickEvent event) {

    }

    @SubscribeEvent
    public static void onPlayerConnectionEvent(PlayerEvent.PlayerLoggedInEvent event){
        sessions.add(new Session((EntityPlayerMP)event.player,event.player.getPosition(),0));
    }

    @SubscribeEvent
    public static void onPlayerDisconnectionEvent(PlayerEvent.PlayerLoggedOutEvent event){
        for (Session session : sessions){
            if (session.getPlayer().getName().equals(event.player.getName())){
                sessions.remove(session);
            }
        }
    }
}
