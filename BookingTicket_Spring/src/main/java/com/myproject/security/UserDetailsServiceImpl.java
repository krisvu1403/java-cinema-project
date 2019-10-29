package com.myproject.security;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myproject.entity.User;
import com.myproject.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository _userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = _userRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("Không tìm thấy tài khoản!");
		}
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

		CustomUserDetails userDetails = new CustomUserDetails(user.getEmail(), user.getPassword(), authorities);
		
		userDetails.setFullname(user.getFullname());
		userDetails.setAvatar(user.getAvatar());
		return userDetails;
	}

}
