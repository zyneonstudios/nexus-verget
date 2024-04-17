package com.zyneonstudios.verget.fabric;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FabricVerget {

    public static ArrayList<String> getVersions(boolean showUnstable) {
        return getFabricVersions(showUnstable, "https://meta.fabricmc.net/v2/versions/loader");
    }

    public static ArrayList<String> getVersions(boolean showUnstable, String minecraftVersion) {
        JsonArray versions = new Gson().fromJson(getFromURL("https://meta.fabricmc.net/v2/versions/loader/"+minecraftVersion),JsonArray.class);
        ArrayList<String> list = new ArrayList<>();
        for(JsonElement element:versions) {
            JsonObject json = element.getAsJsonObject().get("loader").getAsJsonObject();
            if(json.get("stable").getAsBoolean()) {
                list.add(json.get("version").getAsString());
            } else {
                if(showUnstable) {
                    list.add(json.get("version").getAsString());
                }
            }
        }
        return list;
    }

    public static ArrayList<String> getSupportedMinecraftVersions(boolean showUnstable) {
        return getFabricVersions(showUnstable, "https://meta.fabricmc.net/v2/versions/game");
    }

    private static ArrayList<String> getFabricVersions(boolean showUnstable, String url) {
        JsonArray versions = new Gson().fromJson(getFromURL(url),JsonArray.class);
        ArrayList<String> list = new ArrayList<>();
        for(JsonElement element:versions) {
            JsonObject json = element.getAsJsonObject();
            if(json.get("stable").getAsBoolean()) {
                list.add(json.get("version").getAsString());
            } else {
                if(showUnstable) {
                    list.add(json.get("version").getAsString());
                }
            }
        }
        return list;
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
