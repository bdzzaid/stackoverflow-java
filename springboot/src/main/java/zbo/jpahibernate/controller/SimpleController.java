package zbo.jpahibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller()
public class SimpleController
{
    @RequestMapping(value = "/test")
    public @ResponseBody
    void test(HttpServletRequest request,
              HttpServletResponse response) throws Exception
    {
        StringBuilder b = new StringBuilder();
        b.append(request.getSession().getCreationTime()).append("\n");
        String referer = request.getHeader("Referer");
        b.append("Referer ").append(referer).append("\n");
        response.getOutputStream().write(b.toString().getBytes());
    }

    @RequestMapping(value = "/redirect")
    public String redirect(HttpServletRequest request)
    {
        return "redirect:/test";
    }
}
