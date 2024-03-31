package spring.jdbc.template.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class TransactionSecurity {
    @Autowired
    private TransactionAuthorityService transactionAuthorityService;

    AuthenticationManager manager;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable();
        httpSecurity.httpBasic();
        httpSecurity.formLogin();

        httpSecurity.authorizeRequests().antMatchers("/account/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/transactions/receiver/{receiver}").hasAuthority("customer");
        httpSecurity.authorizeRequests().antMatchers("/transactions/sender/{sender}").hasAnyAuthority("customer");
        httpSecurity.authorizeRequests().antMatchers("/transactions/amount/{amount}").hasAnyAuthority("customer");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE).hasAuthority("admin");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT).hasAuthority("manager,admin");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST).hasAnyAuthority("admin");
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        AuthenticationManagerBuilder builder=httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(transactionAuthorityService);
        manager=builder.build();
        httpSecurity.authenticationManager(manager);
        return httpSecurity.build();
    }

}
