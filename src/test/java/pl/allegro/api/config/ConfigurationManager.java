package pl.allegro.api.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigCache;

public class ConfigurationManager {

    public ConfigurationManager() {
    }

    public static Configuration getConfiguration(){
        return ConfigCache.getOrCreate(Configuration.class);
    }
}
