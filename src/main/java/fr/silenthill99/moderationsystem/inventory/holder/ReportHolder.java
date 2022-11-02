package fr.silenthill99.moderationsystem.inventory.holder;

import fr.silenthill99.moderationsystem.inventory.SilenthillHolder;
import fr.silenthill99.moderationsystem.inventory.hook.ReportInventory.*;
import org.bukkit.OfflinePlayer;

import javax.naming.ldap.HasControls;
import java.util.HashMap;

public class ReportHolder extends SilenthillHolder
{
    private OfflinePlayer target;

    public ReportHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }

    public HashMap<Integer, Items> items = new HashMap<>();
}
