package fr.silenthill99.moderationsystem.events;

import fr.silenthill99.moderationsystem.inventory.InventoryManager;
import fr.silenthill99.moderationsystem.inventory.InventoryType;
import fr.silenthill99.moderationsystem.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ModItemsInteract implements Listener
{
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event)
    {
        Player player = event.getPlayer();
        if (!PlayerManager.isInModerationMod(player)) return;
        if (!(event.getRightClicked() instanceof Player)) return;

        Player target = (Player) event.getRightClicked();
        ItemStack current = player.getInventory().getItemInMainHand();

        switch (current.getType())
        {
            /**
             * Voir l'inventaire
             */
            case PAPER:
            {
                InventoryManager.openInventory(player, InventoryType.INVENTORY, target);
                break;
            }
            case BOOK:
            {
                /**
                 * TODO
                 */
            }
            /**
             * Freeze
             */
            case PACKED_ICE:
            {
                /**
                 * TODO
                 */
            }
            /**
             * Tueur de joueur
             */

            case BLAZE_ROD:
            {
                target.damage(target.getHealth());
                break;
            }
            default:
            {
                break;
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        HashMap<Player, Long> timer = new HashMap<>();
        Player player = event.getPlayer();
        long time = System.currentTimeMillis();


        Action action = event.getAction();
        if (!PlayerManager.isInModerationMod(player)) return;
        if (action != Action.RIGHT_CLICK_BLOCK && action != Action.RIGHT_CLICK_AIR) return;
        ItemStack current = player.getInventory().getItemInMainHand();

        switch (current.getType())
        {
            /**
             * La téléportation aléatoire
             */
            case ARROW:
            {
                List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
                list.remove(player);
                if (list.size() == 0)
                {
                    player.sendMessage(ChatColor.RED + "Il n'y a aucun joueur sur lequel vous téléporter.");
                    return;
                }

                Player target = list.get(new Random().nextInt(list.size()));
                player.teleport(target.getLocation());
                player.sendMessage(ChatColor.GREEN + "Vous avez été téléporté à " + ChatColor.YELLOW + target.getName());
                if (time < timer.get(player)) return;
                timer.put(player, System.currentTimeMillis() + 1000);
                break;
            }
            /**
             * Vanish
             */
            case BLAZE_POWDER:
            {
                PlayerManager mod = PlayerManager.getFromPlayer(player);
                mod.setVanished(!mod.isVanished());
                player.sendMessage(mod.isVanished() ? ChatColor.GREEN + "Vous êtes à présent invisible !" : ChatColor.AQUA + "Vous êtes à présent visible !");
                break;
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        for (Player players : Bukkit.getOnlinePlayers())
        {
            if (PlayerManager.isInModerationMod(players))
            {
                PlayerManager pm = PlayerManager.getFromPlayer(players);
                if (pm.isVanished())
                {
                    players.hidePlayer(player);
                }
            }
        }
    }

}
