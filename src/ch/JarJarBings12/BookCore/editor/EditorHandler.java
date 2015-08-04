package ch.JarJarBings12.BookCore.editor;

import java.util.HashMap;
import java.util.UUID;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 19.07.2015
 */
public class EditorHandler
{
    public HashMap<UUID, Editor> openEditors = new HashMap<>();

    public boolean doPlayerEdit(UUID uuid)
    {
        return openEditors.containsKey(uuid);
    }

    public void closeEditor(UUID uuid)
    {
        openEditors.remove(uuid).close();
    }
}
