package com.vandendaelen.autoafkkicker.handlers;

import com.vandendaelen.autoafkkicker.AutoAfkKicker;
import com.vandendaelen.autoafkkicker.configs.AutoKickConfig;
import com.vandendaelen.autoafkkicker.objects.Session;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = AutoAfkKicker.MOD_ID)
@SideOnly(Side.SERVER)
public class AutoKickerServerEventHandler {

    public static HashMap<UUID, Session> sessions = new HashMap<UUID, Session>();
    public static List<EntityPlayerMP> playersToKick = new ArrayList<>();
    public static long warnTimerTick = 0;
    public static long kickTimerTick = 0;

    @SubscribeEvent
    public static  void onPlayerInteract(PlayerInteractEvent event) {
        Session s_player = sessions.get(event.getEntityPlayer().getGameProfile().getId());
        if (s_player != null) {
            s_player.reset();
        }
    }

    @SubscribeEvent
    public static void onServerTickEvent(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.START){
            if (!sessions.isEmpty()){
                for (Session session : sessions.values()){
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
                        session.reset();
                    }
                }
            }
        }
        else if (event.phase == TickEvent.Phase.END && !playersToKick.isEmpty()){
            for (EntityPlayerMP player : playersToKick){
                player.connection.disconnect(new TextComponentString(AutoKickConfig.AfkTimer.discMsg));
            }
            playersToKick.clear();
        }
    }

    @SubscribeEvent
    public static void onPlayerConnectionEvent(PlayerEvent.PlayerLoggedInEvent event){
        sessions.put(event.player.getGameProfile().getId(),new Session((EntityPlayerMP)event.player,event.player.getPosition()));
    }

    @SubscribeEvent
    public static void onPlayerDisconnectionEvent(PlayerEvent.PlayerLoggedOutEvent event){
        sessions.remove(event.player.getGameProfile().getId());
    }
}
