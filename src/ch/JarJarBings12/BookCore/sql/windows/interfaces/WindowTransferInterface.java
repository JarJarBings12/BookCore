package ch.JarJarBings12.BookCore.sql.windows.interfaces;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 04.06.2015
 */
public interface WindowTransferInterface
{
    boolean exportBinary(String system_name, String fileName);

    byte[] importBinary(String fileName);
}
