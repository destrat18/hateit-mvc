package com.hateit.repositories.ORMLiteRepositories;

import com.hateit.interfaces.repositories.CategoryRepository;
import com.hateit.interfaces.repositories.PostRepository;
import com.hateit.interfaces.repositories.UserRepository;
import com.hateit.listener.AppListener;
import com.hateit.models.Category;
import com.hateit.models.Post;
import com.hateit.models.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.DataSourceConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ORMLiteCategoryRepository extends CategoryRepository {

    @Autowired
    PostRepository postRepository;

    public ORMLiteCategoryRepository() {
        Dao<Category, String> userIntegerDao;

        // create a connection source to our database
        DataSourceConnectionSource connectionSource = null;
        try {
            connectionSource = new DataSourceConnectionSource(createDataSource(), AppListener.DATABASE_URL);
            // Create a Data Access Object
            userIntegerDao = DaoManager.createDao(connectionSource, Category.class);
            // Create a corresponding database table as needed
            TableUtils.createTableIfNotExists(connectionSource, Category.class);
            setDao(userIntegerDao);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            justCloseIt(connectionSource);
        }
    }

    @Override
    public Category getByName(String name) throws SQLException{
        Category category = getUserDao().queryBuilder().where().eq("name", name).queryForFirst();
        return category;
    }

    @Override
    public List<Post> getAllPostByName(String name) throws SQLException {
        QueryBuilder<Category, String> cBuilder = getUserDao().queryBuilder();
        cBuilder.where().eq("name", name);
        QueryBuilder<Post, String> pBuilder = postRepository.getUserDao().queryBuilder();
        pBuilder.orderBy("timestamp", false);
        pBuilder.join(cBuilder);
        return pBuilder.query();
    }
}
