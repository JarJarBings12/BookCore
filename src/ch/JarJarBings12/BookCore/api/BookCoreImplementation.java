package ch.JarJarBings12.BookCore.api;

import ch.JarJarBings12.BookCore.kernel.threads.SessionEvent;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 11.07.2015
 */
public abstract class BookCoreImplementation
{
    public abstract void onLoad();

    public abstract void onUnload();

    public abstract void call(SessionEvent sessionEvent);
}
