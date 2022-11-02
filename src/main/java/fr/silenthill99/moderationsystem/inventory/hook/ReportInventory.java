package fr.silenthill99.moderationsystem.inventory.hook;

import fr.silenthill99.moderationsystem.ItemBuilder;
import fr.silenthill99.moderationsystem.inventory.AbstractInventory;
import fr.silenthill99.moderationsystem.inventory.holder.ReportHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ReportInventory extends AbstractInventory<ReportHolder>
{
    public ReportInventory()
    {
        super(ReportHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        ReportHolder holder = new ReportHolder(target);

        Inventory inv = createInventory(holder, 18, ChatColor.AQUA + "Report: " + ChatColor.RED + target.getName());
        int slot = 0;
        for (Items items : Items.values())
        {
            holder.items.put(slot, items);
            inv.setItem(slot++, new ItemBuilder(items.getMaterial()).setName(items.getCustomName()).toItemStack());
        }
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, ReportHolder holder) {
        OfflinePlayer target = holder.getTarget();
        Items items = holder.items.get(event.getSlot());
        switch (current.getType())
        {
            case IRON_SWORD:
            case BOW:
            {
                player.closeInventory();
                sendToMods(items.getCustomName(), target);
                player.sendMessage(ChatColor.DARK_RED + "Vous avez bien signalé ce joueur !");
                break;
            }
            default:
            {
                break;
            }
        }
    }

    private void sendToMods(String reason, OfflinePlayer target)
    {
        for (Player players : Bukkit.getOnlinePlayers())
        {
            if (players.hasPermission("mod.receive"))
            {
                players.sendMessage(ChatColor.AQUA + "Le joueur " + ChatColor.GREEN + target.getName() + ChatColor.AQUA + " a été signalé pour : " + reason);
            }
        }
    }

    public enum Items
    {
        FORCEFIELD(Material.IRON_SWORD, ChatColor.RED + "ForceField"),
        SPAMBOW(Material.BOW, ChatColor.RED + "SpamBow");

        private final Material m;
        private final String customName;
        Items(Material m, String customName)
        {
            this.m = m;
            this.customName = customName;
        }

        public Material getMaterial()
        {
            return this.m;
        }

        public String getCustomName()
        {
            return this.customName;
        }
    }
}
