package uz.pdp.bankamat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("http://localhost:8080/api/employee/verifyEmail/***").hasAnyRole("HODIM" , "DIREKTOR")
                    .antMatchers("http://localhost:8080/api/card").permitAll()
                .antMatchers(HttpMethod.DELETE).hasAuthority("DELETE")
                .antMatchers(HttpMethod.POST).hasAuthority("CREATE")
                .antMatchers(HttpMethod.PUT).hasAuthority("UPDATE")
                .antMatchers(HttpMethod.GET).hasAnyAuthority("READ", "READ1")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("direktor").password(passwordEncoder().encode("direktor")).roles("DIREKTOR").authorities("CREATE", "READ", "READ1", "UPDATE", "DELETE")
                .and()
                .withUser("hodim").password(passwordEncoder().encode("hodim")).roles("HODIM").authorities("CREATE", "READ", "READ1")
                .and()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER").authorities("READ", "READ1");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
