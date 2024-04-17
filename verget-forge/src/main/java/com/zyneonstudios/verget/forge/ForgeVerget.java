package com.zyneonstudios.verget.forge;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ForgeVerget {

    private static final String metaUrl = "https://files.minecraftforge.net/net/minecraftforge/forge/maven-metadata.json";

    public static ArrayList<String> getVersions() {
        ArrayList<String> versions = new ArrayList<>();
        for(String version:getSupportedMinecraftVersions()) {
            versions.addAll(getVersions(version));
        }
        return versions;
    }

    public static ArrayList<String> getVersions(String minecraftVersion) {
        ArrayList<String> versions = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject json = gson.fromJson(getFromURL(metaUrl),JsonObject.class);
        JsonArray array = json.get(minecraftVersion).getAsJsonArray();
        for(JsonElement element:array) {
            versions.add(element.getAsString().replace("\"",""));
        }
        return versions;
    }

    public static ArrayList<String> getSupportedMinecraftVersions() {
        ArrayList<String> versions = new ArrayList<>();
        String json = getFromURL("https://api.modrinth.com/v2/tag/game_version");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray array = gson.fromJson(json, JsonArray.class);
        String forge = getFromURL(metaUrl);
        JsonObject forgeJson = gson.fromJson(forge, JsonObject.class);
        for(JsonElement element:array) {
            String type = element.getAsJsonObject().get("version_type").getAsString().replace("\"","");
            if(type.equals("snapshot") || type.equals("alpha") || type.equals("beta")) {
                continue;
            }
            String version = element.getAsJsonObject().get("version").getAsString().replace("\"","");
            if(forgeJson.get(version)!=null) {
                versions.add(version);
            }
        }
        return versions;
    }

    private static String getFromURL(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();
            return response.toString();
        } catch (Exception e) {
            return null;
        }
    }
}