package Mizdooni.Controller;

import Mizdooni.Model.User.User;
import Mizdooni.Model.User.UserRepository;
import Mizdooni.Model.User.userView;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLWarning;
import java.util.Map;

@RestController
@RequestMapping(path= "/",produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    @PostMapping("login")
    public User login(HttpServletResponse response,
                      @RequestBody Map<String, String> body) throws Exception {

        UserRepository userRepo = UserRepository.getInstance();
        User user = userRepo.findByUsernameAndPassword(body.get("username"), body.get("password"));
        System.out.println(body.get("username") + body.get("password"));
        if(user == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Invalid username or password!");
            response.getWriter().flush();
            System.out.println("Invalid username or password!");
        }
        return user;
    }
    @PostMapping("signup")
    public User signup(HttpServletResponse response,
                       @RequestBody userView userView) throws Exception {
        UserRepository userRepository = UserRepository.getInstance();
        User newUser = userView.viewToUser();
        try{
            userRepository.addUser(newUser);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return newUser;
        }catch (SQLWarning e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(e.getMessage());
            response.getWriter().flush();
            System.out.println(e.getMessage());
        }
        return null;
    }

}
