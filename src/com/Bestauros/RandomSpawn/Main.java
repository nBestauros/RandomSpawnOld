package com.Bestauros.RandomSpawn;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        try{
            this.getCommand("RandomSpawn").setExecutor(new RandomSpawn());

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
