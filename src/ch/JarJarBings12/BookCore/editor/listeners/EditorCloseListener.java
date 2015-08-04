package ch.JarJarBings12.BookCore.editor.listeners;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.kernel.window.events.WindowCloseEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author JarJarBings12
 * @creationDate ${DATA}
 * @since 1.0.0.0
 */
public class EditorCloseListener implements Listener
{
    @EventHandler
    public void onWindowClose(WindowCloseEvent e)
    {
        if (BookCoreFramework.getEditorHandler().doPlayerEdit(e.getPlayer().getUniqueId()));
            BookCoreFramework.getEditorHandler().closeEditor(e.getPlayer().getUniqueId());
    }
}
