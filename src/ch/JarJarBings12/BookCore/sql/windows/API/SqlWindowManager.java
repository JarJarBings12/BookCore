package ch.JarJarBings12.BookCore.sql.windows.API;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.kernel.window.objects.BCWindow;
import ch.JarJarBings12.BookCore.sql.database.BasicSqlConnector;

import java.sql.SQLException;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 12.06.2015
 *
 *
 * @WARNING
 * Don't use this class, use this classes:
 * - {@link ch.JarJarBings12.BookCore.kernel.WindowHandler}
 * - {@link ch.JarJarBings12.BookCore.kernel.WindowManager}
 */
public class SqlWindowManager extends BasicSqlConnector
{
    public boolean create(String system_name, byte[] bytes)
    {
        try
        {
            if (bytes == null)
                throw new NullPointerException();

            createStatement().executeQuery("INSERT INTO WIN_STORAGE (SYSTEM_NAME, WIN_OBJECT) VALUES (" + system_name + "," + bytes);
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean remove(String system_name)
    {
        try
        {
            createStatement().executeQuery("REMOVE * FROM WIN_STORAGE WHERE SYSTEM_NAME=" + system_name);
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public BCWindow get(String system_name)
    {
        try
        {
            return (BCWindow) BookCoreFramework.getObjectSerializer().deserializeByteArray(openConnection().createStatement().executeQuery("SELECT * FROM WIN_STORAGE WHERE SYSTEM_NAME=" + system_name).getBytes("WIN_OBJECT"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean exists(String system_name)
    {
        try
        {
            if (createStatement().executeQuery("SELECT COUNT(*) AS total FROM WIN_STORAGE WHERE SYSTEM_NAME=" + system_name + " LIMIT 1").getInt("total") > 0)
                return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(String system_name, byte[] bytes)
    {
        try
        {
            if (bytes == null)
                throw new NullPointerException();

            createStatement().executeQuery("INSERT OR UPDATE WIN_STORAGE SET WIN_OBJECT=" + bytes + " WHERE SYSTEM_NAME=" + system_name);
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean save(String system_name, byte[] bytes)
    {
        try
        {
            if (bytes == null)
                throw new NullPointerException();

            createStatement().executeQuery("INSERT OR UPDATE WIN_STORAGE SET WIN_OBJECT=" + bytes + " WHERE SYSTEM_NAME=" + system_name);
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

}
