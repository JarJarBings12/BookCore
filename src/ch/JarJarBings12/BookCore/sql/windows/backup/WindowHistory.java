package ch.JarJarBings12.BookCore.sql.windows.backup;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.kernel.window.objects.BCWindow;
import ch.JarJarBings12.BookCore.sql.windows.API.SqlWindowManager;
import ch.JarJarBings12.BookCore.sql.windows.backup.objects.WindowHistoryObject;
import ch.JarJarBings12.BookCore.sql.windows.interfaces.WindowBackupInterface;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 05.06.2015
 *
 *
 * @WARNING
 * Don't use this class, use this classes:
 * - {@link ch.JarJarBings12.BookCore.kernel.WindowHandler}
 * - {@link ch.JarJarBings12.BookCore.kernel.WindowManager}
 */
public class WindowHistory extends SqlWindowManager implements WindowBackupInterface
{

    @Override
    public boolean create(String system_name)
    {
        if (!BookCoreFramework.getConfiguration().use_window_backup || !BookCoreFramework.getConfiguration().database_window_backup_create_allow)
            return false;
        if (!BookCoreFramework.getWindowManager().getWindowHandler().exists(system_name))
            return false;

        if (hasHistory(system_name))
        {
            try
            {
                WindowHistoryObject windowsHistoryObject = (WindowHistoryObject) BookCoreFramework.getObjectSerializer().deserializeByteArray(createStatement().executeQuery("SELECT * FROM WIN_BACKUP WHERE NAME=" + system_name).getBytes("HISTORY"));
                HashMap<Integer, byte[]> tempContainer = windowsHistoryObject.getContainer();
                HashMap<Integer, Long> tempAge = windowsHistoryObject.getAge();
                tempContainer.put(windowsHistoryObject.size, createStatement().executeQuery("SELECT * FROM WIN_STORAGE WHERE NAME=" + system_name).getBytes("WIN_OBJECT"));
                tempAge.put(windowsHistoryObject.size, System.currentTimeMillis());
                windowsHistoryObject.setContainer(tempContainer);
                windowsHistoryObject.setAge(tempAge);
                return update(system_name, windowsHistoryObject);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean load(String system_name, int version)
    {
        if (!BookCoreFramework.getConfiguration().use_window_backup || !BookCoreFramework.getConfiguration().database_window_backup_load_allow)
            return false;
        if (!hasHistory(system_name))
            return false;
        try
        {
            WindowHistoryObject windowHistoryObject = (WindowHistoryObject) BookCoreFramework.getObjectSerializer().deserializeByteArray(createStatement().executeQuery("SELECT * FROM WIN_BACKUP WHERE NAME=" + system_name).getBytes("HISTORY"));

            if (!windowHistoryObject.keys.contains(version))
                return false;

            BCWindow window = (BCWindow) BookCoreFramework.getObjectSerializer().deserializeByteArray(windowHistoryObject.getContainer().get(version));
            BookCoreFramework.getWindowManager().getWindowHandler().unregister(system_name);
            super.update(system_name, BookCoreFramework.getObjectSerializer().serializeObject(window));

            System.out.printf("[BookCore][Win_Backup] Load backup for %s from %s", system_name, new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss").toString());
        }
        catch (SQLException e)
        {
            System.out.printf("[BookCore][Win_Backup] ---------------- [ERROR] ----------------");
            System.out.printf("[BookCore][Win_Backup] Can't load backup for %s from %s", system_name, new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss"));
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean remove(String system_name, int version)
    {
        if (!hasHistory(system_name))
            return false;

        WindowHistoryObject windowsHistoryObject = null;

        try
        {
            windowsHistoryObject = (WindowHistoryObject) BookCoreFramework.getObjectSerializer().deserializeByteArray(createStatement().executeQuery("SELECT * FROM WIN_BACKUP WHERE NAME=" + system_name).getBytes("HISTORY"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        if (!windowsHistoryObject.getContainer().containsKey(version))
            return false;

        windowsHistoryObject.getContainer().remove(version);
        windowsHistoryObject.getAge().remove(version);
        HashMap<Integer, byte[]> tempContainer = windowsHistoryObject.getContainer();
        HashMap<Integer, Long> tempAge = windowsHistoryObject.getAge();
        windowsHistoryObject.getContainer().keySet().
                forEach(integer ->
                {
                    if (integer > version)
                    {
                        tempContainer.put(integer - 1, tempContainer.remove(integer));
                        tempAge.put(integer - 1, tempAge.remove(integer));
                    }
                });
        windowsHistoryObject.setContainer(tempContainer);
        windowsHistoryObject.setAge(tempAge);
        return update(system_name, windowsHistoryObject);
    }

    @Override
    public boolean update(String system_name, WindowHistoryObject windowsHistoryObject)
    {
        if (!hasHistory(system_name))
            return false;
        try
        {
            createStatement().executeQuery("UPDATE WIN_BACKUP SET HISTORY=" + BookCoreFramework.getObjectSerializer().serializeObject(windowsHistoryObject) + "WHERE SYSTEM_NAME=" + system_name);
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public WindowHistoryObject getHistory(String system_name)
    {
        if (!hasHistory(system_name))
            return null;

        byte[] bytes = null;
        try
        {
            bytes = createStatement().executeQuery("SELECT * FROM WIN_BACKUP WHERE SYSTEM_NAME=" + system_name).getBytes("HISTOROY");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (WindowHistoryObject) BookCoreFramework.getObjectSerializer().deserializeByteArray(bytes);
    }

    @Override
    public boolean hasHistory(String system_name)
    {
        try
        {
            if (createStatement().executeQuery("SELECT COUNT(*) FROM WIN_BACKUP WHERE SYSTEM_NAME=" + system_name).getInt("total") > 0)
                return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean destroyHistory(String system_name)
    {
        try
        {
            createStatement().executeQuery("REMOVE * FROM WIN_BACKUP WHERE SYSTEM_NAME=" + system_name);
            return false;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return true;
    }

}
