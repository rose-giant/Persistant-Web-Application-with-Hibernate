package Mizdooni.Controller;


import Mizdooni.Model.Restaurant.Restaurant;
import Mizdooni.Model.User.User;
import Mizdooni.Model.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepo;

    @Autowired
    public UserController() throws Exception {
        userRepo = UserRepository.getInstance();
    }

    @GetMapping("")
    public ArrayList<User> getAll() throws SQLException {
        return userRepo.getAll();
    }
    @GetMapping("/{username}")
    public User getOne(@PathVariable String username) throws SQLException {
        System.out.println("get one user: " + username);
        return userRepo.findUserByUserName(username);
    }

}