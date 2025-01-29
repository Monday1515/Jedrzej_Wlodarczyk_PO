package com.example.yourdiet;

import java.sql.Connection;

public class BaseDAO {
    protected Connection connection;

    BaseDAO(Connection connection){
        this.connection = connection;
    }
}
