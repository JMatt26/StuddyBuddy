package Study.App.config;

import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.nimbusds.jose.proc.SecurityContext;

import Study.App.model.enums.ParticipationRole;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;

@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf().disable()
            .oauth2ResourceServer().jwt()
            .jwtAuthenticationConverter(new AuthConverter()).and().and()
            .authorizeHttpRequests().anyRequest().authenticated().and()
            .httpBasic().and()
            .build();
    }

    // HARD CODED USER IN SYSTEM FOR TESTING
    // @Bean
    // UserDetailsService userDetailsService() {

    // UserDetails u = User.withUsername("bob").password(passwordEncoder()
    // .encode("pass"))
    // .authorities(ParticipationRole.ADMIN.toString())
    // .build();

    // return new InMemoryUserDetailsManager(u);
    // }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JWTKeys rsaKeys() throws Exception {
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        kg.initialize(2048);
        var kp = kg.generateKeyPair();

        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) kp.getPrivate();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) kp.getPublic();

        return new JWTKeys(rsaPrivateKey, rsaPublicKey);
    }

    /**
     * @return
     * @throws Exception
     */
    @Bean
    JwtEncoder jwtEncoder() throws Exception {
        JWK jwk = new RSAKey.Builder(rsaKeys().getPublicKey()).privateKey(rsaKeys().getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    JwtDecoder jwtDecoder() throws Exception {
        return NimbusJwtDecoder.withPublicKey(rsaKeys().getPublicKey()).build();
    }
}
