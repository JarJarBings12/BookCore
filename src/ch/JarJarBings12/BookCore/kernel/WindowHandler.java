package ch.JarJarBings12.BookCore.kernel;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.kernel.window.objects.BCWindow;
import org.bukkit.entity.Player;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 16.07.2015
 */
public class WindowHandler extends BasicWindowHandling
{

    public BCWindow getWindow(String system_name)
    {
        if (!getIndex().contains(system_name))
            if (!load(system_name))
                return null;

        return windowCache.get(system_name);
    }

    @Override
    public String getWindowNameByHolder(Player player)
    {
        if (!BookCoreFramework.getWindowManager().getSessionHandler().getPlayers().contains(player))
            return null;

        return super.getWindowByHolder(player).getSystemName();
    }
    @Override
    public BCWindow getWindowByHolder(Player player)
    {
        if (!BookCoreFramework.getWindowManager().getSessionHandler().getPlayers().contains(player))
            return null;

        return super.getWindowByHolder(player);
    }

    @Override
    public boolean save(String system_name)
    {
        if (isLoaded(system_name))
            return false;
        return super.save(system_name);
    }

    @Override
    public boolean isLoaded(String system_name)
    {
        return super.isLoaded(system_name);
    }

    @Override
    public boolean load(String system_name)
    {
        if (!exists(system_name))
            return false;

        return super.load(system_name);
    }

    @Override
    public boolean unload(String system_name)
    {
        if (!getIndex().contains(system_name))
            return false;
        return super.unload(system_name);
    }

}
