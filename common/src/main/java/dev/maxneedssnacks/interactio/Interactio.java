package dev.maxneedssnacks.interactio;

import dev.maxneedssnacks.interactio.event.ExplosionHandler;
import dev.maxneedssnacks.interactio.proxy.ModProxy;
import dev.maxneedssnacks.interactio.proxy.Proxy;
import me.shedaniel.architectury.event.events.ExplosionEvent;
import me.shedaniel.architectury.registry.Registries;
import me.shedaniel.architectury.utils.EnvExecutor;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class Interactio {

    public static final String MOD_ID = "interactio";

    public static final Registries REGISTRIES = Registries.get(MOD_ID);

    public static final Logger LOGGER = LogManager.getLogger();

    public static Interactio INSTANCE;
    public static Proxy PROXY;

    public static UUID CHAT_ID = UUID.randomUUID();

    private final String PROTOCOL_VERSION = "1";

    public Interactio() {

        // static base variables
        INSTANCE = this;
        PROXY = EnvExecutor.getEnvSpecific(() -> ModProxy.Client::new, () -> ModProxy.Server::new);


        // static event handlers
        ExplosionEvent.DETONATE.register(ExplosionHandler::boom);

        // non-static event handlers

    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

}
