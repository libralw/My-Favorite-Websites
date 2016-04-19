<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-top.jsp" />

		<div class="col-md-8 column" id="main">
			
			<c:forEach var="error" items="${errors}">
				<p style="color:red"> ${error} </p>
			</c:forEach>
			
			<form method="post" action="login.do" class="form-horizontal" role="form">
				<div class="form-group">
					 <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
					<div class="col-sm-10">
						<input class="form-control" id="inputEmail3" type="text" name="email" value="${form.email }">
					</div>
				</div>
				<div class="form-group">
					 <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<input class="form-control" id="inputPassword3" type="password" name="password" value="">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						 <input type="submit" class="btn btn-primary" name="action" value="Login">
						 &nbsp; or click <a href="register.do">here</a> to register as a new user.
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<jsp:include page="template-bottom.jsp" />