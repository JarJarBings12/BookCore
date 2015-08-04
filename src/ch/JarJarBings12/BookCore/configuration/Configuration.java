package ch.JarJarBings12.BookCore.configuration;

import ch.JarJarBings12.BookCore.core.Main;
import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.util.exceptions.IllegalPathException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 04.06.2015
 */
public class Configuration
{

    private YamlConfiguration configuration = null;
    private String path = Main.instance.getDataFolder().getParent() + "/BookCore/";
    private final String default_database_path = path+"SYSTEM/winDB.db";
    private final String default_backup_path = path+"SYSTEM/BACKUPS/";
    private final String default_transfer_path = path+"transfer/";
    /* Basic Info */
    public boolean use_database_backup = false;
    public boolean use_window_backup = false;
    public boolean use_window_transfer = false;
    public boolean use_window_compressed_transfer = false;

	/* storage */
    public String database_path = "";
    public String backup_path = "";
    public String transfer_path = "";

    public boolean database_backup_create_allow = false;
    public boolean database_backup_load_allow = false;
    public boolean database_window_backup_create_allow = false;
    public boolean database_window_backup_load_allow = false;
    public boolean database_window_transfer_export_allow = false;
    public boolean database_window_transfer_import_allow = false;
    public boolean database_window_compressed_transfer_export_allow = false;
    public boolean database_window_compressed_transfer_import_allow = false;

    public void setup()
    {
        /* Dir default paths */
        if (!new File(path).exists())
            new File(path).mkdirs();
        if (!new File(path+"SYSTEM/").exists())
            new File(path+"SYSTEM/").mkdirs();

        /* Create config.yml */
        if (!new File(path + "config.yml").exists())
        {
            if (!loadResources("resources/config.yml", "config.yml"))
                BookCoreFramework.getBookCore().shutdown(true);
        }

        /* Create kernel.yml*/
        if (!new File(path + "SYSTEM/kernel.yml").exists())
        {
            if (!loadResources("resources/kernel.yml", "SYSTEM/kernel.yml"))
                BookCoreFramework.getBookCore().shutdown(true);
        }

        /* Create read config */
        configuration = YamlConfiguration.loadConfiguration(new File(this.path + "config.yml"));

        if (!loadVariables()) {
            System.out.println("Load Variables");
            BookCoreFramework.getBookCore().shutdown(true);
        }

        if (!loadFeatures())
            BookCoreFramework.getBookCore().shutdown(true);

        writeFeatures();

        if (!configuration.getBoolean("enable"))
        {
            System.out.println("[BookCore][Setup] Please take a look over the Configuration. And verify that all is okay.");
            BookCoreFramework.getBookCore().shutdown(true);
            return;
        }

        try
        {
            if (!new File(database_path).exists())
                new File(database_path).getParentFile().mkdirs();

            if (!new File(database_path).exists())
                new File(database_path).createNewFile();
            if (!new File(backup_path).exists())
                new File(backup_path).createNewFile();
            if (!new File(transfer_path).exists())
                new File(transfer_path).createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private boolean loadResources(String resourcePath, String name)
    {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        File output = new File(this.path + name);
        try
        {
            output.createNewFile();
            OutputStream outputStream = new FileOutputStream(output);
            int length;
            byte[] byteBuffer = new byte[1024];
            while ((length = inputStream.read(byteBuffer)) > 0)
            {
                outputStream.write(byteBuffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        }
        catch (FileNotFoundException e)
        { e.printStackTrace(); }
        catch (IOException e)
        { e.printStackTrace(); }
        return false;
    }


    private boolean loadFeatures()
    {
        try
        {
            use_database_backup = configuration.getBoolean("bookcore.features.use_database_backup");
            use_window_backup = configuration.getBoolean("bookcore.features.use_window_backup");
            use_window_transfer = configuration.getBoolean("bookcore.features.features.use_window_transfer");
            use_window_compressed_transfer = configuration.contains("bookcore.features.use_window_compressed_transfer");
            database_path = configuration.getString("bookcore.storage.paths.database");
            backup_path = configuration.getString("bookcore.storage.paths.backups");
            transfer_path = configuration.getString("bookcore.storage.paths.transfer");
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    private boolean loadVariables()
    {
        try
        {

            database_path = configuration.getString("bookcore.storage.paths.database");
            backup_path = configuration.getString("bookcore.storage.paths.backups");
            transfer_path = configuration.getString("bookcore.storage.paths.transfer");
            database_backup_create_allow = configuration.getBoolean("bookcore.storage.database.backup.create.allow");
            database_backup_load_allow = configuration.getBoolean("bookcore.storage.database.backup.load.allow");
            database_window_backup_create_allow = configuration.getBoolean("bookcore.storage.database.window.backup.create.allow");
            database_window_backup_load_allow = configuration.getBoolean("bookcore.storage.database.window.backup.load.allow");
            database_window_transfer_export_allow = configuration.getBoolean("bookcore.storage.database.window.transfer.uncompressed.export.allow");
            database_window_transfer_import_allow = configuration.getBoolean("bookcore.storage.database.window.transfer.uncompressed.import.allow");
            database_window_compressed_transfer_export_allow = configuration.getBoolean("bookcore.storage.database.window.compressed.transfer.import.allow");
            database_window_compressed_transfer_import_allow = configuration.getBoolean("bookcore.storage.database.window.compressed.transfer.export.allow");

            if (database_path.contains("$default$") && database_path.contains("$plugins$"))
            {
                BookCoreFramework.getBookCore().shutdown(true);
                throw new IllegalPathException("The database path contains $default$ and $plugins$.");
            }

            if (backup_path.contains("$default$") && backup_path.contains("$plugins$"))
            {
                BookCoreFramework.getBookCore().shutdown(true);
                throw new IllegalPathException("The backup path contains $default$ and $plugins$.");
            }

            if (transfer_path.contains("$default$") && transfer_path.contains("$plugins$"))
            {
                BookCoreFramework.getBookCore().shutdown(true);
                throw new IllegalPathException("The transfer path contains $default$ and $plugins$.");
            }

            database_path.replace("$default$", default_database_path).replace("$plugins$", Main.instance.getDataFolder().getParent());
            backup_path.replace("$default$", default_backup_path).replace("$plugins$", Main.instance.getDataFolder().getParent());
            transfer_path.replace("$default$", default_transfer_path).replace("$plugins$", Main.instance.getDataFolder().getParent());
            System.out.println("wedfwfewrfwerfwerfrewfwerf" + database_path);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    private void writeFeatures()
    {
        System.out.println("[BookCore][SETUP] Features options: ");
        System.out.println("[BookCore][SETUP] Use database backup: " + use_database_backup);
        System.out.println("[BookCore][SETUP] Use Window backup: " + use_window_backup);
        System.out.println("[BookCore][SETUP] Use Window binary transfer: " + use_window_transfer);
        System.out.println("[BookCore][SETUP] Use Window compressed transfer: " + use_window_compressed_transfer);

    }
}
