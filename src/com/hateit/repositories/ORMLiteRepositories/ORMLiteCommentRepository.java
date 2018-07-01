package com.hateit.repositories.ORMLiteRepositories;

import com.hateit.interfaces.repositories.CommentRepository;
import com.hateit.interfaces.repositories.UserRepository;
import com.hateit.listener.AppListener;
import com.hateit.models.Comment;
import com.hateit.models.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.DataSourceConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;


@Repository
public class ORMLiteCommentRepository extends CommentRepository {


    public ORMLiteCommentRepository() {
        Dao<Comment, String> userIntegerDao;

        // create a connection source to our database
        DataSourceConnectionSource connectionSource = null;
        try {
            connectionSource = new DataSourceConnectionSource(createDataSource(), AppListener.DATABASE_URL);
            // Create a Data Access Object
            userIntegerDao = DaoManager.createDao(connectionSource, Comment.class);
            // Create a corresponding database table as needed
            TableUtils.createTableIfNotExists(connectionSource, Comment.class);
            setDao(userIntegerDao);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            justCloseIt(connectionSource);
        }
    }

}
