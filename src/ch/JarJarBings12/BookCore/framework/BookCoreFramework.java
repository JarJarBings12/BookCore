package ch.JarJarBings12.BookCore.framework;

import ch.JarJarBings12.BookCore.configuration.Configuration;
import ch.JarJarBings12.BookCore.core.BookCore;
import ch.JarJarBings12.BookCore.editor.EditorHandler;
import ch.JarJarBings12.BookCore.kernel.WindowManager;
import ch.JarJarBings12.BookCore.kernel.threads.ThreadManager;
import ch.JarJarBings12.BookCore.sql.database.backup.SqlBackup;
import ch.JarJarBings12.BookCore.sql.database.manager.DatabaseManager;
import ch.JarJarBings12.BookCore.util.objectCompressor.objectCompressor;
import ch.JarJarBings12.BookCore.util.objectSerializion.objectSerialize;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 04.06.2015
 */
public class BookCoreFramework
{
    private static BookCore bookCore = null;
    private static Configuration configuration = null;
    private static objectSerialize objectSerialize = null;
    private static objectCompressor objectCompressor = null;
    private static DatabaseManager databaseManager = null;
    private static SqlBackup sqlBackup = null;
    private static WindowManager windowManager = null;
    private static ThreadManager threadManager = null;
    private static EditorHandler editorHandler = null;

    static
    {
        bookCore = new BookCore();
        configuration = new Configuration();
        objectSerialize = new objectSerialize();
        objectCompressor = new objectCompressor();
        databaseManager = new DatabaseManager();
        sqlBackup = new SqlBackup();
        windowManager = new WindowManager();
        threadManager = new ThreadManager();
        editorHandler = new EditorHandler();
    }

    public static BookCore getBookCore()
    {
        return bookCore;
    }

    public static Configuration getConfiguration()
    {
        return configuration;
    }

    public static objectSerialize getObjectSerializer()
    {
        return objectSerialize;
    }

    public static objectCompressor getObjectCompressor()
    {
        return objectCompressor;
    }

    public static DatabaseManager getDatabaseManager()
    {
        return databaseManager;
    }

    public static SqlBackup getSqlBackup()
    {
        return sqlBackup;
    }

    public static WindowManager getWindowManager()
    {
        return windowManager;
    }

    public static ThreadManager getThreadManager()
    {
        return threadManager;
    }

    public static EditorHandler getEditorHandler()
    {
        return editorHandler;
    }
}
