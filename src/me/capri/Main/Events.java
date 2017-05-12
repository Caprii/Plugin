package me.capri.Main;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Capri on 5/1/2017.
 */
public class Events extends Commands implements Listener{
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        String freezeMessage = "&f████&c█&f████\n" +
                "&f███&c█&6█&c█&f███\n" +
                "&f██&c█&6█&0█&6█&c█&f██&c&l Do NOT Logout!\n" +
                "&f██&c█&6█&0█&6█&c█&f██&e If you logout you WILL be banned!\n" +
                "&f█&c█&6██&0█&6██&c█&f█ Please download TeamSpeak and connect to:\n" +
                "&f█&c█&6█████&c█&f█&b" + getConfig().getString("teamspeak ip") + "\n" +
                "&c█&6███&0█&6███&c█\n" +
                "&c█████████";

        Player p = e.getPlayer();
        if (fp.contains(p.getName())) {
            e.setTo(e.getFrom());
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(new Main(), new Runnable() {
                @Override
                public void run() {
                    p.sendMessage("");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', freezeMessage));
                    p.sendMessage("");
                }
            },0, 100);
        }
    }
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if(fp.contains(p.getName())){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(fp.contains(p.getName())){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban" + p.getName());
        }
    }


    public boolean isFrozen(UUID uuid) {
        if(fp.contains(uuid)){
            return true;
        }
        return false;
    }

    public void freeze(UUID uuid) {
        if(isFrozen(uuid)){
            return;
        }
        fp.add(uuid);
    }

    public void unfreeze(UUID uuid) {
        if(!isFrozen(uuid)) return;
        fp.remove(uuid);
    }
}
