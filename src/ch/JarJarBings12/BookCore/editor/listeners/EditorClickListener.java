package ch.JarJarBings12.BookCore.editor.listeners;

import ch.JarJarBings12.BookCore.editor.Editor;
import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.kernel.window.events.WindowClickEvent;
import ch.JarJarBings12.BookCore.kernel.window.objects.BCWindow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author JarJarBings12
 * @creationDate ${DATA}
 * @since 1.0.0.0
 */
public class EditorClickListener implements Listener
{
    @EventHandler
    public void onWindowClick(WindowClickEvent e)
    {
        if (!BookCoreFramework.getEditorHandler().doPlayerEdit(e.getPlayer().getUniqueId()))
            return;

        Editor editor = BookCoreFramework.getEditorHandler().openEditors.get(e.getPlayer().getUniqueId());

        if (e.getEvent().getClickedInventory().getName().equals(e))
        {

        }
        else
        {
            if (e.getEvent().getRawSlot() == 0)
            {
                BCWindow window = e.getDisplaySession().getWindow();
                window.getContent().setContent(e.getEvent().getClickedInventory().getContents());
                BookCoreFramework.getWindowManager().getWindowHandler().update(e.getSystemName(), window);
            }
            else if (e.getEvent().getRawSlot() == 1)
            {

            }

        }
    }
}
