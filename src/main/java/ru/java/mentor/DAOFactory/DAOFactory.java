package ru.java.mentor.DAOFactory;

import java.io.IOException;

public interface DAOFactory {

    DAO createDAO() throws IOException;
}
