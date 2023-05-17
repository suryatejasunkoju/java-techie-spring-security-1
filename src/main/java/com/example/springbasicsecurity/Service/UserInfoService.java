package com.example.springbasicsecurity.Service;

import com.example.springbasicsecurity.Entity.UserInfo;
import com.example.springbasicsecurity.Entity.UserInfoDetails;
import com.example.springbasicsecurity.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoService implements UserDetailsService {
    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findByName(username);
        //converting UserInfo type to UserDetails type and returning it.
        return optionalUserInfo.map(UserInfoDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("User not found with name:"+username));
    }
}
