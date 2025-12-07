package org.parkinglot.parkinglot3.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.parkinglot.parkinglot3.ejb.UsersBean;
import org.parkinglot.parkinglot3.common.UserDto;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet {

    @Inject
    UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<UserDto> users = usersBean.findAllUsers();

        request.setAttribute("users", users);

        request.getRequestDispatcher("/WEB-INF/pages/users.jsp")
                .forward(request, response);
    }
}
