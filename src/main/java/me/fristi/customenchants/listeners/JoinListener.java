package me.fristi.customenchants.listeners;
import me.fristi.customenchants.CustomEnchants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.concurrent.ThreadLocalRandom;

import javax.sound.midi.MetaEventListener;
import java.io.Console;
import java.util.ArrayList;

import static java.lang.Math.random;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        ItemStack axe = new ItemStack(Material.DIAMOND_AXE, 1);
        ItemStack rod = new ItemStack(Material.FISHING_ROD, 1);
        ArrayList<String> lore = new ArrayList<>();

        axe.addUnsafeEnchantment(CustomEnchants.hemorrhage_axe, 1);
        ItemMeta meta = axe.getItemMeta();
        lore.add(ChatColor.DARK_PURPLE+ "Hemorrhage I");
        meta.setLore(lore);
        axe.setItemMeta(meta);

        rod.addUnsafeEnchantment(CustomEnchants.grappling_hook_fishing_rod, 1);
        meta = rod.getItemMeta();
        lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE+ "Grapple I");
        meta.setLore(lore);
        rod.setItemMeta(meta);

        player.getInventory().setItemInMainHand(axe);
        player.getInventory().addItem(rod);
    }
    @EventHandler
    public void onPlayerEnchant(PrepareItemEnchantEvent e) { // !!!==Does not contain any random ints for chances==!!!
        // Debug message to player:
        e.getEnchanter().sendMessage(ChatColor.MAGIC + "HMM, enchanting are we now. Won't make your dick grow");
        // Offer enchantment when player tries to enchant a Diamond_Axe
        if (e.getItem().equals(Material.DIAMOND_AXE)) {
            e.getEnchanter().sendMessage(ChatColor.MAGIC + "ah yes, a dia axe"); // Another debugging message
            e.getOffers()[Random(0, 2)] = new EnchantmentOffer(CustomEnchants.hemorrhage_axe, 1, 1);
        }
    }

    private int Random(int a, int b) { return ThreadLocalRandom.current().nextInt(a, b); }
}

/*
        e.getEnchanter().sendMessage(ChatColor.MAGIC+ "HMM, enchanting are we now");
        EnchantmentOffer offer = new EnchantmentOffer(CustomEnchants.hemorrhage_axe, 1,1);
        EnchantmentOffer offer2 = new EnchantmentOffer(CustomEnchants.hemorrhage_axe, 2,2);
        EnchantmentOffer offer3 = new EnchantmentOffer(CustomEnchants.hemorrhage_axe, 3,3);
        EnchantmentOffer[] offers = new EnchantmentOffer[3];
        offers[0] = offer;
        offers[1] = offer2;
        offers[2] = offer3;
        e = new PrepareItemEnchantEvent(e.getEnchanter(), (InventoryView) e.getInventory(), e.getEnchantBlock(), e.getItem(),offers, e.getEnchantmentBonus());
        //EnchantItemEvent() ?
        e.getEnchanter().sendMessage(e.toString());
 */

