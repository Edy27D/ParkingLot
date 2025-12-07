package org.parkinglot.parkinglot3.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.parkinglot.parkinglot3.ejb.CarsBean;
import org.parkinglot.parkinglot3.ejb.UsersBean;
import org.parkinglot.parkinglot3.common.UserDto;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddCar", value = "/AddCar")
public class AddCar extends HttpServlet {

    @Inject
    UsersBean usersBean;

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        // luăm toți userii pentru dropdown
        List<UserDto> users = usersBean.findAllUsers();
        request.setAttribute("users", users);

        request.getRequestDispatcher("/WEB-INF/pages/addCar.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String licensePlate = request.getParameter("license_plate");
        String parkingSpot  = request.getParameter("parking_spot");
        String ownerIdParam = request.getParameter("owner_id");

        Long ownerId = null;
        if (ownerIdParam != null && !ownerIdParam.isBlank()) {
            ownerId = Long.parseLong(ownerIdParam);
        }

        carsBean.createCar(licensePlate, parkingSpot, ownerId);

        response.sendRedirect(request.getContextPath() + "/Cars");
    }
}
