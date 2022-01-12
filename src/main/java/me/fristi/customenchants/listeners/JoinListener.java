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
    @EventHandler
    public void onPlayerEnchant(PrepareItemEnchantEvent e) { // !!!==Does not contain any random ints for chances==!!!
        // Debug message to player:
        e.getEnchanter().sendMessage(ChatColor.RED + "HMM, enchanting are we now. Won't make your dick grow though. I'm sorry to disappoint you");
        // Offer enchantment when player tries to enchant a Diamond_Axe
        if (e.getItem().getType().equals(Material.DIAMOND_AXE)) {
            e.getEnchanter().sendMessage(ChatColor.RED + "ah yes, a dia axe"); // Another debugging message
            e.getOffers()[Random(0, 2)] = new EnchantmentOffer(Enchantment.KNOCKBACK, 3, 1);

        }
    }

    ItemStack CreateEnchantedItemStack(Material m, Enchantment c, int level, String lore){
        ItemStack itemstack = new ItemStack(m, 1);
        ItemMeta meta = itemstack.getItemMeta();

        // Set lore
        List<String> Lore = new ArrayList<>();
        Lore.add(lore);
        meta.setLore(Lore);

        //finish up
        itemstack.addEnchantment(c, level);
        itemstack.setItemMeta(meta);

        EnchantmentWrapper.registerEnchantment(c);
        return itemstack;
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

