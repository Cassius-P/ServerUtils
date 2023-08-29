package com.su.serverutils.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;


public class ApiConfig {

    public static ConfigValue<String> apiURL;


    public ApiConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Database Configuration");

        apiURL = builder
                .comment("API URL")
                .define("api.url", "localhost");


    }

}
