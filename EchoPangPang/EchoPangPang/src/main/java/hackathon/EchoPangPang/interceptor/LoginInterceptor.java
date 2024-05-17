package hackathon.EchoPangPang.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
/**
 * preHandle(): 컨트롤러의 메서드가 호출되기 전에 실행된다. 주로 인증/인가 검사를 여기서 수행
 *
 * 인터페이스엔 있지만 오버라이드 안 한 부분
 * postHandler(), afterCompletion()
 * */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("member") == null) { // 멤버 속성 확인
            response.sendRedirect("/login"); // 로그인 페이지로 리다이렉트
            return false;  // 요청 중단
        }
        return true; // 요청을 계속 진행
    }
}
