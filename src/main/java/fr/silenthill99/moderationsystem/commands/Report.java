package fr.silenthill99.moderationsystem.commands;

import fr.silenthill99.moderationsystem.inventory.InventoryManager;
import fr.silenthill99.moderationsystem.inventory.InventoryType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Report implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
    {
        if (!(sender instanceof Player))
        {
            System.out.println("Seul un joueur peut éxécuter cette commanhde !");
            return false;
        }

        Player player = (Player) sender;
        if (args.length != 1)
        {
            player.sendMessage(ChatColor.RED + "Veuilleza saisir le pseudo d'un joueur");
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        if (!target.isOnline())
        {
            player.sendMessage(ChatColor.RED + "Ce joueur n'est pas connecté ou n'existe pas !");
            return false;
        }

        InventoryManager.openInventory(player, InventoryType.REPORT, target);

        return false;
    }
}
