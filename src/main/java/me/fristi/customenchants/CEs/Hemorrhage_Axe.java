package me.fristi.customenchants.CEs;

import me.fristi.customenchants.CustomEnchants;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Hemorrhage_Axe extends Enchantment implements Listener {

    public Hemorrhage_Axe(String Namespace){
        super(new NamespacedKey(CustomEnchants.getPlugin(),Namespace));
    }
    @EventHandler
    public void OnPlayerHit(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player){
            Player player = (Player) e.getDamager();
            if (player.getEquipment().getItemInMainHand().getEnchantments().containsKey(Enchantment.getByKey((CustomEnchants.hemorrhage_axe.getKey())))){
                e.getEntity().setGlowing(true);
            }
        }
    }
    @Override
    public String getName() {
        return "Hemorrhage";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }
}
