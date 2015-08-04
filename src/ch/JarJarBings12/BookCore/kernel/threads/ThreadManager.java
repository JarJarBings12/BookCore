package ch.JarJarBings12.BookCore.kernel.threads;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 09.07.2015
 */
public class ThreadManager
{
    private QueueManager var1;

    private List<QueueHandler> var2 = new ArrayList<>();

    public void setup()
    {
        this.var1 = new QueueManager();
        return;
    }

    public QueueManager getQueueManager()
    {
        return this.var1;
    }

    public void start()
    {
        QueueHandler queueHandler = new QueueHandler();
        queueHandler.setName("BookCore-Session-Thread-" + var2.size());
        queueHandler.start();
        var2.add(queueHandler);
        System.out.println("[BookCore][ThreadManager] Start new Thread.");
        System.out.println("[BookCore][ThreadManager] Now are " + var2.size() + " Threads running.");
        return;
    }


    public void start(int amount)
    {
        for (int i = 0; i < amount; i++)
            start();

        return;
    }

    public void stop()
    {
        var2.forEach(t -> t.stopThread());
        System.out.println("[BookCore][ThreadManager] Stop all threads.");
        return;
    }
}
