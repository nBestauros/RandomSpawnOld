package com.Bestauros.RandomSpawn;

import net.minecraft.server.v1_14_R1.BlockAir;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReSpawnListener implements Listener {

    World world;
    @EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerRespawn(PlayerRespawnEvent pre){
        try{
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
                genResPoint(pre, forbiddenBiomeList);
            }
            else if(pre.getPlayer().getBedSpawnLocation() == null){
                genResPoint(pre, forbiddenBiomeList);
            }
        }
        catch(Exception e){
            System.out.println("We Crashed! "+e);
            e.printStackTrace();

        }
    }

    private void genResPoint(PlayerRespawnEvent pre, ArrayList<Biome> forbiddenBiomeList) {
        double x;
        double y;
        double z;

        Random random = new Random();


        //int limiter = 40; //lets the game try for 40 ticks
        //int counter = 0;

        boolean isForbidden = true;
        do {
            //counter++;
            //random num up to 1500 times random either -1 or 1
            //TODO: take a range from config file
            x = random.nextInt(750) * (random .nextBoolean() ? -1 : 1);
            z = random.nextInt(750) * (random .nextBoolean() ? -1 : 1);
            Chunk chunk = new Location(world,x,100,z).getChunk();
            chunk.load(true);
            //Biome temp = world.getBiome((int) x, (int) z);
            //for(int i = 0; i <forbiddenBiomeList.size(); i++){
                //if(temp.equals(forbiddenBiomeList.get(i))){
                    //isForbidden=true;
                //}
            //}
            Material temp = world.getHighestBlockAt((int) x, (int) z).getType();
            Material below = world.getBlockAt((int)x,world.getHighestBlockYAt((int) x, (int) z)-1, (int)z).getType();
            if(temp.equals(Material.AIR)&&!below.equals(Material.WATER)){
                isForbidden=false;
            }
        }while(isForbidden);
        y = world.getHighestBlockYAt((int) x, (int) z);
        pre.setRespawnLocation(new Location(world,x,y,z));
        //Bukkit.broadcastMessage(x+" "+ y+" "+ " "+z);
    }

}
