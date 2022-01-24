package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.member.Service.LoginHistoryService;
import com.zerobase.fastlms.member.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;

    private final LoginHistoryService loginHistoryService;

    @Bean
    UserAuthenticationFailureHandler getFailureHandler(){
        return new UserAuthenticationFailureHandler();
    }

    @Bean
    PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserAuthenticationSuccessHandler getSucceccHandler() {return new UserAuthenticationSuccessHandler();}

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/favicon.ico", "/files/**");

        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.headers().frameOptions().sameOrigin();
        //페이지의 권한 설정 가능
        http.authorizeRequests()
                .antMatchers("/"
                        ,"/member/register"
                        ,"/member/email-auth"
                        ,"/member/find/password"
                        ,"/member/find/find_password_result"
                        ,"/member/find/reset_password"
                        ,"/member/find/reset_password_result"
                )
                .permitAll();

        http.authorizeRequests()
                        .antMatchers("/admin/**")
                                .hasAuthority("ROLE_ADMIN");
        //로그인 관련된 부분 설정
        http.formLogin()
                .loginPage("/member/login")
                .failureHandler(getFailureHandler())
                .successHandler(getSucceccHandler())
                .permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

        http.exceptionHandling()
                .accessDeniedPage("/error/denied");

        super.configure(http);
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
            .passwordEncoder(getPasswordEncoder());

        super.configure(auth);
    }
}
