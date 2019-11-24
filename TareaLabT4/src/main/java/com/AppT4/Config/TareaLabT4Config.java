package com.AppT4.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.AppT4.Service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class TareaLabT4Config extends WebSecurityConfigurerAdapter{
	
	  String[] resources = new String[]{
	            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**",
	            "/fonts/**","/vendors/**","/scss/**","/images/**"
	    };
	  
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        	http
        	.csrf().disable()
            .authorizeRequests()
	        .antMatchers(resources).permitAll()  
	        .antMatchers("/","/login").permitAll()
	        .antMatchers("/InformacionCuenta","/Home","/ServicioMecanico/listar","/ServicioMecanico/Gestionar",
	        		"/ServicioMecanico/listarPersonal","/ServicioMecanico/listarClientes",
	        		"/ServicioMecanico/Guardar","/ServicioMecanico/agregarDetalleServicio").access("hasRole('USER') or hasRole('ADMIN')")
            .antMatchers("/ServicioMecanico/Informacion**").access("hasRole('USER')")
	        .antMatchers("/**").access("hasRole('ADMIN')")
            .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/Home")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }
	
    @Autowired
    UserDetailsServiceImpl userDetailsService;
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception { 
    	
    	/*build.inMemoryAuthentication()
		.passwordEncoder(passwordEncoder())
		.withUser("admin").password(passwordEncoder().encode("12345"))
		.roles("USER","ADMIN");*/
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }
}
