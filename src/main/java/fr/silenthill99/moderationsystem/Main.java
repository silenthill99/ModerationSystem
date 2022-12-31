package fr.silenthill99.moderationsystem;

import fr.silenthill99.moderationsystem.commands.Mod;
import fr.silenthill99.moderationsystem.commands.Report;
import fr.silenthill99.moderationsystem.events.ModCancels;
import fr.silenthill99.moderationsystem.events.ModItemsInteract;
import fr.silenthill99.moderationsystem.inventory.InventoryManager;
import fr.silenthill99.moderationsystem.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin
{

    private static Main instance;

    public static Main getInstance()
    {
        return instance;
    }
    @Override
    public void onEnable()
    {
        saveDefaultConfig();
        instance = this;
        getLogger().info("Le plugin est opérationnel !");
        registers();
        commands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ArrayList<UUID> moderateurs = new ArrayList<>();
    public HashMap<UUID, PlayerManager> players = new HashMap<>();

    private void commands()
    {
        getCommand("mod").setExecutor(new Mod());
        getCommand("report").setExecutor(new Report());
    }

    public void registers()
    {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryManager(), this);
        pm.registerEvents(new ModCancels(), this);
        pm.registerEvents(new ModItemsInteract(), this);
    }


}
