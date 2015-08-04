package ch.JarJarBings12.BookCore.kernel.bridges.events;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.kernel.UI.UIDisplaySession;
import ch.JarJarBings12.BookCore.kernel.window.events.WindowOpenEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 28.06.2015
 */
public class InventoryOpenBridge implements Listener
{
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e)
    {
        if (BookCoreFramework.getWindowManager().getWindowHandler().isWindowHolder((Player) e.getPlayer()))
            return;
        UIDisplaySession displaySession = BookCoreFramework.getWindowManager().getSessionHandler().getDisplaySession(BookCoreFramework.getWindowManager().getSessionHandler().getSession((Player) e.getPlayer()));
        Bukkit.getPluginManager().callEvent(new WindowOpenEvent((Player)e.getPlayer(), displaySession.getWindow().getSystemName(), e, displaySession, ""));
    }
}
