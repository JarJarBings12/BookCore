package ch.JarJarBings12.BookCore.kernel.bridges.events;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.kernel.UI.UIDisplaySession;
import ch.JarJarBings12.BookCore.kernel.window.events.WindowClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 16.06.2015
 */
public class InventoryClickBridge implements Listener
{
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        if (!BookCoreFramework.getWindowManager().getWindowHandler().isWindowHolder((Player) e.getWhoClicked()))
            return;
        UIDisplaySession displaySession = BookCoreFramework.getWindowManager().getSessionHandler().getDisplaySession(BookCoreFramework.getWindowManager().getSessionHandler().getSession((Player) e.getWhoClicked()));
        Bukkit.getPluginManager().callEvent(new WindowClickEvent((Player)e.getWhoClicked(), displaySession.getWindow().getSystemName(), e, displaySession, ""));
        return;
    }
}
