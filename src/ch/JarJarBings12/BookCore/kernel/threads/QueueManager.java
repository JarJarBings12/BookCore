package ch.JarJarBings12.BookCore.kernel.threads;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 09.07.2015
 */
public class QueueManager
{
    private Queue<SessionEvent> sessionEventsQueue = new ConcurrentLinkedQueue<>();

    public void add(SessionEvent sessionEvent)
    {
        this.sessionEventsQueue.add(sessionEvent);
    }

    public SessionEvent next()
    {
        return this.sessionEventsQueue.poll();
    }

    public boolean isEmpty()
    {
        return this.sessionEventsQueue.isEmpty();
    }

    public boolean hasNext()
    {
        return this.sessionEventsQueue.isEmpty();
    }

}
