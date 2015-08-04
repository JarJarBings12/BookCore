package ch.JarJarBings12.BookCore.util.objectSerializion;

import java.io.*;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 04.06.2015
 */
public class objectSerialize implements serialize
{
    @Override
    public byte[] serializeObject(Object object)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.flush();
            baos.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    @Override
    public Object deserializeByteArray(byte[] bytes)
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try
        {
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
