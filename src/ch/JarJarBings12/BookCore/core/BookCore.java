package ch.JarJarBings12.BookCore.core;

import ch.JarJarBings12.BookCore.api.BookCoreImplementation;
import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.kernel.bridges.events.InventoryClickBridge;
import ch.JarJarBings12.BookCore.kernel.bridges.events.InventoryCloseBridge;
import ch.JarJarBings12.BookCore.kernel.bridges.events.InventoryInteractBridge;
import ch.JarJarBings12.BookCore.kernel.bridges.events.InventoryOpenBridge;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 3.06.2015
 */
public class BookCore
{
    public List<BookCoreImplementation> subscribes = new ArrayList<>();

    public void setup()
    {
        System.out.println("[BookCore][Core] Setup...");
        BookCoreFramework.getConfiguration().setup();
        BookCoreFramework.getDatabaseManager().setup();
        BookCoreFramework.getThreadManager().setup();
        BookCoreFramework.getThreadManager().start();
        System.out.println("[BookCore][Core] Finish Setup...");
    }

    public void shutdown(boolean unsave)
    {
        System.out.println("[BookCore][Core] Start Shutdown...");
        BookCoreFramework.getThreadManager().stop();
        if (unsave) {
            BookCoreFramework.getWindowManager().getWindowHandler().getIndex().forEach(s -> {
                BookCoreFramework.getWindowManager().getWindowHandler().unload(s);
            });
        }
        subscribes.forEach(s -> unregister(s));
        new Main().onDisable();
    }

    public void register(BookCoreImplementation instance)
    {
        instance.onLoad();
        this.subscribes.add(instance);
        return;
    }

    public void unregister(BookCoreImplementation instance)
    {
        instance.onUnload();
        this.subscribes.remove(instance);
        return;
    }

    public void loadListeners()
    {
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(new File("plugins/BookCore/kernel.yml"));
        for (Object s : yamlConfiguration.getList("kernel.listeners.enable"))
        {
            switch (s.toString())
            {
                case "WindowClose":
                    Bukkit.getPluginManager().registerEvents(new InventoryCloseBridge(), new Main());
                case "WindowOpen":
                    Bukkit.getPluginManager().registerEvents(new InventoryOpenBridge(), new Main());
                case "WindowClick":
                    Bukkit.getPluginManager().registerEvents(new InventoryClickBridge(), new Main());
                case "WindowInteract":
                    Bukkit.getPluginManager().registerEvents(new InventoryInteractBridge(), new Main());
                default:
                    continue;
            }
        }
    }
}
