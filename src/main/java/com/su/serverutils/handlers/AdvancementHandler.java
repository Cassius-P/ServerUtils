package com.su.serverutils.handlers;

import com.google.gson.Gson;
import com.mojang.logging.LogUtils;
import com.su.serverutils.dto.PlayerAdvancementDTO;
import com.su.serverutils.helpers.HttpRequestHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;



@Mod.EventBusSubscriber
public class AdvancementHandler {

    private static final Logger logger = LogUtils.getLogger();

    @SubscribeEvent
    public void onAdvancementCompleted(AdvancementEvent.AdvancementEarnEvent event) {
        try{
            if (event.getEntity() instanceof ServerPlayer) {
                ServerPlayer player = (ServerPlayer) event.getEntity();
                if(player.getAdvancements().getOrStartProgress(event.getAdvancement()).isDone()) {

                    logger.info("Advancement completed: " + event.getAdvancement().getId().toString() + " by " + player.getName().getString());
                    PlayerAdvancementDTO dto = new PlayerAdvancementDTO(player, event.getAdvancement());

                    // Convert DTO to JSON
                    Gson gson = new Gson();
                    String json = gson.toJson(dto);
                    HttpRequestHelper.sendPostRequest("/advancements/obtain", json);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}

