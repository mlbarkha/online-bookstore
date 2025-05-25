package com.amity.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@Component
class InMemoryAuthConfig {

	public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin").password(passwordEncoder.encode("admin123")).roles("ADMIN")
            .and()
            .withUser("user").password(passwordEncoder.encode("user123")).roles("USER");
    }
}
 