package com.codekova.loreplacer.commands;

import com.codekova.loreplacer.LoReplacer;
import com.codekova.loreplacer.resources.HexFormatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.List;

public class Loreplace_reline implements CommandExecutor {

    LoReplacer loreplacer = LoReplacer.getInstance();
    private FileConfiguration msgConfig = loreplacer.loadCustomConfig("messages.yml", new File(loreplacer.getDataFolder(),"messages.yml"));



    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args[0].equalsIgnoreCase("reline"))
        {
            if(args.length == 1){
                sender.sendMessage(HexFormatter.format("&9[LoReplacer] &cUsage /loreplace reline <Player> <Search (Use @ As BlankSpace)> <Replacement>"));
                return true;
            }
            if(args.length > 1 && args.length < 4){
                sender.sendMessage(HexFormatter.format("&9[LoReplacer] &cUsage /loreplace reline <Player> <Search (Use @ As BlankSpace)> <Replacement>"));
                return true;
            }



            if(!(sender instanceof Player)){

                if(Bukkit.getPlayer(args[1]) == null)
                {
                    sender.sendMessage(HexFormatter.format("&9[LoReplacer] &cPlayer "+args[1]+" not found"));
                    return true;
                }
                Player targetPlayer = Bukkit.getPlayer(args[1]);
                ItemStack item = targetPlayer.getInventory().getItemInMainHand();
                ItemMeta itemMeta = item.getItemMeta();
                List<String> itemLore = itemMeta.getLore();
                String Searchline = ChatColor.stripColor(HexFormatter.format(args[2].replaceAll("@"," ")));
                StringBuilder replaceLineBuilder = new StringBuilder();
                for(int i = 3; i < args.length; i++)
                {
                    replaceLineBuilder.append(args[i]);
                    replaceLineBuilder.append(" ");
                }
                String replaceLine = HexFormatter.format(replaceLineBuilder.toString());
                if(itemLore == null)
                {
                    itemLore = List.of(replaceLine);
                    itemMeta.setLore(itemLore);
                    item.setItemMeta(itemMeta);
                    targetPlayer.getInventory().setItemInMainHand(item);
                    return true;
                }
                for(int i = 0;i < itemLore.size(); i++)
                {
                    if(ChatColor.stripColor(itemLore.get(i)).toLowerCase().contains(Searchline.toLowerCase()))
                    {
                        itemLore.set(i,replaceLine);
                        break;
                    }
                    else if(i == itemLore.size()-1)
                    {
                        itemLore.add(replaceLine);
                        break;
                    }

                }
                itemMeta.setLore(itemLore);
                item.setItemMeta(itemMeta);
                targetPlayer.getInventory().setItemInMainHand(item);
                return true;
            }

            Player player = (Player) sender;
            if(!player.isOp()){
                player.sendMessage(HexFormatter.format(msgConfig.getString("Messages.NoPermission")));
                return true;
            }
            if(Bukkit.getPlayer(args[1]) == null){
                player.sendMessage(HexFormatter.format("&9[LoReplacer] &c Player "+args[1]+" not found"));
                return true;
            }
            Player targetPlayer = Bukkit.getPlayer(args[1]);
            ItemStack item = targetPlayer.getInventory().getItemInMainHand();
            ItemMeta itemMeta = item.getItemMeta();
            List<String> itemLore = itemMeta.getLore();
            String Searchline = ChatColor.stripColor(HexFormatter.format(args[2].replaceAll("@"," ")));
            StringBuilder replaceLineBuilder = new StringBuilder();
            for(int i = 3; i < args.length; i++)
            {
                replaceLineBuilder.append(args[i]);
                replaceLineBuilder.append(" ");
            }
            String replaceLine = HexFormatter.format(replaceLineBuilder.toString());
            if(itemLore == null)
            {
                itemLore = List.of(replaceLine);
                itemMeta.setLore(itemLore);
                item.setItemMeta(itemMeta);
                targetPlayer.getInventory().setItemInMainHand(item);
                return true;
            }
            for(int i = 0;i < itemLore.size(); i++)
            {
                if(ChatColor.stripColor(itemLore.get(i)).toLowerCase().contains(Searchline.toLowerCase()))
                    {
                        itemLore.set(i,replaceLine);
                        break;
                    }
                    else if(i == itemLore.size()-1)
                    {
                        itemLore.add(replaceLine);
                        break;
                    }

            }
            itemMeta.setLore(itemLore);
            item.setItemMeta(itemMeta);
            targetPlayer.getInventory().setItemInMainHand(item);
            return true;
        }



        return true;
    }
}
