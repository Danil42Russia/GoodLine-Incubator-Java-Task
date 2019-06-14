package ru.danil42russia.pasta.config

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import ru.danil42russia.pasta.repository.UserDetailsRepository
import ru.danil42russia.pasta.domain.User

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/**/**").permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .permitAll()
                .and()
                    .csrf().disable()
    }

    @Bean
    fun principalExtractor(userDetailsRepo: UserDetailsRepository): PrincipalExtractor {
        return PrincipalExtractor { map ->
            val googleId: String = map["sub"] as String

            val user = userDetailsRepo.findByGoogleId(googleId).orElseGet {
                User(
                        google_id = googleId,
                        name = map["name"] as String,
                        email = map["email"] as String
                )
            }

            userDetailsRepo.save(user)
        }
    }
}