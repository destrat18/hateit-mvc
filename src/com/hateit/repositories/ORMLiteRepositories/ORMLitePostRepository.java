package com.hateit.repositories.ORMLiteRepositories;

import com.hateit.interfaces.repositories.CRUDRepository;
import com.hateit.interfaces.repositories.PostRepository;
import com.hateit.listener.AppListener;
import com.hateit.models.Post;
import com.hateit.models.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.DataSourceConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;


@Repository
public class ORMLitePostRepository extends PostRepository {


    public ORMLitePostRepository() {
        Dao<Post, String> userIntegerDao;

        // create a connection source to our database
        DataSourceConnectionSource connectionSource = null;
        try {
            connectionSource = new DataSourceConnectionSource(createDataSource(), AppListener.DATABASE_URL);
            // Create a Data Access Object
            userIntegerDao = DaoManager.createDao(connectionSource, Post.class);
            // Create a corresponding database table as needed
            TableUtils.createTableIfNotExists(connectionSource, Post.class);
            setDao(userIntegerDao);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            justCloseIt(connectionSource);
        }
    }

    @Override
    public List<Post> getAll() throws SQLException {
        List<Post> posts = super.getAll();
        posts.sort(new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                return (int)(o1.getTimestamp() - o2.getTimestamp());
            }
        });
        return posts;
    }
}
