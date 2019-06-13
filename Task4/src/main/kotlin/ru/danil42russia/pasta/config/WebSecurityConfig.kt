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
import java.time.LocalDateTime

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
    }

    @Bean
    fun principalExtractor(userDetailsRepo: UserDetailsRepository): PrincipalExtractor {
        return PrincipalExtractor { map ->
            val id: String = map["sub"] as String

            val user = userDetailsRepo.findById(id).orElseGet {
                User(
                        id = id,
                        name = map["name"] as String,
                        userpic = map["picture"] as String,
                        email = map["email"] as String,
                        gender = map["gender"] as String?,
                        locale = map["locale"] as String
                )
            }

            userDetailsRepo.save(user)
        }
    }
}