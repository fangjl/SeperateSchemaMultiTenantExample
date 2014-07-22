package com.wavemaker.mt.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;


/**
 * @author gauravs
 *
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Version
    @Column(name = "version")
    Integer version;

    @NotNull
    String name;

    @OneToMany(cascade = CascadeType.ALL)
    List<TodoItem> todoItems = new ArrayList<TodoItem>();

    public void addItem(TodoItem item) {
        todoItems.add(item);
    }

    public void deleteTodo(Long id) {

        for (TodoItem item : todoItems) {
            if (item.id.equals(id)) {
                todoItems.remove(item);
                return;
            }
        }

        throw new IllegalArgumentException("No todo found for ID: " + id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }
}
