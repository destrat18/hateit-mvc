<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<%--
  Created by IntelliJ IDEA.
  User: des
  Date: 6/18/18
  Time: 12:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Hate It</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-blue-grey.css">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<c:url value="/static/js/main.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/static/css/main.css" />">
    <script>

        $(document).ready(function(){
            $("button").click(function () {
                sendHate(this["id"], $(this).find("span"));
            })
        });
    </script>
</head>
<body class="w3-theme-l5">
<div class="modal fade" id="notification_modal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-body">
                <p id="notification_message"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">باشه</button>
            </div>
        </div>

    </div>
</div>
<!-- Navbar -->
<div class="w3-top">
    <div class="w3-bar w3-theme-d2 w3-left-align w3-large">
        <a href="/" class="w3-right w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" title="Login"><i class="fa fa-home"></i> هیت‌ایت</a>

        <c:choose>
            <c:when test="${!empty sessionScope.user}">
                <a href="/new-post" class="w3-right w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" title="پست جدید"><i class="fa fa-plus fa-flip-horizontal"></i> پست جدید</a>
                <a href="/logout" class="w3-left w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" title="خروج"><i class="fa fa-sign-out fa-flip-horizontal"></i> خروج</a>
                <a href="/profile" class="w3-bar-item w3-button w3-hide-small w3-left w3-padding-large w3-hover-white" title="My Account">
                    <img src="${sessionScope.user.image}" class="w3-circle" style="height:23px;width:23px">
                        ${sessionScope.user.name}
                </a>
            </c:when>
            <c:otherwise>
                <a href="/login" class="w3-right w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" title="ورود"><i class="fa fa-sign-in fa-flip-horizontal"></i> ورود</a>
                <a href="/sign-up" class="w3-right w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" title="Login"><i class="fa fa-user-plus"></i> ثبت‌نام</a>

            </c:otherwise>
        </c:choose>

    </div>
</div>

    <!-- Page Container -->
    <div class="w3-container w3-content" style="max-width:1400px;margin-top:80px">

        <!-- The Grid -->
        <div class="w3-row">
            <div class="w3-col m3">
                <div class="w3-card w3-round w3-white w3-hide-small">
                    <div class="w3-container">
                    </div>
                </div>
            </div>

            <div class="w3-col m6">
                <!-- Profile -->
                    <div class="w3-card w3-round w3-white">
                        <div class="w3-container">
                            <h4 class="w3-center">${sessionScope.user.name}</h4>
                            <c:choose>
                                <c:when test="${empty sessionScope.user.image}">
                                    <p class="w3-center"><img src="https://www.w3schools.com/w3images/avatar1.png" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
                                </c:when>
                                <c:otherwise>
                                    <p class="w3-center"><img src="${sessionScope.user.image}" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
                                </c:otherwise>

                            </c:choose>
                            <hr>
                            <form enctype="multipart/form-data" action="edit-user" method="post" name="postForm">
                                <h5 class="w3-opacity">چه اسمی رو نمایش بدیم؟</h5>
                                <input type="text" placeholder="مثلا سروش!" class="w3-border w3-padding" name="name" value="${sessionScope.user.name}" style="width: 100%;" autofocus/>
                                <br>
                                <h5 class="w3-opacity">توضیح مختصر می‌دی؟</h5>
                                <input type="text" placeholder="مثلا من برنامه نویس هستم!" class="w3-border w3-padding" name="shortDescription" value="${sessionScope.user.shortDescription}" style="width: 100%;" autofocus/>
                                <br>
                                <h5 class="w3-opacity">کجا زندگی می‌کنی؟</h5>
                                <input type="text" placeholder="مثلا یزد!" class="w3-border w3-padding" name="location" value="${sessionScope.user.location}" style="width: 100%;" autofocus/>
                                <br>
                                <h5 class="w3-opacity">سال تولد؟</h5>
                                <input type="text" class="w3-border w3-padding" name="year" value="${sessionScope.user.year}" style="width: 100%;" autofocus/>
                                <br>
                                <h5 class="w3-opacity">ماه تولد؟</h5>
                                <input type="text" class="w3-border w3-padding" name="month" value="${sessionScope.user.month}" style="width: 100%;" autofocus/>
                                <br>
                                <h5 class="w3-opacity">روز تولد؟</h5>
                                <input type="text" class="w3-border w3-padding" name="day" value="${sessionScope.user.day}" style="width: 100%;" autofocus/>
                                <br>
                                <h5 class="w3-opacity">عکس جدید؟</h5>
                                <input type="file" name="file">
                                <br>
                                <hr>
                                <button type="submit" class="w3-right w3-button w3-theme"><i class="fa fa-pencil"></i>  ذخیره</button>
                                <br>
                                <br>
                            </form>


                        </div>
                    </div>
                    <br>
                <!-- End Left Column -->
            </div>
            <!-- End Grid -->
        </div>

        <!-- End Page Container -->
    </div>
    <br>

    <!-- Footer -->
    <footer class="w3-container w3-theme-d3 w3-padding-16">
        <h5>این یک پروژه آزمایشی می‌باشد</h5>
    </footer>

    <footer class="w3-container w3-theme-d5">
        <p>توسط :/</p>
    </footer>


</body>
</html>
