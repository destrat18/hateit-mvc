package com.hateit.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener()
public class AppListener implements ServletContextListener {

    public static final String DATABASE_URL = "jdbc:postgresql://localhost/hateit?user=hateit&password=123456789";

    public void contextInitialized(ServletContextEvent sce) {

//        Dao<User, Integer> userIntegerDao;
//
//        // create a connection source to our database
//        DataSourceConnectionSource connectionSource = null;
//        try {
//            connectionSource = new DataSourceConnectionSource(createDataSource(), DATABASE_URL);
//            // Create a Data Access Object
//            userIntegerDao = DaoManager.createDao(connectionSource, User.class);
//            // Create a corresponding database table as needed
//            TableUtils.createTableIfNotExists(connectionSource, User.class);
//            sce.getServletContext().setAttribute("userIntegerDao", userIntegerDao);
//
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            justCloseIt(connectionSource);
//        }
    }

//    public static DataSource createDataSource () {
//        // ConnectionFactory can handle null username and password (for local host-based authentication)
//        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(DATABASE_URL, null, null);
//        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
//        GenericObjectPool connectionPool = new GenericObjectPool(poolableConnectionFactory);
//        poolableConnectionFactory.setPool(connectionPool);
//        // Disabling auto-commit on the connection factory confuses ORMLite, so we leave it on.
//        // In any case ORMLite will create transactions for batch operations.
//        return new PoolingDataSource(connectionPool);
//    }
//
//    public static void justCloseIt (Closeable closeable) {
//        try {
//            if (closeable != null) closeable.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }


    public void contextDestroyed(ServletContextEvent sce) {
    }
}
