package hackathon.EchoPangPang.config;

import hackathon.EchoPangPang.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig는 Spring MVC의 설정 클래스로, 인터셉터, 리소스 핸들러, 뷰 리졸버 등의 설정을 담당합니다.
 * WebMvcConfigurer 인터페이스를 구현하여 필요한 설정을 추가할 수 있습니다.
 * <p>
 * Interceptorregistry: spring MVC에서 인터셉터를 등록하고 관리하는 데 사용하는 객체
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor) // 인터셉터 등록
                .addPathPatterns("/**") // 모든 경로 검사
                .excludePathPatterns(
                        "/assets/**",
                        "/front/dev/**",
                        "/SignUp",
                        "/LoginPage",
                        "/index",
                        "/",
                        "/api/**",
                        "/test/init"

                ); // 특정 경로는 제외
    }
}
