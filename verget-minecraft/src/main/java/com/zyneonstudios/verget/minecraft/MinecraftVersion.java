package com.zyneonstudios.verget.minecraft;

import com.google.gson.*;

public class MinecraftVersion {

    private final String version;
    private final Type versionType;
    private final String date;
    private final boolean isMajor;

    public MinecraftVersion(String version, Type versionType, String date, boolean major) {
        this.version = version;
        this.versionType = versionType;
        this.date = date;
        this.isMajor = major;
    }

    @Deprecated
    public MinecraftVersion(String version) {
        this.version = version;
        String json = MinecraftVerget.getFromURL("https://api.modrinth.com/v2/tag/game_version");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray array = gson.fromJson(json, JsonArray.class);
        Type versionType = null;
        String date = null;
        boolean isMajor = false;
        for(JsonElement element : array) {
            JsonObject object = element.getAsJsonObject();
            if(object.get("version").getAsString().replace("\"","").equals(version)) {
                String type = object.get("version_type").getAsString().replace("\"","").toUpperCase();
                versionType = Type.valueOf(type);
                date = object.get("date").getAsString().replace("\"","");
                isMajor = object.get("major").getAsBoolean();
                break;
            }
        }
        this.versionType = versionType;
        this.date = date;
        this.isMajor = isMajor;
    }

    public String getDate() {
        return date;
    }

    public String getVersion() {
        return version;
    }

    public Type getVersionType() {
        return versionType;
    }

    public boolean isMajor() {
        return isMajor;
    }

    public enum Type {
        RELEASE,
        SNAPSHOT,
        BETA,
        ALPHA
    }
}
