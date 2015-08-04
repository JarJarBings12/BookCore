package ch.JarJarBings12.BookCore.sql.windows.transfer;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.sql.database.BasicSqlConnector;

import java.io.*;
import java.sql.SQLException;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 14.06.2015
 */
public class AdvancedWindowTransfer extends BasicSqlConnector
{
    public boolean exportPressedBinary(String system_name, String fileName)
    {
        if (!BookCoreFramework.getConfiguration().use_window_compressed_transfer || !BookCoreFramework.getConfiguration().database_window_compressed_transfer_export_allow)
            return false;
        File exportFile = new File(new File(BookCoreFramework.getConfiguration().database_path).getParent() + "/transfer", fileName + "btp");

        if (exportFile.exists())
            return false;

        try
        {
            byte[] bytes = BookCoreFramework.getObjectCompressor().compress(createStatement().executeQuery("SELECT * FROM WIN_STORAGE WHERE SYSTEM_NAME=" + system_name).getByte("WIN_OBJECT"));
            bytes = BookCoreFramework.getObjectCompressor().compress(bytes);

            FileOutputStream fileOutputStream = new FileOutputStream(exportFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.write(bytes);
            objectOutputStream.flush();
            objectOutputStream.close();
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
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

    public byte[] importPressedBinary(String fileName)
    {
        if (!BookCoreFramework.getConfiguration().use_window_compressed_transfer || !BookCoreFramework.getConfiguration().database_window_compressed_transfer_import_allow)
            return null;
        File importFile = new File(new File(BookCoreFramework.getConfiguration().database_path).getParent() + "/transfer", fileName + ".btp");

        if (!importFile.exists())
            return null;

        try
        {
            return BookCoreFramework.getObjectCompressor().decompress(new FileInputStream(importFile));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
