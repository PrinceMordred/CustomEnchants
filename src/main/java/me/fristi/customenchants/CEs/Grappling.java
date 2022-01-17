package me.fristi.customenchants.CEs;

import org.bukkit.event.Event;

public class Grappling extends CustomEnchantment{

    public Grappling() { super("Grappling", 1, false, false); }

    @Override
    public void EventHandler(Event event) {
        //if (event instanceof EntityDamageByEntityEvent e)   HitEntity(e);
    }
}
