package pl.allegro.api.util;


import java.util.HashMap;
import java.util.Map;

public class ApiHeadersBuilder {

    public ApiHeadersBuilder() {
    }

    public static ApiHeadersBuilder builder() {
        return new ApiHeadersBuilder();
    }

    public Map<String, String> header(String token) {
        Map<String, String> map = new HashMap<>();
        map.put("accept", "application/vnd.allegro.public.v1+json");
        map.put("Authorization", token);
        return map;
    }
}