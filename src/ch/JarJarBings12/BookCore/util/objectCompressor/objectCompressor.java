package ch.JarJarBings12.BookCore.util.objectCompressor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 12.06.2015
 */
public class objectCompressor implements compressor
{

    @Override
    public byte[] compress(Object object)
    {
        try
        {
            ByteArrayOutputStream byteArrayOutputStream = null;
            new ObjectOutputStream(new GZIPOutputStream(byteArrayOutputStream)).writeObject(object);
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] decompress(InputStream inputStream)
    {
        try
        {
            byte[] buffer = new byte[1024];
            new GZIPInputStream(inputStream).read(buffer, 0, 1024);
            return buffer;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
