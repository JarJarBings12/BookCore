package ch.JarJarBings12.BookCore.kernel.bridges.events;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.kernel.UI.UIDisplaySession;
import ch.JarJarBings12.BookCore.kernel.window.events.WindowCloseEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 15.06.2015
 */
public class InventoryCloseBridge implements Listener
{

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e)
    {
        if (!BookCoreFramework.getWindowManager().getWindowHandler().isWindowHolder((Player) e.getPlayer()))
            return;
        UIDisplaySession displaySession = BookCoreFramework.getWindowManager().getSessionHandler().getDisplaySession(BookCoreFramework.getWindowManager().getSessionHandler().getSession((Player) e.getPlayer()));
        Bukkit.getServer().getPluginManager().callEvent(new WindowCloseEvent((Player) e.getPlayer(), displaySession.getWindow().getSystemName(), e, displaySession, ""));
        return;
    }
}
