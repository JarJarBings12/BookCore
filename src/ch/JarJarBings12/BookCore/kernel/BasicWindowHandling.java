package ch.JarJarBings12.BookCore.kernel;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.kernel.window.objects.BCItemSlotInformation;
import ch.JarJarBings12.BookCore.kernel.window.objects.BCWindow;
import ch.JarJarBings12.BookCore.kernel.window.objects.BCWindowContent;
import ch.JarJarBings12.BookCore.sql.windows.API.SqlWindowManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
//TODO Add Documentation
/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 16.06.2015
 */
public class BasicWindowHandling extends SqlWindowManager
{
    private Collection<String> index = new HashSet<>();

    public HashMap<String, BCWindow> windowCache = new HashMap<>();
    public HashMap<String, Long> windowLastUse = new HashMap<>();

    public boolean create(String system_name, int lines)
    {
        // Create Window Object
        BCWindow window = new BCWindow(system_name, system_name, lines, new BCWindowContent(system_name, new ItemStack[]{}, new HashMap<Integer, BCItemSlotInformation>()));
        register(system_name, window);

        // Create Backup
        if (BookCoreFramework.getWindowManager().getWindowHistory().hasHistory(system_name))
            BookCoreFramework.getWindowManager().getWindowHistory().destroyHistory(system_name);

        BookCoreFramework.getWindowManager().getWindowHistory().create(system_name);
        return index.contains(system_name);
    }

    @Override
    public boolean remove(String system_name)
    {
        if (!index.contains(system_name) && !windowCache.containsKey(system_name) && !windowLastUse.containsKey(system_name))
            return false;

		/* Destroy all Backups and create a new */
        if (BookCoreFramework.getWindowManager().getWindowHistory().hasHistory(system_name))
        {
            BookCoreFramework.getWindowManager().getWindowHistory().destroyHistory(system_name);
            BookCoreFramework.getWindowManager().getWindowHistory().create(system_name);
        }

		/* Send message to all Inventory holder */

        BookCoreFramework.getWindowManager().getSessionHandler().getDisplaySessions().forEach(displaySession ->
        {
            if (displaySession.getWindow().getSystemName().equals(system_name))
                displaySession.close();
        });

        unregister(system_name);

		/* Remove the Window from the Sql WIN_STORAGE table */
        return super.remove(system_name);
    }

    public boolean isLoaded(String system_name)
    {
        return getIndex().contains(system_name);
    }

    public boolean load(String system_name)
    {
        register(system_name, super.get(system_name));
        return index.contains(system_name);
    }

    /**
     * @since 1.0.0.0
     * @info Please use the version in the {@link ch.JarJarBings12.BookCore.kernel.WindowHandler#unload(String)} class.
     *
     * @WARNING If you use this version of the method and the Window didn't exists Book Core throw a Exception.
     * @param system_name
     * @return
     */
    public boolean unload(String system_name)
    {
        super.save(system_name, BookCoreFramework.getObjectSerializer().serializeObject(windowCache.get(system_name)));

        BookCoreFramework.getWindowManager().getSessionHandler().getDisplaySessions().forEach(displaySession -> {
            if (displaySession.getWindow().getSystemName() == system_name)
                displaySession.close();
        });

        unregister(system_name);
        return !index.contains(system_name);
    }

    public boolean save(String system_name)
    {
        return super.save(system_name, BookCoreFramework.getObjectSerializer().serializeObject(windowCache.get(system_name)));
    }

    /**
     * @since 1.0.0.0
     * @info Please use this version {@link ch.JarJarBings12.BookCore.kernel.WindowHandler#register(String, BCWindow)}
     *
     * @WARNING If you run this version of the Method Book Core overwrite the existing in the Cache when it exists! \n Please use this version {@link ch.JarJarBings12.BookCore.kernel.WindowHandler#unload(String)}
     * @param system_name
     * @param window
     */
    public void register(String system_name, BCWindow window)
    {
        if (index.contains(system_name))
            return;

        index.add(system_name);
        windowCache.put(system_name, window);
        windowLastUse.put(system_name, System.currentTimeMillis());
        return;
    }

    /**
     * @WARNING When you run this method Book Core unload this Window and all changes are lost! \n If you want to save this Window run the "unload" method in {@link WindowHandler}.
     * @param system_name
     */
    public void unregister(String system_name)
    {
        if (!index.contains(system_name))
            return;

        index.remove(system_name);
        windowCache.remove(system_name);
        windowLastUse.remove(system_name);
        return;
    }

    /**
     *
     * @param player
     * @return
     */
    public boolean isWindowHolder(Player player)
    {
        return BookCoreFramework.getWindowManager().getSessionHandler().getPlayers().contains(player);
    }

    /**
     *
     * @prarm session
     * @return
     */
    public boolean isSessionOpen(String session)
    {
        return BookCoreFramework.getWindowManager().getSessionHandler().getSessions().contains(session);
    }

    /**
     *
     * @param player
     * @return
     */
    public String getWindowNameByHolder(Player player)
    {
        return BookCoreFramework.getWindowManager().getSessionHandler().getDisplaySession(BookCoreFramework.getWindowManager().getSessionHandler().getSession(player)).getWindow().getSystemName();
    }

    /**
     *
     * @param player
     * @return
     */
    public BCWindow getWindowByHolder(Player player)
    {
        return BookCoreFramework.getWindowManager().getSessionHandler().getDisplaySession(BookCoreFramework.getWindowManager().getSessionHandler().getSession(player)).getWindow();
    }

    public Collection<String> getIndex()
    {
        return this.index;
    }

    public boolean update(String system_name, BCWindow window)
    {
        if (!getIndex().contains(system_name))
            return false;

        this.windowCache.put(system_name, window);

        BookCoreFramework.getWindowManager().getSessionHandler().getDisplaySessions().forEach(s -> {
            if (s.getWindow().getSystemName() == system_name) {
                s.update(window);
            }

        });
        return super.update(system_name, BookCoreFramework.getObjectSerializer().serializeObject(window));
    }
}
