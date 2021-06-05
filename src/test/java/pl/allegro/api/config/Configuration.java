package pl.allegro.api.config;

import org.aeonbits.owner.*;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:api.properties","classpath:api.local.properties"})
public interface Configuration extends Config, Mutable, Accessible {

    @Key("api.uri")
    String apiUri();

    @Key("api.auth.uri")
    String authUri();

    @Key("clientId")
    String clientId();

    @Key("clientSecret")
    String clientSecret();
}
