package me.fristi.customenchants.CEs;

import me.fristi.customenchants.CustomEnchantsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class Grappling extends CustomEnchantment{

    private final int velocity = 20;
    /*private final Runnable r = new Runnable() {
        @Override
        public void run() { PlayerFishes(event); }
    };*/

    public Grappling() { super("Grappling", 1, new Material[] { Material.FISHING_ROD }, false, false); }

    @Override
    public void EventListener(Event event) {
        super.EventListener(event);
        if      (event instanceof PlayerFishEvent e)   PlayerFishes(e);
    }

    private void PlayerFishes(PlayerFishEvent event) {
        //event.getPlayer().sendMessage("Hello there");
        //while (event.getState() != PlayerFishEvent.State.REEL_IN)
        //    Bukkit.getScheduler().runTaskLater(CustomEnchantsPlugin, 100L);
        Location location = event.getHook().getLocation();
        //event.getPlayer().sendMessage("Location: " + location);
        Vector vector = location.getDirection();
        //event.getPlayer().sendMessage("Vector: " + vector);
        event.getPlayer().setVelocity(vector.multiply(velocity));
        //event.getPlayer().sendMessage("Vector netto: " + vector);
    }
}
