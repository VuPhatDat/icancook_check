package dev.vuphatdat.service;

import dev.vuphatdat.models.Role;
import dev.vuphatdat.models.User;
import dev.vuphatdat.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Service
public class CustomerUserDetailService implements UserDetailsService {
    @Autowired  private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user!=null){
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
        }
        else{
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return mapRoles;
    }
}
