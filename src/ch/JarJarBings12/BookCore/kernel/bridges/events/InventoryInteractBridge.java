package ch.JarJarBings12.BookCore.kernel.bridges.events;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.kernel.UI.UIDisplaySession;
import ch.JarJarBings12.BookCore.kernel.window.events.WindowInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 16.06.2015
 */
public class InventoryInteractBridge implements Listener
{
    @EventHandler
    public void onInventoryInteractEvent(InventoryInteractEvent e)
    {
        if (!BookCoreFramework.getWindowManager().getWindowHandler().isWindowHolder((Player) e.getWhoClicked()))
            return;
        UIDisplaySession displaySession = BookCoreFramework.getWindowManager().getSessionHandler().getDisplaySession(BookCoreFramework.getWindowManager().getSessionHandler().getSession((Player) e.getWhoClicked()));
        Bukkit.getPluginManager().callEvent(new WindowInteractEvent((Player)e.getWhoClicked(), displaySession.getWindow().getSystemName(), e, displaySession, ""));
    }
}