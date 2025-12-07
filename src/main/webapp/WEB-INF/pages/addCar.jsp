<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:pageTemplate pageTitle="Add Car">
  <h1>Add Car</h1>

  <div class="container">
    <div class="row">
      <div class="col-md-8">

        <form class="needs-validation"
              novalidate
              method="POST"
              action="${pageContext.request.contextPath}/AddCar">

          <!-- License plate -->
          <div class="mb-3">
            <label for="licensePlate" class="form-label">License plate</label>
            <input type="text"
                   class="form-control"
                   id="licensePlate"
                   name="license_plate"
                   required>
            <div class="invalid-feedback">
              Please provide a license plate.
            </div>
          </div>

          <!-- Parking spot -->
          <div class="mb-3">
            <label for="parkingSpot" class="form-label">Parking spot</label>
            <input type="text"
                   class="form-control"
                   id="parkingSpot"
                   name="parking_spot"
                   required>
            <div class="invalid-feedback">
              Please provide a parking spot.
            </div>
          </div>

          <!-- Owner -->
          <div class="mb-3">
            <label for="owner" class="form-label">Owner</label>
            <select class="form-select"
                    id="owner"
                    name="owner_id"
                    required>
              <option value="">Choose...</option>
              <c:forEach var="user" items="${users}">
                <option value="${user.id}">
                    ${user.username} (${user.email})
                </option>
              </c:forEach>
            </select>
            <div class="invalid-feedback">
              Please select an owner.
            </div>
          </div>

          <!-- Save button -->
          <button class="btn btn-primary btn-lg" type="submit">
            Save
          </button>
        </form>

      </div>
    </div>
  </div><form class="needs-validation" novalidate method="POST">
</t:pageTemplate>
