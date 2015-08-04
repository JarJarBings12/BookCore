package ch.JarJarBings12.BookCore.util.objectCompressor;

import java.io.InputStream;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 12.06.2015
 */
public interface compressor
{
    byte[] compress(Object object);

    byte[] decompress(InputStream inputStream);

}
