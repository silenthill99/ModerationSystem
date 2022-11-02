package fr.silenthill99.moderationsystem.inventory;

import fr.silenthill99.moderationsystem.inventory.hook.InventoryInv;
import fr.silenthill99.moderationsystem.inventory.hook.ReportInventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.Arrays;
import java.util.List;

public enum InventoryType
{
    INVENTORY(new InventoryInv()),
    REPORT(new ReportInventory())
    ;
    private final AbstractInventory<?> inv;

    InventoryType(AbstractInventory<?> inv)
    {
        this.inv = inv;
    }

    public AbstractInventory<?> getInv()
    {
        return this.inv;
    }

    public static List<InventoryType> getValues()
    {
        return Arrays.asList(values());
    }
}
