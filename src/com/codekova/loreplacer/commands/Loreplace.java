package com.codekova.loreplacer.commands;


import com.codekova.loreplacer.LoReplacer;
import com.codekova.loreplacer.resources.HexFormatter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Loreplace implements CommandExecutor {

    LoReplacer loreplacer = LoReplacer.getInstance();
    private FileConfiguration msgConfig = loreplacer.loadCustomConfig("messages.yml", new File(loreplacer.getDataFolder(),"messages.yml"));

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase("loreplace")){

            if(args.length == 0 || args[0].equalsIgnoreCase("help")) //loreplace base command for Players
            {
                if(!(sender instanceof Player))
                {
                    sender.sendMessage(HexFormatter.format("&9[LoReplacer]&a Command List"));
                    sender.sendMessage(HexFormatter.format("&9[LoReplacer]&a /lr reline"));
                    sender.sendMessage(HexFormatter.format("&9[LoReplacer]&a /lr rename"));
                    sender.sendMessage(HexFormatter.format("&9[LoReplacer]&a LoReplacer by CodeKova Release v1.0"));

                    return true;
                }
                Player player = (Player) sender;
                if (!player.isOp()){
                    player.sendMessage(HexFormatter.format(msgConfig.getString("Messages.NoPermission")));
                    return true;
                }

                player.sendMessage(HexFormatter.format("&9[LoReplacer]&a Command List"));
                player.sendMessage(HexFormatter.format("&9[LoReplacer]&a /lr reline"));
                player.sendMessage(HexFormatter.format("&9[LoReplacer]&a /lr rename"));
                player.sendMessage(HexFormatter.format("&9[LoReplacer]&a LoReplacer by CodeKova Release v1.0"));
                return true;
            }else if(args[0].equalsIgnoreCase("reline")){
                Loreplace_reline reline = new Loreplace_reline();
                reline.onCommand(sender, command, s, args);
                return true;
            }else if(args[0].equalsIgnoreCase("rename"))
            {
                Loreplace_rename rename = new Loreplace_rename();
                rename.onCommand(sender,command,s,args);
                return true;
            }else
            {
                sender.sendMessage(HexFormatter.format("&9[LoReplacer]&c Unknown Command"));
                return true;
            }


        }
        return true;
    }
}
