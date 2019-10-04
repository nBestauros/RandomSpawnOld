package com.Bestauros.RandomSpawn;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.configuration.file.FileConfiguration;

public class RandomSpawn implements CommandExecutor {
    Main plugin;
    public RandomSpawn(Main instance){
        plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(player.isOp()){
                plugin.reloadConfig();
                commandSender.sendMessage(ChatColor.GREEN+"RandomSpawn config reloaded.");
            }
            else{
                commandSender.sendMessage(ChatColor.RED+"You do not have permission to reload the RandomSpawn config.");
                return false;
            }
            //return true because an op player called this
            return true;
        }
        //Console calls reload, can always reload
        plugin.reloadConfig();
        commandSender.sendMessage(ChatColor.GREEN+"RandomSpawn config reloaded.");
        return true;
    }
}
