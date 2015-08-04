package ch.JarJarBings12.BookCore.kernel.UI.handlers;

import ch.JarJarBings12.BookCore.kernel.UI.UIDisplaySession;
import org.bukkit.entity.Player;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.*;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 26.06.2015
 */
public class SessionHandler
{

    private Random rnd = new Random();
    private final char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                                  'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
                                  'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
                                  'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    /* Storage */
    private HashMap<String, Player> sessionPlayer = new HashMap<>();
    private HashMap<Player, String> playerSession = new HashMap<>();
    private HashMap<String, UIDisplaySession> sessionDisplay = new HashMap<>();

    public Collection<Player> getPlayers()
    {
        return playerSession.keySet();
    }

    public Collection<String> getSessions()
    {
        return playerSession.values();
    }

    public Collection<UIDisplaySession> getDisplaySessions()
    {
        return sessionDisplay.values();
    }

    /* Basic un/register Actions */
    public void register(UIDisplaySession displaySession)
    {

        displaySession.getPlayers().forEach(current ->
        {
            String session = displaySession.getSessions().get(current);
            sessionPlayer.put(session, current);
            playerSession.put(current, session);
            sessionDisplay.put(session, displaySession);
        });
        return;
    }

    public void unregister(String session)
    {
        UIDisplaySession displaySession = sessionDisplay.get(session);

        displaySession.getSessions().remove(session);
        displaySession.getPlayers().remove(sessionPlayer.get(session));
        update(displaySession);
        return;
    }

    public void unregister(Player player)
    {
        UIDisplaySession displaySession = sessionDisplay.get(playerSession.get(player));

        displaySession.getSessions().remove(playerSession.get(player));
        displaySession.getPlayers().remove(player);
        update(displaySession);
        return;
    }

    public Player getPlayer(String session)
    {
        return sessionPlayer.get(session);
    }

    public String getSession(Player player)
    {
        return playerSession.get(player);
    }

    public UIDisplaySession getDisplaySession(String session)
    {
        return sessionDisplay.get(session);
    }

    public void update(UIDisplaySession displaySession)
    {
        displaySession.getSessions().values().forEach(s ->
        {
            sessionDisplay.remove(s);
            sessionDisplay.put(s, displaySession);
        });
        return;
    }

    public String genSessionID()
    {
        // (6*62)^3 that makes 19150131456 different compilations possible.
        try
        {
            MaskFormatter maskFormatter = new MaskFormatter("sid=HHHHHH-HHHHHH-HHHHHH");
            return maskFormatter.valueToString(random(18));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public String random(int length)
    {
        StringBuilder stringBuilder = new StringBuilder("");

        for (int i = 0; i < length; i++)
        {
            stringBuilder.append(chars[rnd.nextInt(62)]);
        }
        return stringBuilder.toString();
    }
}
