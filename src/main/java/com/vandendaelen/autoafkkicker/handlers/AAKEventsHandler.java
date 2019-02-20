package com.vandendaelen.autoafkkicker.handlers;

import com.vandendaelen.autoafkkicker.configs.ConfigAAK;
import com.vandendaelen.autoafkkicker.objects.Session;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AAKEventsHandler {
    private HashMap<UUID, Session> sessions = new HashMap<UUID, Session>();
    private List<EntityPlayerMP> playersToKick = new ArrayList<>();
    private long warnTimerTick = 0;
    private long kickTimerTick = 0;

    public AAKEventsHandler() {
        this.warnTimerTick = ConfigAAK.getWarnTimer()*60*20;
        this.kickTimerTick = ConfigAAK.getKickTimer()*60*20;
    }

    @SubscribeEvent
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Session s_player = sessions.get(event.getEntityPlayer().getGameProfile().getId());
        if (s_player != null) {
            s_player.reset(s_player.isAfk());
        }
    }

    @SubscribeEvent
    public void onCommandEvent(CommandEvent event) {
        Entity ent = event.getParseResults().getContext().getSource().getEntity();
        if (ent instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)ent;
            Session s_player = sessions.get(player.getGameProfile().getId());
            if (s_player != null) {
                s_player.reset(s_player.isAfk());
            }
        }
    }

    @SubscribeEvent
    public void onChatEvent(ServerChatEvent event) {
        Session s_player = sessions.get(event.getPlayer().getGameProfile().getId());
        if (s_player != null) {
            s_player.reset(s_player.isAfk());
        }
    }

    @SubscribeEvent
    public void onServerTickEvent(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.START){
            if (!sessions.isEmpty()){
                for (Session session : sessions.values()){
                    if (session.getPlayer().getPosition().equals(session.getPos())){
                        session.increaseTimer();
                        if (session.getTickAFK() == warnTimerTick){
                            session.setAfk(true,true);
                        }
                        else if (session.getTickAFK() >= kickTimerTick && session.isKickable()){
                            playersToKick.add(session.getPlayer());
                        }
                    }
                    else {
                        session.reset(session.isAfk());
                    }
                }
            }
        }
        else if (event.phase == TickEvent.Phase.END && !playersToKick.isEmpty()){
            for (EntityPlayerMP player : playersToKick){
                player.connection.disconnect(new TextComponentString(ConfigAAK.getDiscMsg()));
            }
            playersToKick.clear();
        }
    }

    @SubscribeEvent
    public void onPlayerConnectionEvent(PlayerEvent.PlayerLoggedInEvent event){
        sessions.put(event.getPlayer().getGameProfile().getId(),new Session((EntityPlayerMP)event.getPlayer(),event.getPlayer().getPosition()));
    }

    @SubscribeEvent
    public void onPlayerDisconnectionEvent(PlayerEvent.PlayerLoggedOutEvent event){
        sessions.remove(event.getPlayer().getGameProfile().getId());
    }
}
