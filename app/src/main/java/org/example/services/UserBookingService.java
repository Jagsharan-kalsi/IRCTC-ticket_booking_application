package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.User;
import org.example.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private User user;

    private List<User> userList;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH = "app/src/main/java/org/example/localDb";

    //deserialization of JSON object to Java object for reading users and adding it to userList //
    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        loadUsers();
    }

    public UserBookingService() throws IOException{
        loadUsers();
    }

    public List<User> loadUsers () throws IOException {
        File users = new File(USERS_PATH);
        return objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    // takes user globally and matches with every user in userList (name and password) if match found is present returns true otherwise false //
    public boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
                return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),user1.getHashedPassword());
                }).findFirst();
        return foundUser.isPresent();

    }

    public boolean signup (User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    // serialisation to save java object to json file //
    public void saveUserListToFile() throws IOException{
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile,userList);
    }

    public void fetchBooking() {
        user.printTickets();
    }

    public Boolean cancelBooking() {

        /* todo : complete this function */
        return Boolean.FALSE;
    }

}
