package fr.silenthill99.moderationsystem.events;

import fr.silenthill99.moderationsystem.managers.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ModCancels implements Listener
{
    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event)
    {
        Player player = event.getPlayer();
        event.setCancelled(PlayerManager.isInModerationMod(player));
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event)
    {
        Player player = event.getPlayer();
        event.setCancelled(PlayerManager.isInModerationMod(player));
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        event.setCancelled(PlayerManager.isInModerationMod(player));
    }
    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event)
    {
        Player player = event.getPlayer();
        event.setCancelled(PlayerManager.isInModerationMod(player));
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event)
    {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        event.setCancelled(PlayerManager.isInModerationMod(player));
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        event.setCancelled(PlayerManager.isInModerationMod(player));
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(PlayerManager.isInModerationMod(player));
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
    {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();
        Player target = (Player) event.getEntity();
        ItemStack current = player.getInventory().getItemInMainHand();

        if (PlayerManager.isInModerationMod(player))
        {
            event.setCancelled(current.getType() != Material.STICK);
        }
    }

}
