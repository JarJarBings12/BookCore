package ch.JarJarBings12.BookCore.kernel.threads;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 09.07.2015
 */
public abstract class BookThread extends Thread
{
    private volatile boolean run = true;
    private volatile boolean enable = true;

    public boolean isRunning()
    {
        return run;
    }

    public void stopThread()
    {
        run = false;
    }

    private int runningMinutes;

    @Override
    public void run()
    {
        System.out.println("[BookCore][Thread] Start BookCore Thread.");
        while (run)
        {
            if (enable == true)
            {
                beat();
            }
            runningMinutes++;
            try
            {
                Thread.sleep(60000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public abstract void beat();
}
