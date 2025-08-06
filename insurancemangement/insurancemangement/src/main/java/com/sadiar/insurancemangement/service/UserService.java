package com.sadiar.insurancemangement.service;

import com.sadiar.insurancemangement.repository.IUserReporisitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserReporisitory  userReporisitory;

}
