package ch.JarJarBings12.BookCore.sql.database.interfaces;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 10.07.2015
 */
public interface AdvancedWindowBackupInterface
{
    boolean createCompressedBackup(String name);

    boolean loadCompressedBackup(String name);
}
