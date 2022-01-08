package com.codekova.loreplacer;

import com.codekova.loreplacer.commands.Loreplace;
import com.codekova.loreplacer.resources.HexFormatter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoReplacer extends JavaPlugin {

        private static LoReplacer instance;

        private FileConfiguration msgConfig;

        public void onEnable(){

            instance = this;
            msgConfig = loadCustomConfig("messages.yml", new File(getDataFolder(),"messages.yml"));
            getCommand("loreplace").setExecutor(new Loreplace());
            getServer().getConsoleSender().sendMessage(HexFormatter.format("&a[LoReplace] Plugin Enabled"));
        }

        public FileConfiguration loadCustomConfig(String resourceName, File out){
            try{
                InputStream in = getResource(resourceName);

                if(!out.exists()){

                    getDataFolder().mkdir();
                    out.createNewFile();
                }

                FileConfiguration file = YamlConfiguration.loadConfiguration(out);
                if (in!=null){
                    InputStreamReader inReader = new InputStreamReader(in);
                    file.setDefaults(YamlConfiguration.loadConfiguration(inReader));
                    file.options().copyDefaults(true);
                    file.save(out);

                }
                return file;

            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }



        }


        @Override
        public void reloadConfig(){
            super.reloadConfig();
            msgConfig = loadCustomConfig("messages.yml", new File(getDataFolder(),"messages.yml"));

        }

        public static LoReplacer getInstance(){
            return instance;
        }

        @Override
        public void onDisable(){

            getServer().getConsoleSender().sendMessage(HexFormatter.format("&a[LoReplace] Plugin Enabled"));
        }





}
