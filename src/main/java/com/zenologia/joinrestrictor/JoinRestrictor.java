package com.zenologia.joinrestrictor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class JoinRestrictor extends JavaPlugin implements Listener, TabExecutor {

    private String kickMessage;
    private boolean logDeniedJoins;
    private String requiredPermission;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfigValues();
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("joinrestrictor").setExecutor(this);
        this.getCommand("joinrestrictor").setTabCompleter(this);
        getLogger().info("JoinRestrictor enabled.");
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        if (!event.getPlayer().hasPermission(requiredPermission)) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, kickMessage);
            if (logDeniedJoins) {
                getLogger().info("Blocked join attempt by " + event.getPlayer().getName() + " [" + event.getAddress().getHostAddress() + "]");
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("joinrestrictor.reload")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to run this command.");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            reloadConfig();
            loadConfigValues();
            sender.sendMessage(ChatColor.GREEN + "JoinRestrictor config reloaded.");
            return true;
        }

        sender.sendMessage(ChatColor.YELLOW + "Usage: /joinrestrictor reload");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1 && "reload".startsWith(args[0].toLowerCase())) {
            return Collections.singletonList("reload");
        }
        return Collections.emptyList();
    }

    private void loadConfigValues() {
        FileConfiguration config = getConfig();
        this.kickMessage = ChatColor.translateAlternateColorCodes('&',
                config.getString("kick-message", "&cYou are not allowed to join this server."));
        this.logDeniedJoins = config.getBoolean("log-denied-joins", true);
        this.requiredPermission = config.getString("required-permission", "essentials.joinfullserver");
    }
}
