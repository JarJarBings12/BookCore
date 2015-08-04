package ch.JarJarBings12.BookCore.kernel.threads;

import ch.JarJarBings12.BookCore.framework.BookCoreFramework;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 09.07.2015
 */
public class QueueHandler extends BookThread
{
    @Override
    public void beat()
    {
        if (!BookCoreFramework.getThreadManager().getQueueManager().isEmpty())
            BookCoreFramework.getBookCore().subscribes.forEach(s -> s.call(BookCoreFramework.getThreadManager().getQueueManager().next()));
    }
}
