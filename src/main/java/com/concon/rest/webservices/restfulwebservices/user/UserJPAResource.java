package com.concon.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> retriveAllUsers(){ return userRepository.findAll();}

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retriveUsersAllPost(@PathVariable int id){

       Optional<User> userOptional=userRepository.findById(id);
       if(!userOptional.isPresent()){
           throw new UserNotFoundException("id"+id);
       }
        return userOptional.get().getPosts();

    }

    @GetMapping("/jpa/users/{id}")
    public User retriveUser(@PathVariable int id){
       Optional<User> user=userRepository.findById(id);
        if(user.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }
        return userRepository.findById(id).get();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id,@RequestBody Post post){
        Optional<User> userOptional =userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }
         User user =userOptional.get();
         post.setUser(user);

         postRepository.save(post);

        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser=userRepository.save(user);
        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
       userRepository.deleteById(id);

    }


}
