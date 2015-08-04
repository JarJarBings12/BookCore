package ch.JarJarBings12.BookCore.core;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 11.07.2015
 */
public class Main extends JavaPlugin
{
    public static Main instance;

    @Override
    public void onEnable()
    {
        instance = this;
        BookCoreFramework.getBookCore().setup();
    }

    @Override
    public void onDisable()
    {
        System.out.println("[BookCore][Core] Finish Shutdown...");
    }
}
