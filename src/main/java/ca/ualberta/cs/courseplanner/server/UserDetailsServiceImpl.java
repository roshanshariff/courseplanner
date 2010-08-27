package ca.ualberta.cs.courseplanner.server;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.ualberta.cs.courseplanner.entities.User;
import ca.ualberta.cs.courseplanner.entities.User.Role;

@Repository
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String userId)
			throws UsernameNotFoundException, DataAccessException {
		User user = (User) sessionFactory.getCurrentSession().get(User.class, userId);
		if (user != null) {
			Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			for (Role role : user.getRoles()) authorities.add(new GrantedAuthorityImpl("ROLE_"+role.name()));
			return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), user.isEnabled(), true, true, !user.isLocked(), authorities);
		}
		else {
			throw new UsernameNotFoundException("Invalid username: "+userId);
		}
	}

}
