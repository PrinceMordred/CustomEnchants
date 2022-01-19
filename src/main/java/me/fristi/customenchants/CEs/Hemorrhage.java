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
        super("Hemorrhage", 7, new Material[] { Material.DIAMOND_AXE }, false, false);
    }

    @Override
    public void EventListener(Event event) {
        super.EventListener(event);
        if      (event instanceof EntityDamageByEntityEvent e)  HitEntity(e);
    }

    private void HitEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof LivingEntity damager && event.getEntity() instanceof LivingEntity cocksucker
            && damager.getEquipment() != null)
            if (ItemHasEnchantment(damager.getEquipment().getItemInMainHand(), this)) { //TODO: Dit als eerste testen?
                cocksucker.setVelocity(new Vector(20, 30, 0));
                cocksucker.getWorld().strikeLightning(cocksucker.getLocation());
            }
    }
}