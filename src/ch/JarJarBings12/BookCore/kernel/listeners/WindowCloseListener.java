package ch.JarJarBings12.BookCore.kernel.listeners;

import ch.JarJarBings12.BookCore.kernel.threads.SessionEvent;
import ch.JarJarBings12.BookCore.kernel.window.events.BookCoreSubscribeEvent;
import ch.JarJarBings12.BookCore.kernel.window.events.WindowCloseEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 16.06.2015
 */
public class WindowCloseListener implements Listener
{
    @EventHandler
    public void onWindowClose(WindowCloseEvent e)
    {
        Bukkit.getPluginManager().callEvent(new BookCoreSubscribeEvent(new SessionEvent(e.getPlayer(), e), ""));
    }
}
