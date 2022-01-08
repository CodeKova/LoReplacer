package com.codekova.loreplacer.resources;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexFormatter {
    public static String format(String msg){
        final Pattern hexpattern = Pattern.compile("&#[a-fA-f0-9]{6}");
        Matcher match = hexpattern.matcher(msg);
        while(match.find()){
            String color = msg.substring(match.start(),match.end());
            msg = msg.replace(color, net.md_5.bungee.api.ChatColor.of(color.replace("&","")) + "");
            match = hexpattern.matcher(msg);
        }
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&',msg);
    }
}
