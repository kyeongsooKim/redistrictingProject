package america.redistricting.controller;

import america.redistricting.model.Users;
import america.redistricting.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

    //logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    UsersRepository usersRepository;

    @GetMapping(value = "all")
    public List<Users> getAll(){

        log.info("accessed end point /rest/users/all");
        return usersRepository.findAll();
    }


    //save new user
    @PostMapping(value = "/addUser")
    public List<Users> persist(final Users users)
    {
        log.info("accessed end point /rest/users/addUser");
        usersRepository.save(users);
        return usersRepository.findAll();
    }
}
