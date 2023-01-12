<%--
  ~ Licensed to the Apache Software Foundation (ASF) under one
  ~ or more contributor license agreements.  See the NOTICE file
  ~ distributed with this work for additional information
  ~ regarding copyright ownership.  The ASF licenses this file
  ~ to you under the Apache License, Version 2.0 (the
  ~ "License"); you may not use this file except in compliance
  ~ with the License.  You may obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing,
  ~ software distributed under the License is distributed on an
  ~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  ~ KIND, either express or implied.  See the License for the
  ~ specific language governing permissions and limitations
  ~ under the License.
  --%>
<%@ include file="include.jsp" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="../my-app-0.0.1-SNAPSHOT/css/style.css">
	<!-- script section -->
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<div class="main">
		<div class="row desktop-header">
			<div class="col-xs-12 col-md-8 col-lg-8">
				<h1>Bitte logge dich ein</h1>
			</div>
		</div>
		<div class="section">
			<div class="row">
				<div class="col-xs-12 col-md-12 col-lg-12">
					<form name="loginform" action="" method="post">
					<%@page import = "java.io.PrintWriter" %>
					<%
						PrintWriter outwrite = response.getWriter();
						String usr = "";
						if(request.getParameter("username") != null)
						{
							usr = request.getParameter("username");
						}
						else{
							usr = "";
						}
					%>
					    <div class="form__row">
					    	<label class="hiddenLabel" for="username">Username</label>
					    	<input type="text" name="username" maxlength="30" value="<%=usr%>" placeholder="Username">
					    </div>
						<div class="form__row">
							<label class="hiddenLabel" for="password">Passwort</label>
							<input type="password" name="password" maxlength="30" placeholder="Passwort">
						</div>
					    <div class="form__row">
					    	<input type="checkbox" name="rememberMe">
					    	<label for="rememberMe" style="margin-left:20px;">Remember Me</label>
					    </div>
					    <div class="form__row">
					    	<input type="submit" class="submit__btn" name="submit" value="Login">
					    </div>
					</form>
				</div>
			</div>
		</div>



</body>
</html>
