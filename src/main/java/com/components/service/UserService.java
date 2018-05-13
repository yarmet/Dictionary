package com.components.service;

import com.components.database.repository.RoleRepository;
import com.components.database.repository.JpaUserRepository;
import com.components.database.models.Role;
import com.components.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import com.components.database.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;


@Service
public class UserService {

    private static final Long ROLE_USER = 1L;

    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @Transactional
    public void registryUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(Utils.getCurrentTimestampAsUTC());
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(ROLE_USER));
        user.setRoles(roles);
        userRepository.save(user);
    }


    @Transactional
    public void login(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

    @Transactional
    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }
}
