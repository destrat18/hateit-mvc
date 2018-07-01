package com.hateit.repositories.ORMLiteRepositories;

import com.hateit.interfaces.repositories.CRUDRepository;
import com.hateit.interfaces.repositories.UserRepository;
import com.hateit.listener.AppListener;
import com.hateit.models.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.DataSourceConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.Closeable;
import java.sql.SQLException;
import java.util.List;


@Repository
public class ORMLiteUserRepository extends UserRepository {


    public ORMLiteUserRepository() {
        Dao<User, String> userIntegerDao;

        // create a connection source to our database
        DataSourceConnectionSource connectionSource = null;
        try {
            connectionSource = new DataSourceConnectionSource(createDataSource(), AppListener.DATABASE_URL);
            // Create a Data Access Object
            userIntegerDao = DaoManager.createDao(connectionSource, User.class);
            // Create a corresponding database table as needed
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            setDao(userIntegerDao);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            justCloseIt(connectionSource);
        }
    }

    @Override
    public User getUserByUsername(String username) throws SQLException{
        User user = getUserDao().queryBuilder().where().eq("username", username).queryForFirst();
        return user;
    }
}
