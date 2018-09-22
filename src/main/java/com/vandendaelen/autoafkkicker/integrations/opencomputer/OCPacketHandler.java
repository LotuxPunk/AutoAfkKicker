package com.vandendaelen.autoafkkicker.integrations.opencomputer;

import com.vandendaelen.autoafkkicker.AutoAfkKicker;
import com.vandendaelen.autoafkkicker.configs.AutoKickConfig;
import com.vandendaelen.autoafkkicker.handlers.AutoKickerServerEventHandler;
import com.vandendaelen.autoafkkicker.objects.Session;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class OCPacketHandler {
    @SubscribeEvent
    public void onPacketEvent(FMLNetworkEvent.ServerCustomPacketEvent packet) {
        try {
            INetHandler handler = packet.getPacket().getOrigin().getNetHandler();
            if (handler instanceof NetHandlerPlayServer) {
                NetHandlerPlayServer handlerPS = (NetHandlerPlayServer) handler;
                Session s_player = AutoKickerServerEventHandler.sessions.get(handlerPS.player.getGameProfile().getId());
                s_player.reset(s_player.isAfk());
                if (AutoKickConfig.DEBUGGING.debug){
                    AutoAfkKicker.LOGGER.info(handlerPS.player.getName()+" OC packet");
                }
            }
        } catch (Exception e) {
            if (AutoKickConfig.DEBUGGING.debug){
                AutoAfkKicker.LOGGER.info(e.getMessage());
            }
        }

    }
}
