package com.Bestauros.RandomSpawn;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        FileConfiguration config = this.getConfig();
        //These ranges are negative through positive range, ie:
        //X Range of 750 means the possible spawn can be between -750 and 750
        config.addDefault("X Range:", 750);
        config.addDefault("Z Range:", 750);
        config.options().copyDefaults(true);
        saveConfig();

        try{
            int xRange = config.getInt("X Range:");
            int zRange = config.getInt("Z Range:");
            this.getCommand("RandomSpawn").setExecutor(new RandomSpawn(this));
            getServer().getPluginManager().registerEvents(new ReSpawnListener(xRange, zRange), this);
            getServer().getPluginManager().registerEvents(new NewSpawnListener(xRange, zRange), this);

        }
        catch(NullPointerException e){
            getLogger().info("Error: "+e);
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable(){

    }
}
