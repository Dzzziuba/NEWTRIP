package today.newtrip.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import today.newtrip.DAO.LandmarkDAO;
import today.newtrip.FileServices.ImageService;
import today.newtrip.Model.Landmark;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Romachka on 31.07.17.
 */
@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    ObjectController objCtrl;
    @Autowired
    LandmarkDAO landmarkDAO;
    @Autowired
    BCryptPasswordEncoder encoder;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirect(HttpServletRequest request) {
        return  "forward:/Login";
    }
    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String redirectLogin(HttpServletRequest request) {
        request.getSession().invalidate();
        return  "forward:/Pages/Login.jsp";
    }

    @RequestMapping(value = "/success", method = RequestMethod.POST)
    public String logSuccess() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String accessDenied() {
        return "forward:/Pages/Denied.jsp";
    }

    @RequestMapping(value = "/Logout", method = RequestMethod.GET)
    public String redirectLogout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return  "redirect:/home";
    }

}