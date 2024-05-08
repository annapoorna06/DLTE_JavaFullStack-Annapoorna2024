package project.backend.security;
import mybank.dao.services.MyBankCustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomerSecureConfiguration {
    @Autowired
    MyBankCustomersService service;
    AuthenticationManager authenticationManager;
    @Autowired
    CustomersFailureHandler customersFailureHandler;
    @Autowired
    CustomersSuccesshandler customersSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/v3/api-docs").permitAll();

        httpSecurity.formLogin().loginPage("/weblogin/").
                usernameParameter("username").
                failureHandler(customersFailureHandler).
                successHandler(customersSuccessHandler);
        httpSecurity.authorizeRequests().antMatchers("/images/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/styles/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/weblogin/").permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        httpSecurity.logout().permitAll();

        AuthenticationManagerBuilder builder=httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(service);
        authenticationManager=builder.build();
        httpSecurity.authenticationManager(authenticationManager);
        return httpSecurity.build();
    }
}

