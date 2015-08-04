package ch.JarJarBings12.BookCore.sql.database.interfaces;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 04.06.2015
 */
public interface DatabaseBackupInterface
{
    boolean createBackup(String name);

    boolean loadBackup(String name);

}
