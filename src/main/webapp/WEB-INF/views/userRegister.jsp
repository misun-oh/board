<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-5 col-md-offset-5">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">회원가입</h3>
                    </div>
                    <div class="panel-body">
                         <form role="form" action="/userCreate" method="post">
                              <div class="form-inline">
                                  <label>UserId</label><br>
                                  <input class="form-control" name="id"> <button type="button" class="btn btn-default">중복 확인</button>
                                  <p class="help-block"> </p>
                              </div>
                              <div class="form-group">
                                  <label>password</label><br>
                                  <input class="form-control" placeholder="비밀번호"  name="pwd">
                                  <p class="help-block"> </p>
                                  <input class="form-control" placeholder="비밀번호 확인">
                                  <p class="help-block">10자리 이상 특수문자를 포함 합니다.</p>
                              </div>
                              <div class="form-group">
                                  <label>name</label>
                                  <input class="form-control"  name="name">
                                  <p class="help-block"> </p>
                              </div>
                              <div class="form-group">
                                  <label>email</label>
                                  <input class="form-control" placeholder="test@gmail.com" type=email  name="email">
                                  <p class="help-block"> </p>
                              </div>
							  
                              
                              <button class="btn btn-lg btn-success btn-block">회원가입</button>
						</form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="/resources/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/resources/dist/js/sb-admin-2.js"></script>

</body>

</html>

