package com.Bestauros.RandomSpawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReSpawnListener implements Listener {
    World world;
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(PlayerRespawnEvent pre){

        ArrayList<Biome> forbiddenBiomeList = new ArrayList<Biome>();
        //TODO: Dont hardcode these, add them to the config file
        forbiddenBiomeList.add(Biome.OCEAN);
        forbiddenBiomeList.add(Biome.COLD_OCEAN);
        forbiddenBiomeList.add(Biome.DEEP_COLD_OCEAN);
        forbiddenBiomeList.add(Biome.DEEP_FROZEN_OCEAN);
        forbiddenBiomeList.add(Biome.DEEP_LUKEWARM_OCEAN);
        forbiddenBiomeList.add(Biome.DEEP_OCEAN);
        forbiddenBiomeList.add(Biome.DEEP_WARM_OCEAN);
        forbiddenBiomeList.add(Biome.FROZEN_OCEAN);
        forbiddenBiomeList.add(Biome.LUKEWARM_OCEAN);
        forbiddenBiomeList.add(Biome.WARM_OCEAN);

        List<World> worldList = Bukkit.getWorlds();
        //TODO: take user input for world name
        for(World w : worldList){
            if(w.getName().equals("world")){
                world = w;
            }
        }

        //if we aren't respawning to a bed, set the respawn to random
        if(!pre.isBedSpawn()){
            double x;
            double y;
            double z;

            Random random = new Random();

            boolean isForbidden = false;
            do {
                //random num up to 1500 times random either -1 or 1
                //TODO: take a range from config file
                x = random.nextInt(1500) * (random .nextBoolean() ? -1 : 1);
                z = random.nextInt(1500) * (random .nextBoolean() ? -1 : 1);
                Biome temp = world.getBiome((int) x, (int) z);
                for(int i = 0; i <forbiddenBiomeList.size(); i++){

                    if(temp.equals(forbiddenBiomeList.get(i))){
                        isForbidden=true;
                    }
                }
            }while(isForbidden);
            y = world.getHighestBlockYAt((int) x, (int) z);
            pre.setRespawnLocation(new Location(world,x,y,z));
            //Bukkit.broadcastMessage(x+" "+ y+" "+ " "+z);
        }
    }

}
