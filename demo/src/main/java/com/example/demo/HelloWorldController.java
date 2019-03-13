package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by anyho on 2019/3/7.
 */
@RestController
@RequestMapping("/users")
public class HelloWorldController
{
    private Map<Integer, User> userMap = Collections.synchronizedMap(new HashMap<Integer, User>());

    @RequestMapping("/hello")
    public String index()
    {
        return "Hello SpringBoot!";
    }

    // http://localhost:8080/users/?page=1&size=10
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUserList(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size)
    {
        return new ArrayList<>(userMap.values());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user)
    {
        userMap.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int id)
    {
        return userMap.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable int id, @ModelAttribute User user)
    {
        User savedUser = userMap.get(user.getId());
        savedUser.setName(user.getName());
        savedUser.setAge(user.getAge());

        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable int id)
    {
        userMap.remove(id);
        return "success";
    }

}
