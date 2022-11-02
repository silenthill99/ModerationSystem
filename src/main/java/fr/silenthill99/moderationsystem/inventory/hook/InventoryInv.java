package fr.silenthill99.moderationsystem.inventory.hook;

import fr.silenthill99.moderationsystem.inventory.AbstractInventory;
import fr.silenthill99.moderationsystem.inventory.holder.InventoryHolder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

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
}
