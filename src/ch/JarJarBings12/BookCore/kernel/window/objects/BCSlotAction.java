package ch.JarJarBings12.BookCore.kernel.window.objects;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 11.06.2015
 */
public class BCSlotAction
{
    private String permission;
    private String action;

    public BCSlotAction(String permission, String action)
    {
        this.permission = permission;
        this.action = action;
    }

    public String getPermission()
    {
        return this.permission;
    }

    public void setPermission(String permission)
    {
        this.permission = permission;
    }

    public String getAction()
    {
        return this.action;
    }

    public void  setAction(String action)
    {
        this.action = action;
    }
}
