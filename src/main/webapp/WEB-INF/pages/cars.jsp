<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:pageTemplate pageTitle="Cars">
  <h1>Cars</h1>
  <a class="btn btn-primary btn-lg"
     href="${pageContext.request.contextPath}/AddCar">
    Add Car
  </a>
  <form method="POST" action="${pageContext.request.contextPath}/Cars">

    <div class="container text-center">
      <c:forEach var="car" items="${cars}">
        <div class="row align-items-center mb-2">

          <!-- Checkbox -->
          <div class="col-1">
            <input type="checkbox" name="car_ids" value="${car.id}" />
          </div>

          <!-- License plate -->
          <div class="col">
              ${car.licensePlate}
          </div>

          <!-- Parking spot -->
          <div class="col">
              ${car.parkingSpot}
          </div>

          <!-- Owner -->
          <div class="col">
              ${car.ownerName}
          </div>

          <!-- Edit button -->
          <div class="col">
            <a class="btn btn-secondary btn-sm"
               href="${pageContext.request.contextPath}/EditCar?id=${car.id}">
              Edit
            </a>
          </div>

        </div>
      </c:forEach>
    </div>

    <!-- Delete selected cars -->
    <button class="btn btn-danger btn-lg mt-3" type="submit">
      Delete Selected Cars
    </button>

  </form>

  <h5>Free parking spots: ${numberOfFreeParkingSpots}</h5>
</t:pageTemplate>
