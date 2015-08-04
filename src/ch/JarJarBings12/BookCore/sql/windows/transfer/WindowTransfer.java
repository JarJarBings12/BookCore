package ch.JarJarBings12.BookCore.sql.windows.transfer;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.sql.database.BasicSqlConnector;
import ch.JarJarBings12.BookCore.sql.windows.interfaces.WindowTransferInterface;

import java.io.*;
import java.sql.SQLException;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 04.06.2015
 */
public class WindowTransfer extends BasicSqlConnector implements WindowTransferInterface
{
    @Override
    public boolean exportBinary(String system_name, String fileName)
    {
        if (!BookCoreFramework.getConfiguration().use_window_transfer || !BookCoreFramework.getConfiguration().database_window_transfer_export_allow)
            return false;
        File exportFile = new File(new File(BookCoreFramework.getConfiguration().database_path).getParent() + "/transfer", fileName + ".bt");

        if (exportFile.exists())
            return false;

        try
        {
            byte[] bytes = BookCoreFramework.getObjectCompressor().compress(createStatement().executeQuery("SELECT * FROM WIN_STORAGE WHERE SYSTEM_NAME=" + system_name).getByte("WIN_OBJECT"));

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

    @Override
    public byte[] importBinary(String fileName)
    {
        if (!BookCoreFramework.getConfiguration().use_window_transfer || !BookCoreFramework.getConfiguration().database_window_transfer_import_allow)
            return null;
        File importFile = new File(new File(BookCoreFramework.getConfiguration().database_path).getParent() + "/transfer", fileName + ".bt");

        if (importFile.exists())
            return null;

        try
        {
            InputStream inputStream = new FileInputStream(importFile);
            int length;
            byte[] buffer = new byte[1024];
            length = inputStream.read(buffer, 0, 1024);
            return buffer;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
