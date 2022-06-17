package sd.hotelroomservice.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sd.hotelroomservice.entity.enums.Role;

import java.util.Arrays;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(user.getRole().equals(Role.ROLE_MANAGER))
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER"));
        else if (user.getRole().equals(Role.ROLE_LABORER))
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_LABORER"));
        else if (user.getRole().equals(Role.ROLE_CUSTOMER))
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        else if (user.getRole().equals(Role.ROLE_RECEPTIONIST))
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_RECEPTIONIST"));
        return null;
    }

    public boolean isRestaurantAdministrator() {
        return true;
        //return user.getUserType().equals(UserType.ROLE_RESTAURANT_ADMINISTRATOR);
    }

    public boolean isCustomer() {
        return true;
        //return user.getUserType().equals(UserType.ROLE_CUSTOMER);
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
