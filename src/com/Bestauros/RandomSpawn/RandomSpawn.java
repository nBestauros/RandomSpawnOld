package com.Bestauros.RandomSpawn;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RandomSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            ItemStack diamond = new ItemStack(Material.DIAMOND,20);
            player.getInventory().addItem(diamond);
            //return true because a player called this
            return true;
        }
        //TODO: Figure out how to make an error when the console calls this.
        return false;
    }
}
