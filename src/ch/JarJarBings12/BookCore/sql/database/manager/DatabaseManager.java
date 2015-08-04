package ch.JarJarBings12.BookCore.sql.database.manager;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.sql.database.BasicSqlConnector;
import ch.JarJarBings12.BookCore.sql.database.backup.SqlBackup;
import org.bukkit.Bukkit;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 06.06.2015
 */
public class DatabaseManager extends BasicSqlConnector
{
    public boolean setup()
    {
        if (!new File(BookCoreFramework.getConfiguration().database_path).exists())
        {
            Connection c = null;
            Statement statement = null;

            try
            {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:" + BookCoreFramework.getConfiguration().database_path);
                statement = c.createStatement();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS WIN_BACKUP (SYSTEM_NAME VARCHAR(32) NOT NULL UNIQUE, HISTORY BINARY NOT NULL)");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS WIN_CACHE (SYSTEM_NAME VARCHAR(32) NOT NULL UNIQUE, WIN_CACHE_OBJECT BINARY NOT NULL)");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS WIN_STORAGE (SYSTEM_NAME VARCHAR(32) NOT NULL UNIQUE, WIN_OBJECT BINARY NOT NULL");
                c.close();
            }
            catch (Exception e)
            {
                Bukkit.getLogger().log(Level.WARNING, "Can't setup Sql Database!");
                BookCoreFramework.getBookCore().shutdown(true);
                e.printStackTrace();
            }
        }
        return true;
    }

    public SqlBackup backupManager()
    {
        return new SqlBackup();
    }
}