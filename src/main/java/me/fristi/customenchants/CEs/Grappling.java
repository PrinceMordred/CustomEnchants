package me.fristi.customenchants.CEs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class Grappling extends CustomEnchantment{

    private static final float grappleVelocity = 1.5f;
    private static final float yoinkVelocity = 1.5f;
    private static final float minimumGrappleEffectAsInDistanceKindOf = 10f;
    private static final float hookVelocityMultiplier = 1.5f;
    private static final float hookVelocityMultiplierWhenCrouched = 1f;

    public Grappling() { super("Grappling", 1, new Material[] { Material.FISHING_ROD }, false, false); }

    @Override
    public void EventListener(Event event) {
        super.EventListener(event);
        if      (event instanceof PlayerFishEvent e)   PlayerFishes(e);
    }

    private void PlayerFishes(PlayerFishEvent event) {
        Player player = event.getPlayer();
        FishHook hook = event.getHook();
        Vector playerVelocity = player.getVelocity();
        Vector hookVelocity = hook.getVelocity();

        if (event.getState() == PlayerFishEvent.State.FISHING) {
            if (player.isSneaking()) hook.setVelocity(hookVelocity.multiply(hookVelocityMultiplierWhenCrouched));
            else                     hook.setVelocity(hookVelocity.multiply(hookVelocityMultiplier));
        }
        else if (event.getState() == PlayerFishEvent.State.IN_GROUND) {
            Vector DistanceVector = CreateDistanceVector(player.getLocation(), hook.getLocation());

            // When the distance between you and the hook is less than minimumGrappleEffectAsInDistanceKindOf, set the distance to that so that a minimum grapple effect is applied.
            if (DistanceVector.length() < minimumGrappleEffectAsInDistanceKindOf)
                DistanceVector.normalize().multiply(minimumGrappleEffectAsInDistanceKindOf);

            Vector gainedVelocity = DistanceVector.multiply(0.1f * grappleVelocity);
            player.setVelocity(playerVelocity.add(gainedVelocity));
        }
        else if (event.getState() == PlayerFishEvent.State.CAUGHT_ENTITY && event.getCaught() != null) {
            Entity victim = event.getCaught();
            Vector victimVelocity = victim.getVelocity();

            Vector DistanceVector = CreateDistanceVector(victim.getLocation(), player.getLocation());
            Vector gainedVelocity = DistanceVector.multiply(0.1f * yoinkVelocity);
            victim.setVelocity(victimVelocity.add(gainedVelocity));
        }
    }
}