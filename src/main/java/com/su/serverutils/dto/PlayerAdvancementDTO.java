package com.su.serverutils.dto;

import net.minecraft.advancements.Advancement;
import net.minecraft.server.level.ServerPlayer;

public class PlayerAdvancementDTO {

    private String playerUUID;
    private String advancementId;

    public PlayerAdvancementDTO(String playerUUID, String advancementId) {
        this.playerUUID = playerUUID;
        this.advancementId = advancementId;
    }

    public PlayerAdvancementDTO(ServerPlayer player, Advancement advancement) {
        this.playerUUID = player.getUUID().toString();
        this.advancementId = advancement.getId().toString();
    }

    public PlayerAdvancementDTO() {
    }

    public String getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(String playerUUID) {
        this.playerUUID = playerUUID;
    }

    public String getAdvancementId() {
        return advancementId;
    }

    public void setAdvancementId(String advancementId) {
        this.advancementId = advancementId;
    }

}
