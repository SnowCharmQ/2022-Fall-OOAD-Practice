package sustech.ooad.a2.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sustech.ooad.a2.interceptor.LoginInterceptor;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoginInterceptorConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new LoginInterceptor();
        List<String> patterns = new ArrayList<>();
        patterns.add("/course/bootstrap3/**");
        patterns.add("/user/reg");
        patterns.add("/user/reg.html");
        patterns.add("/user/login");
        patterns.add("/user/login.html");
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
