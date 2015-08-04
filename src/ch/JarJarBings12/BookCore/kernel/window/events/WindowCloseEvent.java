package ch.JarJarBings12.BookCore.kernel.window.events;

import ch.JarJarBings12.BookCore.kernel.UI.UIDisplaySession;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 15.06.2015
 */
public class WindowCloseEvent extends Event implements Cancellable
{
    private boolean cancelled;

    private Player player;
    private String system_name;
    private InventoryCloseEvent inventoryCloseEvent;
    private UIDisplaySession displaySession;
    private String flags;

    public WindowCloseEvent(Player player, String system_name, InventoryCloseEvent event, UIDisplaySession displaySession, String flags)
    {
        this.player = player;
        this.system_name = system_name;
        this.inventoryCloseEvent = event;
        this.displaySession = displaySession;
        this.flags = flags;
    }

    private static final HandlerList handlers = new HandlerList();

    @Override
    public boolean isCancelled()
    {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b)
    {
        this.cancelled = b;
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

    public Player getPlayer()
    {
        return player;
    }

    public String getSystemName()
    {
        return system_name;
    }

    public InventoryCloseEvent getEvent()
    {
        return inventoryCloseEvent;
    }

    public UIDisplaySession getDisplaySession()
    {
        return displaySession;
    }

    public String getFlags()
    {
        return this.flags;
    }
}
