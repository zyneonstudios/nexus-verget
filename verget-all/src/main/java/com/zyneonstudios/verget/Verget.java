package com.zyneonstudios.verget;

import com.zyneonstudios.verget.fabric.FabricVerget;
import com.zyneonstudios.verget.forge.ForgeVerget;
import com.zyneonstudios.verget.minecraft.MinecraftVerget;
import com.zyneonstudios.verget.quilt.QuiltVerget;

import java.util.ArrayList;

public class Verget {

    public static ArrayList<String> getFabricVersions(boolean showUnstable) {
        return FabricVerget.getVersions(showUnstable);
    }

    public static ArrayList<String> getFabricVersions(boolean showUnstable, String minecraftVersion) {
        return FabricVerget.getVersions(showUnstable,minecraftVersion);
    }

    public static ArrayList<String> getFabricGameVersions(boolean showUnstable) {
        return FabricVerget.getSupportedMinecraftVersions(showUnstable);
    }

    public static ArrayList<String> getForgeVersions() {
        return ForgeVerget.getVersions();
    }

    public static ArrayList<String> getForgeVersions(String minecraftVersion) {
        return ForgeVerget.getVersions(minecraftVersion);
    }

    public static ArrayList<String> getForgeGameVersions() {
        return ForgeVerget.getSupportedMinecraftVersions();
    }

    public static ArrayList<String> getMinecraftVersions(boolean showExperimental) {
        return MinecraftVerget.getVersions(showExperimental);
    }

    public static ArrayList<String> getMinecraftVersions(boolean showExperimental, String filter) {
        return MinecraftVerget.getVersions(showExperimental, filter);
    }

    public static ArrayList<String> getQuiltVersions() {
        return QuiltVerget.getVersions();
    }

    public static ArrayList<String> getQuiltVersions(String minecraftVersion) {
        return QuiltVerget.getVersions(minecraftVersion);
    }

    public static ArrayList<String> getQuiltGameVersions(boolean showUnstable) {
        return QuiltVerget.getSupportedMinecraftVersions(showUnstable);
    }
}