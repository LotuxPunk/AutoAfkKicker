package com.vandendaelen.autoafkkicker.integrations.computercraft;

public class CCPacketHandler {
//    @SubscribeEvent(priority = EventPriority.HIGHEST) // Because we need to copy the ByteBuf before ComputerCraft read it, otherwise the buffer will be empty
//    public void onPacketEvent(FMLNetworkEvent.ServerCustomPacketEvent packet) {
//        try {
//            ByteBuf buf = packet.getPacket().payload().copy(); //Copy the ByteBuf
//            if (buf.readByte() == ComputerCraftPacket.QueueEvent) { //Check if the packet is a QueueEvent packet
//                INetHandler handler = packet.getPacket().getOrigin().getNetHandler();
//                if (handler instanceof NetHandlerPlayServer) {
//                    NetHandlerPlayServer handlerPS = (NetHandlerPlayServer) handler;
//                    Session s_player = AutoKickerServerEventHandler.sessions.get(handlerPS.player.getGameProfile().getId());
//                    s_player.reset(s_player.isAfk());
//                    if (AutoKickConfig.DEBUGGING.debug) {
//                        AutoAfkKicker.LOGGER.info(handlerPS.player.getName() + " CC packet");
//                    }
//                }
//            }
//        } catch (Exception e) {
//            if (AutoKickConfig.DEBUGGING.debug){
//                AutoAfkKicker.LOGGER.info(e.getMessage());
//            }
//        }
//    }
}
