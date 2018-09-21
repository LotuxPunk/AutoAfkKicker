package com.vandendaelen.autoafkkicker.objects;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class Session {
    public static String AFKMessage = "%s is now AFK";
    public static String noLongerAFKMessage = "%s is no longer AFK";
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
                this.player.sendMessage(new TextComponentString(String.format(AFKMessage,this.player.getName())));
            else
                this.player.sendMessage(new TextComponentString(String.format(noLongerAFKMessage,this.player.getName())));
        }
    }
}
