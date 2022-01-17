package me.fristi.customenchants.CEs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import java.util.Map;

public class Hemorrhage extends CustomEnchantment {

    public Hemorrhage() {
        super("Hemorrhage", 7, false, false);
    }

    @Override
    public void EventHandler(Event event) {
        if      (event instanceof EntityDamageByEntityEvent e)  HitEntity(e);
        else if (event instanceof PrepareItemEnchantEvent e)    PrepareEnchanting(e);
        else if (event instanceof EnchantItemEvent e)           EnchantItem(e);
        else if (event instanceof InventoryClickEvent e)        InventoryClick(e);
        else if (event instanceof PrepareAnvilEvent e)          AnvilPrepare(e);
    }

    private void HitEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof LivingEntity damager && event.getEntity() instanceof LivingEntity cocksucker
            && damager.getEquipment() != null)
            if (ItemHasEnchantment(damager.getEquipment().getItemInMainHand(), this)) { //TODO: Dit als eerste testen?
                cocksucker.setVelocity(new Vector(20, 30, 0));
                cocksucker.getWorld().strikeLightning(cocksucker.getLocation());
            }
    }
    private void PrepareEnchanting(PrepareItemEnchantEvent event) {
        if (event.getItem().getType().equals(Material.DIAMOND_AXE)) {
            int level = 1;
            int xpCost = 69;
            if (RandomInt(0, 100) < 100) { //100% kans //TODO
                event.getOffers()[RandomInt(0, 2)] = new EnchantmentOffer(this, level, xpCost);
            }
        }
    }

    int xpCost = 69; //TODO
    int level = 2; //TODO
    private void EnchantItem(EnchantItemEvent event) {
        event.whichButton();
        int diff = xpCost - event.getExpLevelCost();
        event.getEnchanter().setExp(event.getEnchanter().getExp() - diff);
        Map<Enchantment, Integer> oldEnchantMap = event.getEnchantsToAdd();
        oldEnchantMap.put(this, level);
        AddLore(event.getItem(), ChatColor.DARK_PURPLE, "AIDS " + level);
    }

    private void InventoryClick(InventoryClickEvent event) { //TODO: nullReferenceExceptions kunnen overal in dit project optreden
        ItemStack clickedItem = event.getCurrentItem();
        if (event.getClickedInventory().getType() == InventoryType.GRINDSTONE && event.getSlotType() == InventoryType.SlotType.RESULT) {
            event.getWhoClicked().sendMessage("lol");
            RemoveLore(clickedItem);
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
            AddLore(event.getResult(), ChatColor.AQUA, "AIDS " + newLvl);
        }
    }
}