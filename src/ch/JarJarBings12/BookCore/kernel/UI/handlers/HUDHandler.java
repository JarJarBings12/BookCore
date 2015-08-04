package ch.JarJarBings12.BookCore.kernel.UI.handlers;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.kernel.UI.UIDisplaySession;
import ch.JarJarBings12.BookCore.kernel.window.objects.BCWindow;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
//TODO Add Documentation
/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 16.07.2015
 */
public class HUDHandler
{
    public UIDisplaySession display(Player player, BCWindow window)
    {
        UIDisplaySession uiDisplaySession1 = new UIDisplaySession(player, window);
        uiDisplaySession1.display();
        return uiDisplaySession1;
    }

    public UIDisplaySession display(List<Player> players, BCWindow window)
    {
        UIDisplaySession uiDisplaySession = new UIDisplaySession(players, window);
        uiDisplaySession.display();
        return uiDisplaySession;
    }

    public UIDisplaySession display(Player player, String system_name)
    {
        UIDisplaySession uiDisplaySession = new UIDisplaySession(player, BookCoreFramework.getWindowManager().getWindowHandler().get(system_name));
        uiDisplaySession.display();
        return uiDisplaySession;
    }

    public UIDisplaySession display(Player[] players, String system_name)
    {
        UIDisplaySession uiDisplaySession = new UIDisplaySession(Arrays.asList(players), BookCoreFramework.getWindowManager().getWindowHandler().get(system_name));
        uiDisplaySession.display();
        return uiDisplaySession;
    }

    public void close(Player player)
    {
        if (BookCoreFramework.getWindowManager().getWindowHandler().isWindowHolder(player))
            BookCoreFramework.getWindowManager().getSessionHandler().getDisplaySession(BookCoreFramework.getWindowManager().getSessionHandler().getSession(player)).close();
        return;
    }

    public void close(String session)
    {
        if (BookCoreFramework.getWindowManager().getWindowHandler().isSessionOpen(session))
            BookCoreFramework.getWindowManager().getSessionHandler().getDisplaySession(session).close();
        return;
    }
}
