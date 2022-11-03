package fr.silenthill99.moderationsystem.inventory.hook;

import fr.silenthill99.moderationsystem.inventory.AbstractInventory;
import fr.silenthill99.moderationsystem.inventory.InventoryManager;
import fr.silenthill99.moderationsystem.inventory.InventoryType;
import fr.silenthill99.moderationsystem.inventory.holder.InventoryHolder;
import fr.silenthill99.moderationsystem.managers.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryInv extends AbstractInventory<InventoryHolder>
{
    public InventoryInv() {
        super(InventoryHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        Player target = (Player) args[0];
        InventoryHolder holder = new InventoryHolder(target);

        Inventory inv = createInventory(holder, 45, target.getName() + " > Inventaire");
        for (int slot = 0; slot < 36; slot++)
        {
            if (target.getInventory().getItem(slot) != null)
            {
                inv.setItem(slot, target.getInventory().getItem(slot));
            }
        }
        inv.setItem(36, target.getInventory().getHelmet());
        inv.setItem(37, target.getInventory().getChestplate());
        inv.setItem(38, target.getInventory().getLeggings());
        inv.setItem(39, target.getInventory().getBoots());

        player.openInventory(inv);
    }

    @Override
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        if (!(event.getRightClicked() instanceof Player)) return;
        if (!PlayerManager.isInModerationMod(player)) return;

        Player target = (Player) event.getRightClicked();
        ItemStack current = event.getPlayer().getInventory().getItemInMainHand();

        if (current.getType().equals(Material.PAPER))
        {
            InventoryManager.openInventory(player, InventoryType.INVENTORY, target);
        }
    }
}
