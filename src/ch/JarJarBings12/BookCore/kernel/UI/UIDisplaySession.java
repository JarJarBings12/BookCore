package ch.JarJarBings12.BookCore.kernel.UI;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;
import ch.JarJarBings12.BookCore.kernel.window.objects.BCWindow;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 26.06.2015
 */
public class UIDisplaySession
{
    private List<Player> players;
    private BCWindow window;
    private Inventory inventory;
    private HashMap<Player, String> sessions;

    public UIDisplaySession(Player player, BCWindow window)
    {
        this.players = singlePlayerToList(player);
        this.window = window;
        this.inventory = load();
        this.sessions = singleSessionToMap(player, BookCoreFramework.getWindowManager().getSessionHandler().genSessionID());
    }

    public UIDisplaySession(List<Player> players, BCWindow window)
    {
        this.players = players;
        this.window = window;
        this.inventory = load();
        this.sessions = sessionsForList(players);
    }

    public void display()
    {
        players.forEach(current -> current.openInventory(inventory));
        BookCoreFramework.getWindowManager().getSessionHandler().register(this);
        return;
    }

    public void close()
    {
        players.forEach(current -> BookCoreFramework.getWindowManager().getSessionHandler().unregister(current));
        return;
    }

    public List<Player> getPlayers()
    {
        return players;
    }

    public BCWindow getWindow()
    {
        return window;
    }

    public HashMap<Player, String> getSessions()
    {
        return sessions;
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public Inventory getOriginalInventory()
    {
        return load();
    }

    public Inventory load()
    {
        Inventory tempInventory = Bukkit.createInventory(null, window.getHigh(), window.getDisplayName());
        tempInventory.setContents(window.getContent().getContent());
        return tempInventory;
    }

    public void update(BCWindow window)
    {
        this.window = window;
        load();
        players.forEach(p -> {
            p.closeInventory();
            p.openInventory(inventory);
        });
        load();
    }

    private List<Player> singlePlayerToList(Player player)
    {
        List<Player> tempList = new ArrayList<>();
        tempList.add(player);
        return tempList;
    }

    private HashMap<Player, String> singleSessionToMap(Player player, String session)
    {
        HashMap<Player, String> tempMap = new HashMap<>();
        tempMap.put(player, session);
        return tempMap;
    }

    private HashMap<Player, String> sessionsForList(List<Player> players)
    {
        HashMap<Player, String> sessions = new HashMap<Player, String>();
        players.forEach(player -> {
            sessions.put(player, BookCoreFramework.getWindowManager().getSessionHandler().genSessionID());
        });
        return sessions;
    }
}
