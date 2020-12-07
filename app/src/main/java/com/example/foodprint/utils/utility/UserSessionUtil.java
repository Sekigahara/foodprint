package com.example.foodprint.utils.utility;

import com.example.foodprint.model.user.User;
import com.example.foodprint.utils.session.UserSessionRepositoryRepository;

public class UserSessionUtil {
    private UserSessionRepositoryRepository userSessionRepositoryRepository;

    public UserSessionUtil(UserSessionRepositoryRepository userSessionRepositoryRepository){
        this.userSessionRepositoryRepository = userSessionRepositoryRepository;
    }

    public void setSession(User user){
        userSessionRepositoryRepository.setSessionData(user);
    }

    public User getSession(){
        return userSessionRepositoryRepository.getSessionData();
    }

    public void updateSession(User user){
        userSessionRepositoryRepository.update(user);
    }

    public void logout(){
        userSessionRepositoryRepository.destroy();
    }
}
