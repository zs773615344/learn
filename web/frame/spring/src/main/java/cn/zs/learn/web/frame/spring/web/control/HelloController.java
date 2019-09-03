package cn.zs.learn.web.frame.spring.web.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping(value="hh",method=RequestMethod.GET)
    public String printHello(ModelMap model) {
        model.addAttribute("message", "hello spring web mvc");
        return "hello";
    }
}
