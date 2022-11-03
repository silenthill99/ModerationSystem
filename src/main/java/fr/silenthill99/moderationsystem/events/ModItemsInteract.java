package fr.silenthill99.moderationsystem.events;

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
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack current = player.getInventory().getItemInMainHand();
        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) return;

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
