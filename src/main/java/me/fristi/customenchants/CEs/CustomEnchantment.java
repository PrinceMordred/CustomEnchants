package me.fristi.customenchants.CEs;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class CustomEnchantment extends CustomEnchantmentWrapper {

    public CustomEnchantment(String name, int maxLvl, boolean isTreasure, boolean isCursed) {
        super(name.toLowerCase(), name, maxLvl, isTreasure, isCursed);
    }

    public boolean ItemHasEnchantment(ItemStack i, Enchantment e){
        return i.getItemMeta() != null && i.getItemMeta().hasEnchant(e);
    }
    protected int RandomInt(int min, int max) { return ThreadLocalRandom.current().nextInt(min, max + 1); }
    protected void AddLore(ItemStack item, ChatColor chatColor, String lore){
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(chatColor+ lore);
        meta.setLore(Lore);
        item.setItemMeta(meta);
    }
    protected void RemoveLore(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        meta.setLore(null);
        item.setItemMeta(meta);
    }

    public abstract void EventHandler(Event event);
}
