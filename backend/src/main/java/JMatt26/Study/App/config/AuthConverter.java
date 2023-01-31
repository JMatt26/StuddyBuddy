package JMatt26.Study.App.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class AuthConverter implements Converter<Jwt, AbstractAuthenticationToken>{

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        List<String> authorities = source.getClaimAsStringList("authorities");
        
        List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();

        for (String authority : authorities) {
            roles.add(new SimpleGrantedAuthority(authority));
        }

        return new JwtAuthenticationToken(source, roles, source.getClaimAsString("sub"));
    }
    
}
