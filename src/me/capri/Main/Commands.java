package me.capri.Main;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Capri on 5/1/2017.
 */
public class Commands extends JavaPlugin implements CommandExecutor{
    ArrayList<UUID> fp = new ArrayList<UUID>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("Freeze")) {
            if(args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Pass in arguments!");
                return true;
            }

            //defines the target of the freeze command
            Player target = getServer().getPlayer(args[0]);

            //if the target is not on or is specified incorrectly
            if(target == null) {
                sender.sendMessage(ChatColor.RED + "No player on by such name!");
                return true;
            }

            //if the target is already on the fp array, they will be removed and therefor unfrozen
            if (fp.contains(target.getName())){
                fp.remove(target.getName());
                sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.AQUA + target.getName() + ChatColor.GOLD
                        + " Has been unfrozen!");
                target.sendMessage(ChatColor.GREEN + "You have been Unfrozen!");
                return true;
            }
            fp.add(target.getUniqueId());
            sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.AQUA + target.getName() + ChatColor.GOLD +
                    " Has been Frozen!");
        }
        return false;
    }
}
