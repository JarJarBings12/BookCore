package ch.JarJarBings12.BookCore.sql.database;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.sql.database.interfaces.DatabaseInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

 /**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 14.06.2015
 */
public class BasicSqlConnector implements DatabaseInterface
{
    @Override
    public Connection openConnection()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:" + BookCoreFramework.getConfiguration().database_path);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Statement createStatement()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:" + BookCoreFramework.getConfiguration().database_path).createStatement();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PreparedStatement createPreparedStatement(String query)
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:" + BookCoreFramework.getConfiguration().database_path).prepareStatement(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
