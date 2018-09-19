package com.vandendaelen.autoafkkicker.objects;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

public class Session {
    private EntityPlayerMP player;
    private BlockPos pos;
    private long tickAFK;

    public Session(EntityPlayerMP player, BlockPos pos, long tickAFK) {
        this.player = player;
        this.pos = pos;
        this.tickAFK = tickAFK;
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
}
