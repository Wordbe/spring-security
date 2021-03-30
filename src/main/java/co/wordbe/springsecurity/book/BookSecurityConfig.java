package co.wordbe.springsecurity.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class BookSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()

        .and().formLogin() // Form 로그인 인증 기능
//                .loginPage("/loginPage") // 사용자 정의 로그인 페이지
                .defaultSuccessUrl("/") // 로그인 성공 후 이동 페이지
                .failureUrl("/login") // 로그인 실패 후 이동 페이지
                .usernameParameter("userId") // 아이디 파라미터명 설정
                .passwordParameter("passwd") // 패스워드 파라미터명 설정
                .loginProcessingUrl("/loginProc") // 로그인 Form Action Url
                .successHandler(loginSuccessHandler()) // 로그인 성공 후 핸들러
                .failureHandler(loginFailureHandler()) // 로그인 실패 후 핸들러
                .permitAll()

        .and().logout() // 로그아웃 기능
                .logoutUrl("/logout") // 로그아웃 처리 URL
                .logoutSuccessUrl("/login") // 로그아웃 성공 후 이동페이지
                .deleteCookies("JSESSIONID", "remember-me") // 로그아웃 후 쿠키삭제
                .addLogoutHandler(logoutHandler()) // 로그아웃 핸들러
                .logoutSuccessHandler(logoutSuccessHandler()) // 로그아웃 성공 후 핸들러

        .and().rememberMe() // rememberMe 기능 작동
                .rememberMeParameter("remember") // 기본 파라미터명은 remember-me
                .tokenValiditySeconds(3600) // Default는 14일
                .alwaysRemember(false) // true 로 하면 리멤버 미 기능이 활성화되지 않아도 항상 실
                .userDetailsService(userDetailsService) // 사용자 계정 조회시 필요

        .and().sessionManagement() // 세션 관리 기능 작동
                .invalidSessionUrl("/invalid") // 세션이 유효하지 않을 때 이동할 페이지
                .maximumSessions(1) // 최대 허용 가능 세션, -1이면 무제한 로그인 세션 허용
                .maxSessionsPreventsLogin(true) // 동시 로그인 차단, false이면 기존 세션 만료(default)
                .expiredUrl("/expired") // 세션이 만료된 경우 이동할 페이
        ;

//        http.sessionManagement()
//                .sessionFixation().changeSessionId() // 서블릿 3.1 이상에서 기본값, 기존 세션의 속성들은 유지한채 아이디만 변경
//                                            // migrateSession : 서블릿 3.1 미만에서 기본값, 기존 세션의 속성들은 유지한채 아이디만 변경
//                                            // none : 같은 쿠키 정보에 대해서 세션아이디 바뀌지않음 (SF 공격에 취약)
//                                            // newSession : 세션 ID가 새로 만들어지지만, 이전에 세션에서 저장된 설정값을 사용하지 못한다.
//        ;
//
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 기본값, 스프링 시큐리티가 필요시 생성
//                ;
//        SessionCreationPolicy.ALWAYS // 항상 세션 생성
//        SessionCreationPolicy.NEVER // 생성하지 않지만, 이미 존재하면 사용
//        SessionCreationPolicy.STATELESS // 생성하지도 않고, 존재해도 사용하지 않음 (JWT 방식 등)
    }

    private LogoutHandler logoutHandler() {
        return new LogoutHandler() {
            @Override
            public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
                HttpSession session = httpServletRequest.getSession();
                session.invalidate();
            }
        };
    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.sendRedirect("/login");
            }
        };
    }

    private AuthenticationSuccessHandler loginSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                System.out.println("authentication " + authentication.getCredentials());
                response.sendRedirect("/");
            }
        };
    }

    private AuthenticationFailureHandler loginFailureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                System.out.println("exception " + e.getMessage());
                response.sendRedirect("/login");
            }
        };
    }
}
