package com.vandendaelen.autoafkkicker.objects;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

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
        setTickAFK(getTickAFK()+1);
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

    public void setAfk(boolean afk) {
        isAfk = afk;
    }
}
