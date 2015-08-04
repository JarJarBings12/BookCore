package ch.JarJarBings12.BookCore.sql.windows.backup.objects;

import java.util.Collection;
import java.util.HashMap;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 10.06.2015
 */
public class WindowHistoryObject
{
    public WindowHistoryObject(String system_name, HashMap<Integer, byte[]> container, HashMap<Integer, Long> age)
    {
        this.system_name = system_name;
        this.container = container;
        this.age = age;
    }

    private String system_name;
    private HashMap<Integer, byte[]> container;
    private HashMap<Integer, Long> age;

    public String getSystemName()
    {
        return this.system_name;
    }

    public HashMap<Integer, byte[]> getContainer()
    {
        return this.container;
    }

    public HashMap<Integer, Long> getAge()
    {
        return this.age;
    }

    public void setContainer(HashMap<Integer, byte[]> container)
    {
        this.container = container;
    }

    public void setAge(HashMap<Integer, Long> age)
    {
        this.age = age;
    }

    public int size = container.size();

    public Collection<Integer> keys = container.keySet();

    public Collection<byte[]> values = container.values();

}
