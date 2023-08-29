package com.su.serverutils.dto;

import com.google.gson.Gson;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.Criterion;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;


public class AdvancementDTO {
    private String id;
    private String title;
    private String description;
    private String icon; // Assuming base64 encoded image
    private String parent;
    private String modId; // If you want to include the mod ID

    private String image_base64;

    public AdvancementDTO(String id, String displayName, String description, String icon, String parent, String modId) {
        this.id = id;
        this.title = displayName;
        this.description = description;
        this.icon = icon;
        this.parent = parent;
        this.modId = modId;
    }

    public AdvancementDTO(Advancement advancement){
        this.id = advancement.getId().toString();

        if(advancement.getDisplay() != null){
            this.title = advancement.getDisplay().getTitle().getString();
            this.description = advancement.getDisplay().getDescription().getString();

            if(advancement.getDisplay().getIcon() != null){
                ResourceLocation location = ForgeRegistries.ITEMS.getKey(advancement.getDisplay().getIcon().getItem());
                if (location != null){
                    String pathToImage = location.getNamespace() + ":" + location.getPath();
                    this.icon = pathToImage;
                }
            }
        }

        this.parent = advancement.getParent() != null ? advancement.getParent().getId().toString() : null;
        this.modId = advancement.getId().getNamespace();
    }

    public AdvancementDTO() {
    }

    public AdvancementDTO(String s) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public String getImage_base64() {
        return image_base64;
    }
    public void setImage_base64(String image_base64) {
        this.image_base64 = image_base64;
    }

}
