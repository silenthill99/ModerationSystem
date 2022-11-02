package fr.silenthill99.moderationsystem.inventory.holder;

import fr.silenthill99.moderationsystem.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class InventoryHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public InventoryHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }
}
