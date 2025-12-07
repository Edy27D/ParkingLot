package org.parkinglot.parkinglot3.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.parkinglot.parkinglot3.common.UserDto;
import org.parkinglot.parkinglot3.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<UserDto> copyUsersToDto(List<User> users) {
        List<UserDto> dtos = new ArrayList<>();
        for (User user : users) {
            UserDto dto = new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    public List<UserDto> findAllUsers() {
        LOG.info("findAllUsers");
        try {
            TypedQuery<User> typedQuery = entityManager.createQuery(
                    "SELECT u FROM User u", User.class);
            List<User> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
