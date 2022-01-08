package com.codekova.loreplacer.commands;

import com.codekova.loreplacer.LoReplacer;
import com.codekova.loreplacer.resources.HexFormatter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;

public class Loreplace_rename implements CommandExecutor {

    LoReplacer loreplacer = LoReplacer.getInstance();
    private FileConfiguration msgConfig = loreplacer.loadCustomConfig("messages.yml", new File(loreplacer.getDataFolder(),"messages.yml"));


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args[0].equalsIgnoreCase("rename"))
        {

            if(args.length == 1){
                sender.sendMessage(HexFormatter.format("&9[LoReplacer] &cUsage /loreplace rename <Player> <Item new Name>"));
                return true;
            }

            if(args.length > 1 && args.length < 3){
                sender.sendMessage(HexFormatter.format("&9[LoReplacer] &cUsage /loreplace rename <Player> <Item new Name>"));
                return true;
            }

            if(Bukkit.getPlayer(args[1]) == null)
            {
                sender.sendMessage(HexFormatter.format("&9[LoReplacer] &cPlayer "+args[1]+" not found"));
                return true;
            }
            StringBuilder nameBuilder = new StringBuilder();
            for(int i = 2; i < args.length; i++)
            {
                nameBuilder.append(args[i]);
                nameBuilder.append(" ");
            }
            String newName = HexFormatter.format(nameBuilder.toString());
            Player targetPlayer = Bukkit.getPlayer(args[1]);
            ItemStack item = targetPlayer.getInventory().getItemInMainHand();
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(newName);
            item.setItemMeta(itemMeta);
            targetPlayer.getInventory().setItemInMainHand(item);
            return true;

        }

        return true;
    }
}
