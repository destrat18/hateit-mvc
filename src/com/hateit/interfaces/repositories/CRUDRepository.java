package com.hateit.interfaces.repositories;

import com.hateit.listener.AppListener;
import com.hateit.models.User;
import com.j256.ormlite.dao.Dao;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;

import javax.sql.DataSource;
import java.io.Closeable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class CRUDRepository<T>{

    private Dao<T, String> userDao;

    public Dao<T, String> getUserDao() {
        return userDao;
    }

    public void add(T t) throws SQLException {
        userDao.create(t);
    }

    public void update(T t) throws SQLException {
        userDao.update(t);
    }

    public List<T> getAll() throws SQLException {
        return userDao.queryForAll();
    }

    public T getById(String id) throws SQLException {
        return userDao.queryForId(id);
    }

    public void remove(String id) throws SQLException {
        userDao.deleteById(id);
    }

    public void setDao(Dao<T, String> userDao) throws SQLException {
        this.userDao = userDao;
    }

    public boolean isExist(String id) throws SQLException
    {
        return userDao.idExists(id);
    }

    public static DataSource createDataSource () {
        // ConnectionFactory can handle null username and password (for local host-based authentication)
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(AppListener.DATABASE_URL, null, null);
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
        GenericObjectPool connectionPool = new GenericObjectPool(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        // Disabling auto-commit on the connection factory confuses ORMLite, so we leave it on.
        // In any case ORMLite will create transactions for batch operations.
        return new PoolingDataSource(connectionPool);
    }

    public static void justCloseIt (Closeable closeable) {
        try {
            if (closeable != null) closeable.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
