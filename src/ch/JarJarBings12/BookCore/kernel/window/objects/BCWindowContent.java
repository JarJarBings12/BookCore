package ch.JarJarBings12.BookCore.kernel.window.objects;

import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 11.06.2015
 */
public class BCWindowContent
{
    private String system_name;
    private ItemStack[] content;
    private Map<Integer, BCItemSlotInformation> itemSlotInformationMap;

    public BCWindowContent(String system_name, ItemStack[] content, Map<Integer, BCItemSlotInformation> itemSlotInformationMap)
    {
        this.system_name = system_name;
        this.content = content;
        this.itemSlotInformationMap = itemSlotInformationMap;
    }

    public String getSystemName()
    {
        return this.system_name;
    }

    public ItemStack[] getContent()
    {
        return this.content;
    }

    public Map<Integer, BCItemSlotInformation> getItemSlotInformationMap()
    {
        return this.itemSlotInformationMap;
    }

    public void setSystemName(String system_name)
    {
        this.system_name = system_name;
    }

    public void setContent(ItemStack[] content)
    {
        this.content = content;
    }

    public void setItemSlotInformationMap(Map<Integer, BCItemSlotInformation> itemSlotInformationMap)
    {
        this.itemSlotInformationMap = itemSlotInformationMap;
    }
}
