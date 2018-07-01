package com.hateit.repositories.ORMLiteRepositories;

import com.hateit.interfaces.repositories.CommentRepository;
import com.hateit.interfaces.repositories.HateRepository;
import com.hateit.listener.AppListener;
import com.hateit.models.Comment;
import com.hateit.models.Hate;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.DataSourceConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.springframework.stereotype.Repository;


@Repository
public class ORMLiteHateRepository extends HateRepository {


    public ORMLiteHateRepository() {
        Dao<Hate, String> userIntegerDao;

        // create a connection source to our database
        DataSourceConnectionSource connectionSource = null;
        try {
            connectionSource = new DataSourceConnectionSource(createDataSource(), AppListener.DATABASE_URL);
            // Create a Data Access Object
            userIntegerDao = DaoManager.createDao(connectionSource, Hate.class);
            // Create a corresponding database table as needed
            TableUtils.createTableIfNotExists(connectionSource, Hate.class);
            setDao(userIntegerDao);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            justCloseIt(connectionSource);
        }
    }

}
