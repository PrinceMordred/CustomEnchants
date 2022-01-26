package me.fristi.customenchants.CEs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.event.Event;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class CustomEnchantment extends CustomEnchantmentWrapper {

    private int level; // TODO
    private int xpCost; //TODO
    private final Material[] EnchantableMats;

    public CustomEnchantment(String name, int maxLvl, Material[] enchantableMats, boolean isTreasure, boolean isCursed) {
        super(name.toLowerCase(), name, maxLvl, isTreasure, isCursed);
        EnchantableMats = enchantableMats;
    }

    protected static boolean ItemHasEnchantment(ItemStack i, Enchantment e){
        return i.getItemMeta() != null && i.getItemMeta().hasEnchant(e);
    }
    protected final void AddLoreToItem(ItemStack item, ChatColor chatColor, String lore){
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(chatColor+ lore);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(Lore);
        item.setItemMeta(meta);
    }
    protected final void RemoveLoreFromItem(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        meta.setLore(null);
        item.setItemMeta(meta);
    }
    protected Vector CreateDistanceVector(Location origin, Location head){
        return head.toVector().subtract(origin.toVector());
    }


    public void EventListener(Event event) {
        if      (event instanceof PrepareItemEnchantEvent e)    PrepareEnchanting(e);
        else if (event instanceof EnchantItemEvent e)           EnchantItem(e);
        else if (event instanceof InventoryClickEvent e)        InventoryClick(e);
        else if (event instanceof PrepareAnvilEvent e)          AnvilPrepare(e);
    }

    //<editor-fold desc="EventHandlers">
    private void PrepareEnchanting(PrepareItemEnchantEvent event) {
        if (Arrays.stream(EnchantableMats).toList().contains(event.getItem().getType())) {
            level = 1; //TODO
            xpCost = 69; //TODO
            if (RandomInt(0, 100) < 100) { //100% kans //TODO
                event.getOffers()[RandomInt(0, 2)] = new EnchantmentOffer(this, level, xpCost);
            }
        }
    }

    private void EnchantItem(EnchantItemEvent event) {
        event.whichButton();
        int diff = xpCost - event.getExpLevelCost();
        event.getEnchanter().setExp(event.getEnchanter().getExp() - diff);
        Map<Enchantment, Integer> oldEnchantMap = event.getEnchantsToAdd();
        oldEnchantMap.put(this, level);

        String lore = getName();
        if (level > 1) lore += ' ' + level;
        AddLoreToItem(event.getItem(), ChatColor.DARK_PURPLE, lore);
    }

    private void InventoryClick(InventoryClickEvent event) { //TODO: nullReferenceExceptions kunnen overal in dit project optreden
        ItemStack clickedItem = event.getCurrentItem();
        if (event.getClickedInventory().getType() == InventoryType.GRINDSTONE && event.getSlotType() == InventoryType.SlotType.RESULT) {
            event.getWhoClicked().sendMessage("lol");
            RemoveLoreFromItem(clickedItem);
        }
    }

    // TODO fix the level sys
    private void AnvilPrepare(PrepareAnvilEvent event) {
        if (event.getInventory().getItem(0).getEnchantments().containsKey(this) || event.getInventory().getItem(0).getEnchantments().containsKey(this)) {
            int newLvl = 1;
            if (!(event.getInventory().getItem(0) == null) && event.getInventory().getItem(0).getEnchantments().containsKey(this)) {
                newLvl = event.getInventory().getItem(0).getEnchantmentLevel(this);
                if (!(event.getInventory().getItem(1) == null) && event.getInventory().getItem(1).getEnchantments().containsKey(this)) {
                    newLvl += 1;
                }
            }
            event.getResult().addUnsafeEnchantment(this, newLvl);
            AddLoreToItem(event.getResult(), ChatColor.AQUA, "AIDS " + newLvl);
        }
    }
    //</editor-fold>

    protected static int RandomInt(int min, int max) { return ThreadLocalRandom.current().nextInt(min, max + 1); }
}
