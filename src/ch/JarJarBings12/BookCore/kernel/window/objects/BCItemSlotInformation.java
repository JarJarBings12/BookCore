package ch.JarJarBings12.BookCore.kernel.window.objects;

import ch.JarJarBings12.BookCore.kernel.window.util.SlotType;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 11.06.2015
 */
public class BCItemSlotInformation
{
    private int rawSlot;
    private SlotType slotType;
    private BCSlotAction action;

    public BCItemSlotInformation(int rawSlot, SlotType slotType, BCSlotAction action)
    {
        this.rawSlot = rawSlot;
        this.slotType = slotType;
        this.action = action;
    }

    public int getRawSlot()
    {
        return this.rawSlot;
    }

    public SlotType getSlotType()
    {
        return this.slotType;
    }

    public void setSlotType(SlotType slotType)
    {
        this.slotType = slotType;
    }

    public BCSlotAction getAction()
    {
        return this.action;
    }

    public void setAction(BCSlotAction action)
    {
        this.action = action;
    }
}
