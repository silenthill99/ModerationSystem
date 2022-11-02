package fr.silenthill99.moderationsystem.managers;

import fr.silenthill99.moderationsystem.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerManager
{
    static Main main = Main.getInstance();
    private final Player player;
    private boolean vanished;
    private ItemStack[] items = new ItemStack[40];

    public PlayerManager(Player player)
    {
        this.player = player;
        vanished = false;
    }

    public void init()
    {
        main.players.put(player.getUniqueId(), this);
    }

    public void destroy()
    {
        main.players.remove(player.getUniqueId());
    }

    public static PlayerManager getFromPlayer(Player player)
    {
        return main.players.get(player.getUniqueId());
    }

    public ItemStack[] getItems()
    {
        return this.items;
    }

    public void saveInventory()
    {
        for (int slot = 0; slot < 36; slot++)
        {
            ItemStack item = player.getInventory().getItem(slot);
            if (item != null)
            {
                items[slot] = item;
            }
        }

        items[36] = player.getInventory().getHelmet();
        items[37] = player.getInventory().getChestplate();
        items[38] = player.getInventory().getLeggings();
        items[39] = player.getInventory().getBoots();

        player.getInventory().clear();
    }

    public void giveInventory()
    {
        player.getInventory().clear();
        for (int slot = 0; slot < 36; slot++)
        {
            ItemStack item = items[slot];
            if (item != null)
            {
                player.getInventory().setItem(slot, item);
            }
        }
        player.getInventory().setHelmet(items[36]);
        player.getInventory().setChestplate(items[37]);
        player.getInventory().setLeggings(items[38]);
        player.getInventory().setBoots(items[39]);
    }

    public boolean isVanished()
    {
        return this.vanished;
    }

    public void setVanished(boolean vanished)
    {
        this.vanished = vanished;
        if(vanished)
        {
            Bukkit.getOnlinePlayers().forEach(players -> players.hidePlayer(player));
        }
        else
        {
            Bukkit.getOnlinePlayers().forEach(players -> players.showPlayer(player));
        }
    }
    public static boolean isInModerationMod(Player player)
    {
        return main.moderateurs.contains(player.getUniqueId());
    }
}
