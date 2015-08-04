package ch.JarJarBings12.BookCore.sql.database.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 08.06.2015
 */
public interface DatabaseInterface
{
    Connection openConnection();

    Statement createStatement();

    PreparedStatement createPreparedStatement(String query);
}
