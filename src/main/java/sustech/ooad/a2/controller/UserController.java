package sustech.ooad.a2.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sustech.ooad.a2.entity.UserEntity;
import sustech.ooad.a2.service.UserService;
import sustech.ooad.a2.vo.RegVo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/reg.html")
    public String reg() {
        return "reg";
    }

    @PostMapping("/reg")
    public String register(RegVo user, RedirectAttributes attributes) {
        Map<String, String> errors = new HashMap<>();
        if (user.getUsername() == null || StringUtils.isEmpty(user.getUsername())) {
            errors.put("user", "Please enter your username!");
        } else if (userService.checkUsername(user.getUsername())) {
            errors.put("user", "Duplicate username!");
        }
        if (!Objects.equals(user.getPwd(), user.getConfirmPwd())) {
            errors.put("confirmPwd", "The two passwords are different!");
        }
        if (!errors.isEmpty()) {
            attributes.addFlashAttribute("errors", errors);
            return "redirect:reg.html";
        } else {
            UserEntity entity = new UserEntity();
            entity.setUsername(user.getUsername());
            entity.setPwd(user.getPwd());
            userService.save(entity);
            return "redirect:login.html";
        }
    }
}
