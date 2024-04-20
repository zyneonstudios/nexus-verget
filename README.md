# nexus-verget

A library to get all available for Minecraft and some of its modloaders.<br>
Supported modloaders: Fabric, Forge & Quilt<br>
If you want us to add a new modloader, just contact us!

---

There are three Version Objects to store more version info.<br>
FabricVersion: Can store the version number, the build number, the maven string and a boolean if it is stable.<br>
MinecraftVersion: Can store the version number, the versionType, the release date and a boolean if it is a major version.<br>
QuiltVersion: Can store the version number, the build number and the maven string.<br>

Warning: These objects are optional - no need to use them.

---

## For verget-all:
`Verget.getFabricVersions(boolean:showUnstable)`: to get all available Fabric modloader versions.<br>
`Verget.getFabricVersions(boolean:showUnstable, String:minecraftVersion)`: to get all available Fabric modloader versions for a defined Minecraft version.<br>
`Verget.getFabricGameVersions(boolean:showUnstable)`: to get all Fabric-compatible Minecraft versions.

`Verget.getForgeVersions()`: to get all available Forge modloader versions.<br>
`Verget.getForgeVersions(String:minecraftVersion)`: to get all available Forge modloader versions for a specific Minecraft version:<br>
`Verget.getForgeGameVersions()`: to get all Forge-compatible Minecraft versions.

`Verget.getMinecraftVersions(MinecraftVerget.Filter:versionType)`: to get all available Minecraft versions.<br>
`Verget.getMinecraftVersions(MinecraftVerget.Filter:versionType, String:filter)`: to get all available Minecraft versions (with a search filter).

`Verget.getQuiltVersions()`: to get all available Quilt modloader versions.<br>
`Verget.getQuiltVersions(String:minecraftVersion)`: to get all available Quilt modloader versions for a defined Minecraft version.<br>
`Verget.getQuiltGameVersions(boolean:showUnstable)`: to get all Quilt-compatible Minecraft versions.

---

## For verget-fabric:
`FabricVerget.getVersions(boolean:showUnstable)`: to get all available Fabric modloader versions.<br>
`FabricVerget.getVersions(boolean:showUnstable, String:minecraftVersion)`: to get all available Fabric modloader versions for a defined Minecraft version.<br>
`FabricVerget.getSupportedMinecraftVersions(boolean:showUnstable)`: to get all Fabric-compatible Minecraft versions.

---

## For verget-forge:
`ForgeVerget.getVersions()`: to get all available Forge modloader versions.<br>
`ForgeVerget.getVersions(String:minecraftVersion)`: to get all available Forge modloader versions for a specific Minecraft version:<br>
`ForgeVerget.getSupportedMinecraftVersions()`: to get all Forge-compatible Minecraft versions.

---

## For verget-minecraft:
`MinecraftVerget.getVersions(MinecraftVerget.Filter:versionType)`: to get all available Minecraft versions.<br>
`MinecraftVerget.getVersions(MinecraftVerget.Filter:versionType, String:filter)`: to get all available Minecraft versions (with a search filter).

---

## For verget-quilt:
`QuiltVerget.getVersions()`: to get all available Quilt modloader versions.<br>
`QuiltVerget.getVersions(String:minecraftVersion)`: to get all available Quilt modloader versions for a defined Minecraft version.<br>
`QuiltVerget.getSupportedMinecraftVersions(boolean:showUnstable)`: to get all Quilt-compatible Minecraft versions.
