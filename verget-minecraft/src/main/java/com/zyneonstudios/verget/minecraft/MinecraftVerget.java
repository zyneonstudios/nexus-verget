package com.zyneonstudios.verget.minecraft;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MinecraftVerget {

    public static ArrayList<String> getVersions(boolean experimentalVersions) {
        ArrayList<String> versions = new ArrayList<>();
        String json = getFromURL("https://api.modrinth.com/v2/tag/game_version");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray array = gson.fromJson(json, JsonArray.class);
        for(JsonElement element:array) {
            String type = element.getAsJsonObject().get("version_type").getAsString().replace("\"","");
            if(type.equals("snapshot") || type.equals("alpha") || type.equals("beta")) {
                if(!experimentalVersions) {
                    continue;
                }
            }
            versions.add(element.getAsJsonObject().get("version").getAsString().replace("\"",""));
        }
        return versions;
    }

    public static ArrayList<String> getVersions(boolean experimentalVersions, String filter) {
        ArrayList<String> versions = new ArrayList<>();
        String json = getFromURL("https://api.modrinth.com/v2/tag/game_version");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray array = gson.fromJson(json, JsonArray.class);
        for(JsonElement element:array) {
            String version = element.getAsJsonObject().get("version").getAsString().replace("\"","");
            String type = element.getAsJsonObject().get("version_type").getAsString().replace("\"","");
            if(type.equals("snapshot") || type.equals("alpha") || type.equals("beta")) {
                if(!experimentalVersions) {
                    continue;
                }
            }
            if(version.contains(filter)) {
                versions.add(version);
            }
        }
        return versions;
    }

    public static String getFromURL(String urlString) {
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