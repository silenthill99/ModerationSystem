package fr.silenthill99.moderationsystem.commands;

import fr.silenthill99.moderationsystem.ItemBuilder;
import fr.silenthill99.moderationsystem.Main;
import fr.silenthill99.moderationsystem.managers.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Mod implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
    {
        Main main = Main.getInstance();
        if (!(sender instanceof Player))
        {
            System.out.println("Seul un joueur peut éxécuter cette commande !");
            return false;
        }
        Player player = (Player) sender;

        if (PlayerManager.isInModerationMod(player))
        {
            PlayerManager pm = PlayerManager.getFromPlayer(player);

            main.moderateurs.remove(player.getUniqueId());
            player.getInventory().clear();
            player.sendMessage(ChatColor.RED + "Vous n'êtes à présent plus en mode modération");
            pm.giveInventory();
            pm.destroy();
            return false;
        }

        PlayerManager pm = new PlayerManager(player);
        pm.init();

        main.moderateurs.add(player.getUniqueId());
        player.sendMessage(ChatColor.GREEN + "Vous êtes à présent en mode modération !");
        pm.saveInventory();
        player.setAllowFlight(true);
        player.setFlying(true);

        ItemStack invSee = new ItemBuilder(Material.PAPER).setName(ChatColor.YELLOW + "Voir l'inventaire").setLore(ChatColor.GRAY + "Clique droit sur un joueur", ChatColor.GRAY + "pour voir son inventaire").toItemStack();
        ItemStack reports = new ItemBuilder(Material.BOOK).setName(ChatColor.GOLD + "Voir les signalements").setLore(ChatColor.GRAY + "Clique droit sur un joueur", ChatColor.GRAY + "pour voir ses signalements").toItemStack();
        ItemStack freeze = new ItemBuilder(Material.PACKED_ICE).setName(ChatColor.AQUA + "Freeze").setLore(ChatColor.GRAY + "Clique droit sur un joueur", ChatColor.GRAY + "pour le freeze").toItemStack();
        ItemStack kbTester = new ItemBuilder(Material.STICK).setName(ChatColor.LIGHT_PURPLE + "Test de recul").setLore(ChatColor.GRAY + "Clique gauche sur un joueur", ChatColor.GRAY + "pour tester son recul").addUnsafeEnchantment(Enchantment.KNOCKBACK, 5).toItemStack();
        ItemStack kill = new ItemBuilder(Material.BLAZE_ROD).setName(ChatColor.RED + "Tueur de joueur").setLore(ChatColor.GRAY + "Clique droit sur un joueur", ChatColor.GRAY + "pour le tuer").toItemStack();
        ItemStack tpRandom = new ItemBuilder(Material.ARROW).setName(ChatColor.GREEN + "Téléportation aléatoire").setLore(ChatColor.GRAY + "Clique droit spour se téléporter", ChatColor.GRAY + "aléatoirement sur un joueur").toItemStack();
        ItemStack vanish = new ItemBuilder(Material.BLAZE_POWDER).setName(ChatColor.GREEN + "Vanish").setLore(ChatColor.GRAY + "Clique droit pour activer/désactiver", ChatColor.GRAY + "son vanish").toItemStack();

        player.getInventory().setItem(0, invSee);
        player.getInventory().setItem(1, reports);
        player.getInventory().setItem(2, freeze);
        player.getInventory().setItem(3, kbTester);
        player.getInventory().setItem(4, kill);
        player.getInventory().setItem(5, tpRandom);
        player.getInventory().setItem(6, vanish);

        return false;
    }
}
