package com.hateit.services;

import com.hateit.common.HateItException;
import com.hateit.common.Utility;
import com.hateit.interfaces.repositories.UserRepository;
import com.hateit.interfaces.services.UserService;
import com.hateit.models.Hate;
import com.hateit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.swing.*;
import java.sql.SQLException;

@Service
public class UserServiceX implements UserService {

    @Autowired
    UserRepository userRepository;

    public User getById(String userId) throws Exception
    {
        User user = userRepository.getById(userId);
        if(user == null)
            throw  new HateItException("/login","ورود", "کاربر وجود ندارد!");
        else
            return user;
    }

    public User signUpUser(String username,
                           String password,
                           String repassword) throws Exception
    {
        HateItException exception = new HateItException("/sign-up", "ثبت‌نام");

        if(username.length() == 0)
             exception.getMessages().add("نام کاربری نباید خالی باشد");

        if(!Utility.isEmail(username))
            exception.getMessages().add("نام کاربری باید ایمیل باشد");

        if(password.length() == 0)
            exception.getMessages().add("گذواژه نباید خالی باشد");

        if(password.length() < 5)
            exception.getMessages().add("طول گذرواژه باید بیشتر ۵ کاراکتر باشد!");

        if(!Utility.hasLetter(password) || !Utility.hasNumber(password))
            exception.getMessages().add("گذرواژه باید شامل عدد و حرف باشد!");

            if(!password.equals(repassword))
            exception.getMessages().add("گذرواژه‌ها یکسان نیستند!");

        if(userRepository.getUserByUsername(username) != null)
            exception.getMessages().add("نام کاربری وجود دارد");

        if(exception.isHappen())
            throw exception;
        else
        {
            User nUser = new User();
            nUser.setId(Utility.getUniqueId());
            nUser.setUsername(username);
            nUser.setPassword(password);
            nUser.setBirthDate(60*60*24*365*23);
            nUser.setShortDescription("برنامه‌نویس");
            nUser.setLocation("مشهد");
            nUser.setName("سروش");
            nUser.setImage("https://www.w3schools.com/w3images/avatar3.png");
            userRepository.add(nUser);
            return nUser;
        }
    }

    @Override
    public User verifyUser(String username, String password) throws SQLException{
        User user = userRepository.getUserByUsername(username);
        if(user != null && user.getPassword().equals(password))
            return user;
        throw  new HateItException("/login","ورود", "نام کاربری یا گذرواژه اشتباه می‌باشد");
    }

    @Override
    public User getByUsername(String username) throws SQLException{
        User user = userRepository.getUserByUsername(username);
        if(user == null)
            throw  new HateItException("/login","ورود", "کاربر وجود ندارد!");
        else
            return user;
    }

    @Override
    public void updateUser(User user) throws Exception {
//        if(userRepository.getById(user.getId()) == null)
//            throw  new HateItException("/login","ورود", "کاربر وجود ندارد!");
//        else
//            userRepository.update(user);
    }
}
