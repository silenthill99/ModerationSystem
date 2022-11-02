package fr.silenthill99.moderationsystem;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class ItemBuilder
{
    private final ItemStack is;

    public ItemBuilder(ItemStack is)
    {
        this.is = is;
    }

    public ItemBuilder(Material m, int amount)
    {
        is = new ItemStack(m, amount);
    }
    public ItemBuilder(Material m)
    {
        this(m, 1);
    }

    public ItemBuilder clone()
    {
        return new ItemBuilder(is);
    }

    public ItemBuilder setName(String name)
    {
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLore(String... lore)
    {
        ItemMeta im = is.getItemMeta();
        im.setLore(Arrays.asList(lore));
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner)
    {
        try
        {
            SkullMeta im = (SkullMeta) is.getItemMeta();
            im.setOwner(owner);
            is.setItemMeta(im);
        }
        catch (ClassCastException e)
        {
            e.printStackTrace();
        }
        return this;
    }

    public ItemBuilder setLeatherArmorColor(Color color)
    {
        try
        {
            LeatherArmorMeta im = (LeatherArmorMeta) is.getItemMeta();
            im.setColor(color);
            is.setItemMeta(im);
        }
        catch(ClassCastException e)
        {
            e.printStackTrace();
        }
        return this;
    }

    public ItemBuilder addGlowingEffect()
    {
        ItemMeta im = is.getItemMeta();
        im.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level)
    {
        is.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment ench)
    {
        is.removeEnchantment(ench);
        return this;
    }

    public ItemStack toItemStack()
    {
        return is;
    }
}
