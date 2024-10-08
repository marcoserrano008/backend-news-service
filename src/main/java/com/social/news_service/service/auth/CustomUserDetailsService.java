package com.social.news_service.service.auth;

import com.social.news_service.dto.response.UserAuthDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String url = String.format("http://localhost:8091/users/username/%s", username);
        UserAuthDetailResponse userAuthDetailResponse = restTemplate.getForObject(url, UserAuthDetailResponse.class);

        if (userAuthDetailResponse == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        List<GrantedAuthority> authorities = userAuthDetailResponse.getAuthorities().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(userAuthDetailResponse.getUsername(), userAuthDetailResponse.getPassword(), authorities);
    }
}
