package dev.vuphatdat.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Configuration
    public static class AdminConfig{
        @Bean
        public SecurityFilterChain filterChain1(HttpSecurity http) throws  Exception{
            http.csrf(csrf -> csrf.disable())

                    .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/home").permitAll()
                            .requestMatchers("/index").hasRole("USER"))
                    .formLogin(form -> form.loginPage("/login")
                            .loginProcessingUrl("/login")
                            .defaultSuccessUrl("/index").permitAll())

                    .authorizeHttpRequests((authorize) ->
                            authorize.requestMatchers("/register/**")
                                    .permitAll().requestMatchers("/home")
                                    .permitAll().requestMatchers("/users").hasRole("ADMIN"))
                    .formLogin(form -> form.loginPage("/login")
                            .loginProcessingUrl("/login")
                            .defaultSuccessUrl("/users").permitAll());
            return http.build();
        }
    }



    @Autowired
    public  void configuredGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder((passwordEncoder()));
    }
}
