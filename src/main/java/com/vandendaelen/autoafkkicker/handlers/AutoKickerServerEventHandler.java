package com.vandendaelen.autoafkkicker.handlers;

import com.vandendaelen.autoafkkicker.AutoAfkKicker;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = AutoAfkKicker.MOD_ID)
public class AutoKickerServerEventHandler {

    @SubscribeEvent
    public static void onServerTickEvent(TickEvent.ServerTickEvent event) {

    }

    @SubscribeEvent
    public static void onPlayerConnectionEvent(FMLNetworkEvent.ServerConnectionFromClientEvent event){

    }

    @SubscribeEvent
    public static void onPlayerDisconnectionEvent(FMLNetworkEvent.ServerConnectionFromClientEvent event){

    }
}
