package ch.JarJarBings12.BookCore.sql.database.backup;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.sql.database.interfaces.AdvancedWindowBackupInterface;
import ch.JarJarBings12.BookCore.sql.database.interfaces.DatabaseBackupInterface;
import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 04.06.2015
 */
public class SqlBackup implements DatabaseBackupInterface, AdvancedWindowBackupInterface
{

    @Override
    public boolean createBackup(String name)
    {
        if (!BookCoreFramework.getConfiguration().use_database_backup || !BookCoreFramework.getConfiguration().database_backup_create_allow)
            return false;
        try
        {
            File backupFolder = new File(new File(BookCoreFramework.getConfiguration().database_path).getParent() + "/backups/" + name + ".sbf");

            if (backupFolder.exists())
                return false;

            new ObjectOutputStream(new FileOutputStream(backupFolder)).write(BookCoreFramework.getObjectSerializer().serializeObject(new File(BookCoreFramework.getConfiguration().database_path)));

            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean loadBackup(String name)
    {
        if (!BookCoreFramework.getConfiguration().use_database_backup || !BookCoreFramework.getConfiguration().database_backup_load_allow)
            return false;
        File backupFile = new File(new File(BookCoreFramework.getConfiguration().database_path).getParent() + "/backups/" + name + ".sbf");

        if (!backupFile.exists())
            return false;

        try
        {
            byte[] bytes = new byte[(int) backupFile.length()];
            new FileInputStream(backupFile).read(bytes);
            FileUtils.copyFile((File) BookCoreFramework.getObjectSerializer().deserializeByteArray(bytes), new File(BookCoreFramework.getConfiguration().database_path));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean createCompressedBackup(String name)
    {
        if (!BookCoreFramework.getConfiguration().use_database_backup || !BookCoreFramework.getConfiguration().database_backup_create_allow)
            return false;
        File backupFolder = new File(new File(BookCoreFramework.getConfiguration().database_path).getParent() + "/backups/" + name + ".csbf");

        if (backupFolder.exists())
            return false;

        try
        {
            new ObjectOutputStream(new FileOutputStream(backupFolder)).write(BookCoreFramework.getObjectCompressor().compress(new File(BookCoreFramework.getConfiguration().database_path)));
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean loadCompressedBackup(String name)
    {
        if (!BookCoreFramework.getConfiguration().use_database_backup || !BookCoreFramework.getConfiguration().database_backup_load_allow)
            return false;
        File backupFile = new File(new File(BookCoreFramework.getConfiguration().database_path).getParent() + "/backups/" + name + ".csbf");

        if (!backupFile.exists())
            return false;

        try
        {
            FileInputStream fileInputStream = new FileInputStream(backupFile);
            FileUtils.copyFile((File) BookCoreFramework.getObjectSerializer().deserializeByteArray(BookCoreFramework.getObjectCompressor().decompress(new FileInputStream(backupFile))), new File(BookCoreFramework.getConfiguration().database_path));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
