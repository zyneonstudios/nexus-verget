package com.zyneonstudios.verget.quilt;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class QuiltVersion {

    private final String version;
    private final int build;
    private final String maven;

    public QuiltVersion(String version, int build, String maven, boolean stable) {
        this.version = version;
        this.build = build;
        this.maven = maven;
    }

    @Deprecated
    public QuiltVersion(String version) {
        JsonArray array = new GsonBuilder().setPrettyPrinting().create().fromJson(QuiltVerget.getFromURL("https://meta.quiltmc.org/v3/versions/loader"), JsonArray.class);
        this.version = version;
        int build = -1;
        String maven = null;
        for(JsonElement element:array) {
            JsonObject json = element.getAsJsonObject();
            String version_ = json.get("version").getAsString().replace("\"","");
            if(version_.equals(version)) {
                build = json.get("build").getAsInt();
                maven = json.get("maven").getAsString();
                break;
            }
        }
        this.build = build;
        this.maven = maven;
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

}
