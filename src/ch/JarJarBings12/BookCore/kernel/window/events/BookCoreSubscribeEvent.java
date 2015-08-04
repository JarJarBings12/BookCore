package ch.JarJarBings12.BookCore.kernel.window.events;

import ch.JarJarBings12.BookCore.kernel.threads.SessionEvent;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 09.07.2015
 */
public class BookCoreSubscribeEvent extends Event
{
    private SessionEvent sessionEvent;
    private String flags;

    public BookCoreSubscribeEvent(SessionEvent sessionEvent, String flags)
    {
        this.sessionEvent = sessionEvent;
        this.flags = flags;
    }

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

    public SessionEvent getSessionEvent()
    {
        return this.sessionEvent;
    }

    public String getFlags()
    {
        return this.flags;
    }
}
