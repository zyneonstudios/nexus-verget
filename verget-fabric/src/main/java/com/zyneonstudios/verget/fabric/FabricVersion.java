package com.zyneonstudios.verget.fabric;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FabricVersion {

    private final String version;
    private final int build;
    private final String maven;
    private final boolean stable;

    public FabricVersion(String version, int build, String maven, boolean stable) {
        this.version = version;
        this.build = build;
        this.maven = maven;
        this.stable = stable;
    }

    @Deprecated
    public FabricVersion(String version) {
        JsonArray array = new GsonBuilder().setPrettyPrinting().create().fromJson(FabricVerget.getFromURL("https://meta.fabricmc.net/v2/versions/loader"), JsonArray.class);
        this.version = version;
        int build = -1;
        String maven = null;
        boolean stable = false;
        for(JsonElement element:array) {
            JsonObject json = element.getAsJsonObject();
            String version_ = json.get("version").getAsString().replace("\"","");
            if(version_.equals(version)) {
                build = json.get("build").getAsInt();
                maven = json.get("maven").getAsString();
                stable = json.get("stable").getAsBoolean();
                break;
            }
        }
        this.build = build;
        this.maven = maven;
        this.stable = stable;
    }

    public String getVersion() {
        return version;
    }

    public int getBuild() {
        return build;
    }

    public String getMaven() {
        return maven;
    }

    public boolean isStable() {
        return stable;
    }
}
