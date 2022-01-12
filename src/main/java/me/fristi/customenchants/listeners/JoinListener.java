package me.fristi.customenchants.listeners;
import me.fristi.customenchants.CEs.Grappling_Hook_Fishing_Rod;
import me.fristi.customenchants.CEs.Hemorrhage_Axe;
import me.fristi.customenchants.CustomEnchants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import java.io.Console;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        ItemStack axe = CreateEnchantedItemStack(Material.DIAMOND_AXE, CustomEnchants.hemorrhage_axe,             1, ChatColor.DARK_PURPLE+ "Hemorrhage I");
        ItemStack rod = CreateEnchantedItemStack(Material.FISHING_ROD, CustomEnchants.grappling_hook_fishing_rod, 1, ChatColor.DARK_PURPLE+ "Grapple I");

        // Add items for debugging purposes to player's inventory
        PlayerInventory playerInvent = e.getPlayer().getInventory();
        playerInvent.setItemInMainHand(axe);
        playerInvent.addItem(rod);
    }

    ItemStack CreateEnchantedItemStack(Material m, Enchantment c, int level, String lore){
        ItemStack itemstack = new ItemStack(m, 1);
        ItemMeta meta = itemstack.getItemMeta();

        // Set lore
        List<String> Lore = new ArrayList<>();
        Lore.add("Lore: " + lore);
        meta.setLore(Lore);

        //finish up
        itemstack.addEnchantment(c, level);
        itemstack.setItemMeta(meta);

        EnchantmentWrapper.registerEnchantment(c);
        return itemstack;
    }
}

