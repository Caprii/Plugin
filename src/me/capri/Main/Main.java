package me.capri.Main;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    //Stores Frozen players so they cannot logout and log back in to be unfrozen!


    //When the server start this takes effect
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nPlugin has been enabled" +
                ChatColor.AQUA + "(v1.2)\n\n");
        registerEvents();
        registerCommands();
    }

    //When the server stop this takes effect
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "\n\nPlugin has been disabled!\n\n");
    }
    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Events(), this);
    }
    public void registerCommands(){
        getCommand("freeze").setExecutor(new Commands());
    }
}