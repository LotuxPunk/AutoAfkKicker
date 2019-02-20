package com.vandendaelen.autoafkkicker.objects;

import com.vandendaelen.autoafkkicker.configs.ConfigAAK;
import com.vandendaelen.autoafkkicker.utils.AAKStrings;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.minecraftforge.server.permission.PermissionAPI;

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

    public boolean isAfk() {
        return isAfk;
    }

    public void setAfk(boolean afk, boolean sendMessage) {
        isAfk = afk;
        if (sendMessage) {
            if (afk)
                ServerLifecycleHooks.getCurrentServer().getPlayerList().sendMessage(new TextComponentString(String.format(ConfigAAK.getAFKMessage(),this.player.getName())));
            else
                ServerLifecycleHooks.getCurrentServer().getPlayerList().sendMessage(new TextComponentString(String.format(ConfigAAK.getNoLongerAFKMessage(),this.player.getName())));
        }
    }

    public boolean isKickable(){
        return !PermissionAPI.hasPermission(player, AAKStrings.Permissions.AFK_BYPASS);
    }
}
