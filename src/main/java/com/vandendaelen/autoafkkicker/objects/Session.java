package com.vandendaelen.autoafkkicker.objects;

import com.vandendaelen.autoafkkicker.utils.AAKString;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.server.permission.PermissionAPI;

import static com.vandendaelen.autoafkkicker.configs.AutoKickConfig.TEXT;

public class Session {
    private EntityPlayerMP player;
    private BlockPos pos;
    private long tickAFK;
    private boolean isAfk;

    public Session(EntityPlayerMP player, BlockPos pos) {
        this.player = player;
        this.pos = pos;
        this.tickAFK = 0;
        this.isAfk = false;
    }

    public void increaseTimer(){
        tickAFK++;
    }

    public void reset(boolean sendMessage){
        this.setAfk(false, sendMessage);
        this.tickAFK=0;
        this.pos = this.getPlayer().getPosition();
    }

    public EntityPlayerMP getPlayer() {
        return player;
    }

    public BlockPos getPos() {
        return pos;
    }

    public long getTickAFK() {
        return tickAFK;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    public void setTickAFK(long tickAFK) {
        this.tickAFK = tickAFK;
    }

    public boolean isAfk() {
        return isAfk;
    }

    public void setAfk(boolean afk, boolean sendMessage) {
        isAfk = afk;
        if (sendMessage) {
            if (afk)
                FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().sendMessage(new TextComponentString(String.format(TEXT.AFKMessage,this.player.getName())));
            else
                FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().sendMessage(new TextComponentString(String.format(TEXT.noLongerAFKMessage,this.player.getName())));
        }
    }

    public boolean isKickable(){
        return !PermissionAPI.hasPermission(player, AAKString.Permissions.AFK_BYPASS);
    }
}
