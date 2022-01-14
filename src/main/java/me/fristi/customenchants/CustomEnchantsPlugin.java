package me.fristi.customenchants;

import me.fristi.customenchants.CEs.CustomEnchantsManager;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
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


public class CustomEnchantsPlugin extends JavaPlugin implements Listener {

    private int xpCost;
    private int level;
    @Override
    public void onEnable() {
        CustomEnchantsManager.RegisterEnchantments();
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() { throw new NotImplementedException(); }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        ItemStack gift = new ItemStack(Material.CHICKEN);
        gift.addUnsafeEnchantment(CustomEnchantsManager.HEMORRHAGE, 1);
        event.getPlayer().getInventory().addItem(gift);
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player player){
            if(player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchantsManager.HEMORRHAGE)){
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
        AddLore(event.getItem(), ChatColor.DARK_PURPLE, "AIDS");

    }
    @EventHandler
    public void invClick(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem();
        if (event.getClickedInventory().getType() == InventoryType.GRINDSTONE && event.getSlotType() == InventoryType.SlotType.RESULT) {
            event.getWhoClicked().sendMessage("lol");
            ItemMeta meta = clickedItem.getItemMeta();
            meta.setLore(new ArrayList<>());
            clickedItem.setItemMeta(meta);
        }
        if (event.getInventory().getType() == InventoryType.ANVIL && event.getSlotType() == InventoryType.SlotType.RESULT) {
            ItemStack current = event.getCurrentItem();
            event.getWhoClicked().sendMessage("lol2");
            clickedItem.addEnchantment(CustomEnchantsManager.HEMORRHAGE, 1);
            AddLore(clickedItem, ChatColor.AQUA, "click");
            AddLore(current, ChatColor.AQUA, "current");
        }
    }

    private int Random(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    public void AddLore(ItemStack item, ChatColor chatColor, String lore){
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(ChatColor.DARK_PURPLE + lore +" "+ level);
        meta.setLore(Lore);
        item.setItemMeta(meta);
    }
}
