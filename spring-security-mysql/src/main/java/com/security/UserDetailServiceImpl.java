package com.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.dao.User;
import com.dao.UserRepository;

@Service
@Qualifier("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = usersRepository.findByEmail(username);
    if (user == null) {
      new UsernameNotFoundException("User not found.");
    }

    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

    return new org.springframework.security.core.userdetails.User(user.getEmail(),
        user.getPassword(), grantedAuthorities);
  }

}
