package me.fristi.customenchants.CEs;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Hentai extends CustomEnchantment{
    public Hentai() {
        super("Hentai", 69, new Material[] {Material.SPRUCE_WOOD}, false, false);
    }

    @Override
    public void EventListener(Event event) {
        super.EventListener(event);
        if      (event instanceof EntityDamageByEntityEvent e)  HitEntity(e);
        //else if (event instanceof )
    }

    private void HitEntity(EntityDamageByEntityEvent event){
        //((LivingEntity) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON));
    }
}
