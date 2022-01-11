package me.fristi.customenchants.listeners;
import me.fristi.customenchants.CustomEnchants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        //Give the player an axe with the enchantment when they join
        Player player = e.getPlayer();
        ItemStack axe = new ItemStack(Material.DIAMOND_AXE, 1);
        axe.addUnsafeEnchantment(CustomEnchants.hemorrhage_axe, 1);
        player.getInventory().setItemInMainHand(axe);

        ItemStack rod = new ItemStack(Material.FISHING_ROD, 1);
        rod.addUnsafeEnchantment(CustomEnchants.grappling_hook_fishing_rod, 1);
        player.getInventory().addItem(rod);
    }
}

