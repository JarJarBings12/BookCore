package ch.JarJarBings12.BookCore.kernel.window.objects;

/**
 * Created by tobias on 11.06.2015.
 */
/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 11.06.2015
 */
public class BCWindow
{
    private String system_name;
    private String display_name;
    private int high;
    private BCWindowContent content;

    public BCWindow(String system_name, String display_name, int high, BCWindowContent content)
    {
        this.system_name = system_name;
        this.display_name = display_name;
        this.high = (high < 6) ? high : 1;
        this.content = content;
    }

    public String getSystemName()
    {
        return this.system_name;
    }

    public String getDisplayName()
    {
        return this.display_name;
    }

    public int getHigh()
    {
        return this.high;
    }

    public BCWindowContent getContent()
    {
        return this.content;
    }

    public void setSystemName(String system_name)
    {
        this.system_name = system_name;
    }

    public void setDisplayName(String display_name)
    {
        this.display_name = display_name;
    }

    public void setHigh(int high)
    {
        this.high = (high < 6) ? high : this.high;
    }

    public void setContent(BCWindowContent content)
    {
        this.content = content;
    }
}
