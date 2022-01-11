package me.fristi.customenchants.listeners;
import me.fristi.customenchants.CustomEnchants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class JoinListener implements Listener{

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        ItemStack axe = new ItemStack(Material.IRON_AXE, 1);
        axe.addUnsafeEnchantment(CustomEnchants.hemorrhage_axe, 1);
        ItemMeta meta = axe.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE + "Hemorrhage I");
        meta.setLore(lore);
        axe.setItemMeta(meta);
        player.getEquipment().setItemInMainHand(axe);
    }
}
