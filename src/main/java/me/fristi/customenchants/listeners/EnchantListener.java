package me.fristi.customenchants.listeners;

import me.fristi.customenchants.CEs.Hemorrhage_Axe;
import me.fristi.customenchants.CustomEnchants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.concurrent.ThreadLocalRandom;

public class EnchantListener implements Listener {

    @EventHandler
    public void onPlayerEnchant(PrepareItemEnchantEvent e) { // !!!==Does not contain any random ints for chances==!!!
        //https://youtu.be/wd1FUOT-BJY <<~~~~~~~~~~
        // Debug message to player:
        e.getEnchanter().sendMessage(ChatColor.RED + "HMM, enchanting are we now. Won't make your dick grow though. I'm sorry to disappoint you");
        // Offer enchantment when player tries to enchant a Diamond_Axe
        if (Hemorrhage_Axe.CanBeOn(e.getItem())) {
            // Another debugging message
            e.getEnchanter().sendMessage(ChatColor.RED + "ah yes, a dia axe");
            // The actual code:
            e.getOffers()[Random(0, 2)] = new EnchantmentOffer(new EnchantmentWrapper("hemorrhage"), 6, 70);
        }
    }

    /* Oude code die Jack eerder probeerde, lijkt interessant:
        e = entity of zo
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

    private int Random(int a, int b) { return ThreadLocalRandom.current().nextInt(a, b); }
}
