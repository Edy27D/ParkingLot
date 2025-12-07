package org.parkinglot.parkinglot3.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.parkinglot.parkinglot3.ejb.CarsBean;
import org.parkinglot.parkinglot3.ejb.UsersBean;
import org.parkinglot.parkinglot3.common.CarDto;
import org.parkinglot.parkinglot3.common.UserDto;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditCar", value = "/EditCar")
public class EditCar extends HttpServlet {

    @Inject
    CarsBean carsBean;

    @Inject
    UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));

        CarDto car = carsBean.findCarById(id);   // DTO-ul pentru formular
        List<UserDto> users = usersBean.findAllUsers();

        request.setAttribute("car", car);
        request.setAttribute("users", users);

        request.getRequestDispatcher("/WEB-INF/pages/editCar.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        String licensePlate = request.getParameter("license_plate");
        String parkingSpot = request.getParameter("parking_spot");
        Long ownerId = Long.parseLong(request.getParameter("owner_id"));

        carsBean.updateCar(id, licensePlate, parkingSpot, ownerId);

        response.sendRedirect(request.getContextPath() + "/Cars");
    }
}
