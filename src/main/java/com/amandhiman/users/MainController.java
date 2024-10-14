package com.amandhiman.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
@RequestMapping(path = "/users")
public class MainController {
  @Autowired
  private UserRepository userRepository;

  @PostMapping(path = "/")
  public @ResponseBody String addNewUser(@RequestParam String username, @RequestParam String firstName,
      @RequestParam String lastName, @RequestParam String email, @RequestParam String phoneNumber) {

    User n = new User();
    n.setUsername(username);
    n.setFirstName(firstName);
    n.setLastName(lastName);
    n.setPhoneNumber(phoneNumber);
    n.setEmail(email);
    userRepository.save(n);
    return "Saved";
  }

  @GetMapping(path = "/")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Integer id) {
    return userRepository.findById(id).orElse(null);
  }

  @PutMapping(path = "/{id}")
  public @ResponseBody String updateUser(@PathVariable Integer id, @RequestBody User user) {
    Optional<User> existingUser = userRepository.findById(id);
    if (existingUser.isPresent()) {
      User n = existingUser.get();
      n.setUsername(user.getUsername());
      n.setFirstName(user.getFirstName());
      n.setLastName(user.getLastName());
      n.setPhoneNumber(user.getPhoneNumber());
      n.setEmail(user.getEmail());
      userRepository.save(n);
    }
    return "Saved";
  }

  @DeleteMapping("/{id}")
  public @ResponseBody String deleteUser(@PathVariable Integer id) {
    userRepository.deleteById(id);
    return "Deleted";
  }
}
