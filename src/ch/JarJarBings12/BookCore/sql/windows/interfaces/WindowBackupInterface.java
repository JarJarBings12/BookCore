package ch.JarJarBings12.BookCore.sql.windows.interfaces;

import ch.JarJarBings12.BookCore.sql.windows.backup.objects.WindowHistoryObject;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 05.06.2015
 */
public interface WindowBackupInterface
{
    boolean create(String windowName);

    boolean load(String windowName, int versioon);

    boolean remove(String windowName, int version);

    boolean update(String windowName, WindowHistoryObject windowsHistoryObject);

    WindowHistoryObject getHistory(String windowName);

    boolean hasHistory(String windowName);

    boolean destroyHistory(String windowName);

}
