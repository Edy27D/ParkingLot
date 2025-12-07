<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:pageTemplate pageTitle="Edit Car">

  <h1>Edit Car</h1>

  <div class="container">
    <form class="needs-validation"
          novalidate
          method="POST"
          action="${pageContext.request.contextPath}/EditCar">

      <input type="hidden" name="id" value="${car.id}"/>

      <div class="mb-3">
        <label class="form-label">License plate</label>
        <input class="form-control"
               type="text"
               name="license_plate"
               value="${car.licensePlate}"
               required>
      </div>

      <div class="mb-3">
        <label class="form-label">Parking spot</label>
        <input class="form-control"
               type="text"
               name="parking_spot"
               value="${car.parkingSpot}"
               required>
      </div>

      <div class="mb-3">
        <label class="form-label">Owner</label>
        <select name="owner_id" class="form-select" required>
          <c:forEach var="user" items="${users}">
            <option value="${user.id}"
              ${user.username == car.ownerName ? "selected" : ""}>
                ${user.username}
            </option>
          </c:forEach>
        </select>
      </div>

      <button class="btn btn-primary btn-lg">Save Changes</button>

    </form>
  </div>
  </div><form class="needs-validation" novalidate method="POST">

</t:pageTemplate>
