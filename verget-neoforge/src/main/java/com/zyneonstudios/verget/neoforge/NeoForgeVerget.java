package com.zyneonstudios.verget.neoforge;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class NeoForgeVerget {

    public static void main(String[] args) {
        System.out.println(getSupportedMinecraftVersions());
        System.out.println(getVersions());
        System.out.println(getVersions("1.20.4"));
    }

    private static final String metaUrl = "https://maven.neoforged.net/api/maven/versions/releases/net/neoforged/neoforge";

    public static ArrayList<String> getVersions() {
        ArrayList<String> versions = new ArrayList<>();
        for(String version:getSupportedMinecraftVersions()) {
            versions.addAll(getVersions(version));
        }
        return versions;
    }

    public static ArrayList<String> getVersions(String minecraftVersion) {
        minecraftVersion = minecraftVersion.replace("1.", "");
        ArrayList<String> versions = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray array = gson.fromJson(getFromURL(metaUrl),JsonObject.class).get("versions").getAsJsonArray();
        for(JsonElement element:array) {
            String version = element.getAsString();
            if(version.startsWith(minecraftVersion)) {
                versions.add(element.getAsString().replace("\"", ""));
            }
        }
        Collections.reverse(versions);
        return versions;
    }

    public static ArrayList<String> getSupportedMinecraftVersions() {
        ArrayList<String> versions = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray array = gson.fromJson(getFromURL(metaUrl),JsonObject.class).get("versions").getAsJsonArray();
        for(JsonElement element:array) {
            String[] version = element.getAsString().split("\\.",3);
            String minecraftVersion = "1."+version[0]+"."+version[1];
            if(!versions.contains(minecraftVersion)) {
                versions.add(minecraftVersion);
            }
        }
        Collections.reverse(versions);
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