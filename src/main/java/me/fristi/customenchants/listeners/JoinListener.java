package me.fristi.customenchants.listeners;
import me.fristi.customenchants.CustomEnchants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.sound.midi.MetaEventListener;
import java.io.Console;
import java.util.ArrayList;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        //Give the player an axe with the enchantment when they join
        Player player = e.getPlayer();
        ItemStack axe = new ItemStack(Material.DIAMOND_AXE, 1);
        axe.addUnsafeEnchantment(CustomEnchants.hemorrhage_axe, 1);
        ItemMeta meta = axe.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE+ "Hemorrhage I");
        meta.setLore(lore);
        axe.setItemMeta(meta);
        player.getInventory().setItemInMainHand(axe);

        ItemStack rod = new ItemStack(Material.FISHING_ROD, 1);
        meta = rod.getItemMeta();
        lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE+ "Grapple I");
        meta.setLore(lore);
        rod.addUnsafeEnchantment(CustomEnchants.grappling_hook_fishing_rod, 1);
        rod.setItemMeta(meta);
        player.getInventory().addItem(rod);
    }
    @EventHandler
    public void onPlayerEnchant(PrepareItemEnchantEvent e){
        e.getEnchanter().sendMessage(ChatColor.MAGIC+ "HMM, enchanting are we now");
        EnchantmentOffer offer = new EnchantmentOffer(CustomEnchants.hemorrhage_axe, 1,1);
        EnchantmentOffer offer2 = new EnchantmentOffer(CustomEnchants.hemorrhage_axe, 2,2);
        EnchantmentOffer offer3 = new EnchantmentOffer(CustomEnchants.hemorrhage_axe, 3,3);
        EnchantmentOffer[] offers = new EnchantmentOffer[3];
        offers[0] = offer;
        offers[1] = offer2;
        offers[2] = offer3;
        e = new PrepareItemEnchantEvent(e.getEnchanter(), (InventoryView) e.getInventory(), e.getEnchantBlock(), e.getItem(),offers, e.getEnchantmentBonus());

    }
}

