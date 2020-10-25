package com.github.steviebeenz.laggyfly;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;

public final class FlightAlertEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player message;

    public FlightAlertEvent(Player example) {
        message = example;
    }

    public Player getPlayer() {
        return message;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}