package com.vandendaelen.autoafkkicker.handlers;

import com.vandendaelen.autoafkkicker.AutoAfkKicker;
import com.vandendaelen.autoafkkicker.configs.AutoKickConfig;
import com.vandendaelen.autoafkkicker.objects.Session;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = AutoAfkKicker.MOD_ID)
public class AutoKickerServerEventHandler {

    public static List<Session> sessions = new ArrayList<>();
    public static List<EntityPlayerMP> playersToKick = new ArrayList<>();

    @SubscribeEvent
    public static void onServerTickEvent(TickEvent.ServerTickEvent event) {
        long warnTimerTick = AutoKickConfig.AfkTimer.warnTimer*60*20;
        long kickTimerTick = AutoKickConfig.AfkTimer.kickTimer*60*20;

        if (event.phase == TickEvent.Phase.START){

            if (!sessions.isEmpty()){
                for (Session session : sessions){
                    if (session.getPlayer().getPosition().equals(session.getPos())){
                        session.increaseTimer();
                        if (session.getTickAFK() == warnTimerTick){
                            FMLCommonHandler.instance().getMinecraftServerInstance().sendMessage(new TextComponentString(session.getPlayer().getName()+" is now AFK"));
                            session.setAfk(true);
                        }
                        else if (session.getTickAFK() == kickTimerTick){
                            playersToKick.add(session.getPlayer());
                        }
                    }
                    else {
                        if (session.isAfk()){
                            FMLCommonHandler.instance().getMinecraftServerInstance().sendMessage(new TextComponentString(session.getPlayer().getName()+" isn't longer AFK"));
                        }
                        session.setAfk(false);
                        session.setTickAFK(0);
                        session.setPos(session.getPlayer().getPosition());
                    }
                }
            }
        }
        else if (event.phase == TickEvent.Phase.END && !playersToKick.isEmpty()){
            for (EntityPlayerMP player : playersToKick){
                player.connection.disconnect(new TextComponentString(AutoKickConfig.AfkTimer.discMsg));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerConnectionEvent(PlayerEvent.PlayerLoggedInEvent event){
        sessions.add(new Session((EntityPlayerMP)event.player,event.player.getPosition()));
    }

    @SubscribeEvent
    public static void onPlayerDisconnectionEvent(PlayerEvent.PlayerLoggedOutEvent event){
        sessions.removeIf(session -> session.getPlayer().getName().equals(event.player.getName()));
    }
}
