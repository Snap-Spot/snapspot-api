package snap.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import snap.jwt.JwtTokenUtil;
import snap.response.ExceptionHandlerFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenUtil jwtTokenUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // .antMatchers("/**").permitAll()
                .antMatchers("/members/test", "/members/signup", "/members/signin").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/photographers/**").permitAll()
                .antMatchers("/plans/**").authenticated()
                .antMatchers("/plans/deposit", "/plans/reserve").hasRole("PHOTOGRAPHER")
                .antMatchers(HttpMethod.POST, "/members/**").authenticated()
                .and()
                .apply(new JwtSecurityConfig(jwtTokenUtil));

        httpSecurity.addFilterBefore(
                new ExceptionHandlerFilter(),
                UsernamePasswordAuthenticationFilter.class
        );

        return httpSecurity.build();

    }

    // password 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
