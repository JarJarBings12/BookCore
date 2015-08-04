package ch.JarJarBings12.BookCore.editor;

import ch.JarJarBings12.BookCore.kernel.window.objects.BCWindow;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 16.07.2015
 */
public class Editor
{
    private Player player;
    private boolean open = false;
    private BCWindow window;
    private ItemStack[] hotbar = new ItemStack[8];
    private final ItemStack bClose = new ItemStack(Material.WOOL, 1, (byte)14);
    private final ItemStack bSave = new ItemStack(Material.WOOL, 1, (byte)13);
    private final ItemStack bReset = new ItemStack(Material.WOOL, 1, (byte)7);
    private final ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, (byte)0);
    private Inventory inventory = null;

    public Editor(Player player, BCWindow window)
    {
        this.player = player;
        this.window = window;
        this.inventory = Bukkit.createInventory(null, window.getContent().getContent().length, "EDIT - " + window.getSystemName());
        this.inventory.setContents(window.getContent().getContent());
    }

    public void display()
    {
        if (open)
            return;
        player.openInventory(inventory);
        a();
        b();

        return;
    }

    public void close()
    {
        if (!open)
            return;
        c();
        return;
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }
    /**
     * Backup Player hot bar
     */
    private void a()
    {
        List<ItemStack> items = new ArrayList<>();

        for (int i = window.getContent().getContent().length+27; i < window.getContent().getContent().length+36; i++)
        {
            items.add(window.getContent().getContent()[i]);
        }

        hotbar = (ItemStack[])items.toArray();
        return;
    }

    /**
     * Set hot bar
     *
     * ##*****#*
     */
    private void b()
    {
        int startIndex = window.getContent().getContent().length+27;
        startIndex++;
        player.getInventory().setItem(startIndex, bSave);
        startIndex++;
        player.getInventory().setItem(startIndex, bReset);
        startIndex = startIndex + 5;
        player.getInventory().setItem(startIndex, bClose);
        return;
    }

    private void c()
    {
        for (int i = 0; i < 9; i++)
        {
            player.getInventory().setItem(i, hotbar[i]);
        }
    }

    private void d()
    {

    }
}
