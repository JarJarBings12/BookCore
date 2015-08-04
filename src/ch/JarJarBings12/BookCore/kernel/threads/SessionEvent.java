package ch.JarJarBings12.BookCore.kernel.threads;

import ch.JarJarBings12.BookCore.kernel.window.events.WindowClickEvent;
import ch.JarJarBings12.BookCore.kernel.window.events.WindowCloseEvent;
import ch.JarJarBings12.BookCore.kernel.window.events.WindowInteractEvent;
import ch.JarJarBings12.BookCore.kernel.window.events.WindowOpenEvent;
import org.bukkit.entity.Player;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 09.07.2015
 */
public class SessionEvent<E>
{
    private Player player;
    private E event;
    private EventType eventType;

    public SessionEvent(Player player, E event)
    {
        this.player = player;
        this.event = event;
        this.eventType = a(event);
    }

    public Player getPlayer()
    {
        return this.player;
    }

    public E getEvent()
    {
        return this.event;
    }

    public EventType getSessionEvent()
    {
        return this.eventType;
    }

    private EventType a(E event)
    {
        if (event instanceof WindowClickEvent)
        {
            return EventType.CLICK;
        } else if (event instanceof WindowCloseEvent)
        {
            return EventType.CLOSE;
        } else if (event instanceof WindowInteractEvent)
        {
            return EventType.INTERACT;
        } else if (event instanceof WindowOpenEvent)
        {
            return EventType.OPEN;
        } else
        {
            throw new IllegalArgumentException();
        }
    }
}
