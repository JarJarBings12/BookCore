package ch.JarJarBings12.BookCore.kernel;

import ch.JarJarBings12.BookCore.kernel.UI.handlers.HUDHandler;
import ch.JarJarBings12.BookCore.kernel.UI.handlers.SessionHandler;
import ch.JarJarBings12.BookCore.sql.windows.backup.WindowHistory;
import ch.JarJarBings12.BookCore.sql.windows.transfer.AdvancedWindowTransfer;
import ch.JarJarBings12.BookCore.sql.windows.transfer.WindowTransfer;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 14.06.2015
 */
public class WindowManager
{
    private static WindowHistory windowHistory = null;
    private static WindowTransfer windowTransfer = null;
    private static AdvancedWindowTransfer advancedWindowTransfer = null;
    private static SessionHandler sessionHandler = null;
    private static WindowHandler windowHandler = null;
    private static HUDHandler hudHandler = new HUDHandler();

    static
    {
        windowHistory = new WindowHistory();
        windowTransfer = new WindowTransfer();
        advancedWindowTransfer = new AdvancedWindowTransfer();
        sessionHandler = new SessionHandler();
        windowHandler = new WindowHandler();
        hudHandler = new HUDHandler();
    }

    public WindowHistory getWindowHistory()
    {
        return windowHistory;
    }

    public WindowTransfer getWindowTransfer()
    {
        return windowTransfer;
    }

    public AdvancedWindowTransfer getAdvancedWindowTransfer()
    {
        return advancedWindowTransfer;
    }

    public SessionHandler getSessionHandler()
    {
        return sessionHandler;
    }

    public WindowHandler getWindowHandler()
    {
        return windowHandler;
    }

    public HUDHandler getHudHandler()
    {
        return hudHandler;
    }
}
