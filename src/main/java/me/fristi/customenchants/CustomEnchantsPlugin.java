package me.fristi.customenchants;

import me.fristi.customenchants.CEs.CustomEnchants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;


public class CustomEnchantsPlugin extends JavaPlugin implements Listener {

    private int xpCost;
    private int level;
    @Override
    public void onEnable() {
        CustomEnchants.register();
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() { }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        ItemStack axe = new ItemStack(Material.WOODEN_AXE);
        axe.addUnsafeEnchantment(CustomEnchants.HEMORRHAGE, 1);
        event.getPlayer().getInventory().setItemInMainHand(axe);
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player player){
            if(player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.HEMORRHAGE)){
                event.getEntity().setVelocity(new Vector(20, 30, 0));
                event.getEntity().getWorld().strikeLightning(player.getTargetBlock(null, 0).getLocation());
            }
        }
    }
    @EventHandler
    public void onPrepEnchant(PrepareItemEnchantEvent event) {
        if (event.getItem().getType().equals(Material.DIAMOND_AXE)) {
            level = 1;
            xpCost = 69;
            if (Random(0, 100) < 100) { //100% kans
                event.getOffers()[Random(0, 2)] = new EnchantmentOffer(CustomEnchantsManager.HEMORRHAGE, level, xpCost);
            }
        }
    }
    @EventHandler
    public void onEnchant(EnchantItemEvent event){
        // Set enchantment
        event.whichButton();
        int diff = xpCost - event.getExpLevelCost();
        event.getEnchanter().setExp(event.getEnchanter().getExp() - diff);
        Map<Enchantment, Integer> oldEnchantMap = event.getEnchantsToAdd();
        oldEnchantMap.put(CustomEnchantsManager.HEMORRHAGE, level);

        // Get current lore
        ItemStack item = event.getItem();
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        // Update lore
        lore.add(ChatColor.DARK_PURPLE + "Hemorrhage "+ level);
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
    @EventHandler
    public void grindStoneClick(InventoryClickEvent event) {
        if (event.getClickedInventory().getType() == InventoryType.GRINDSTONE && event.getSlotType() == InventoryType.SlotType.RESULT)
            event.getCursor().getItemMeta().setLore(new ArrayList<>());
    }

    private int Random(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
