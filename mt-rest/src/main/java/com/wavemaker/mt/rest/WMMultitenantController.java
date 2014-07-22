package com.wavemaker.mt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.mt.domain.TodoItem;
import com.wavemaker.mt.domain.User;
import com.wavemaker.mt.service.WMMultitenantService;


/**
 * @author gauravs
 *
 */
@RestController
@RequestMapping("rest")
public class WMMultitenantController {

    @Autowired
    @Qualifier("wmMultitenantService")
    private WMMultitenantService service;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUserInfo(@PathVariable Long id) {
        return service.getUser(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> getCustomers() {
        return service.getUsers();
    }

    @RequestMapping(value = "/user/{id}/todo", method = RequestMethod.GET)
    public List<TodoItem> getTransactions(@PathVariable Long id) {
        return getUserInfo(id).getTodoItems();
    }

    @RequestMapping(value = "/user/{id}/todo", method = RequestMethod.POST)
    public List<TodoItem> addTransaction(@PathVariable Long id, @RequestBody TodoItem todoItem) {

        User user = getUserInfo(id);
        user.getTodoItems().add(todoItem);
        service.save(user);
        return user.getTodoItems();
    }

    @RequestMapping(value = "/user/{id}/todo/{todoId}", method = RequestMethod.DELETE)
    public User addTransaction(@PathVariable Long id, @PathVariable Long todoId) {
        User user = getUserInfo(id);
        user.deleteTodo(todoId);
        service.save(user);
        return getUserInfo(id);
    }
}
