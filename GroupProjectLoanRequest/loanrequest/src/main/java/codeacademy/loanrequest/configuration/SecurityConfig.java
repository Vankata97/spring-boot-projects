package codeacademy.loanrequest.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{


  private final UserDetailsService userDetails;

  public SecurityConfig(UserDetailsService userDetails)
  {
    this.userDetails = userDetails;
  }

  @Override
  public void configure(AuthenticationManagerBuilder authentication) throws Exception
  {
    authentication.userDetailsService(userDetails);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http
        .httpBasic()
        .and()
        .authorizeRequests()
        .mvcMatchers(HttpMethod.POST, "/svc/registration")
        .permitAll()
        .mvcMatchers(HttpMethod.GET, "/svc/bankoffer")
        .permitAll()
        .mvcMatchers(HttpMethod.POST, "/svc/response")
        .permitAll()
        .mvcMatchers(HttpMethod.POST, "/svc/financialstatus")
        .permitAll()
        .mvcMatchers(HttpMethod.POST, "/svc/personalinformation")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable();
  }
}

