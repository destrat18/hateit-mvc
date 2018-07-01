<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<%--
  Created by IntelliJ IDEA.
  User: des
  Date: 6/18/18
  Time: 12:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>

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

        function getPostId() {
            var pathname = window.location.pathname;
            return pathname.split("/")[2];
        }

        function sendComment(postId, content)
        {

            if(content.length == 0)
            {
                showNotification("متن تنفر نباید خالی باشد");
            }
            else
            {
                $.ajax({
                        type: 'post', // it's easier to read GET request parameters
                        url: '/comment',
                        dataType: 'JSON',
                        data: {
                            post_id: postId,
                            content: content
                        },
                        success: function (response) {
                            if(!response["error_happen"])
                            {
                                $("#comment_container").prepend("" +
                                    "                    <div class=\"w3-container w3-card w3-white w3-round w3-margin\">\n" +
                                    "\n" +
                                    "                        <div class=\"media\">\n" +
                                    "                            <div class=\"media-right\">\n" +
                                    "                                <img src=\" " + response["image"] + " \" class=\"media-object\" style=\"width:60px\">\n" +
                                    "                            </div>\n" +
                                    "                            <div class=\"media-body\">\n" +
                                    "                                <h4 class=\"media-heading\"> " + response["name"] + "</h4>\n" +
                                    "                                <h6>" + response["date"] +" </h6>\n" +
                                    "                                <p>" + content + "</p>\n" +
                                    "                            </div>\n" +
                                    "                        </div>\n" +
                                    "\n" +
                                    "                    </div>"
                                );
                            }
                        }
                    }
                );

            }
        }
        function deletePost(postId)
        {
            console.log("daelete");
            $.ajax({
                    type: 'delete', // it's easier to read GET request parameters
                    url: window.location.pathname,
                    success: function (response) {
                        console.log(response);
                        if(response["success"])
                            window.location.replace("/");
                        else
                            showNotification("شما نمی‌توانید این پست را حذف کنید")
                    }
                }
            );
        }

        $(document).ready(function(){
            $(".hate_button").click(function () {
                sendHate(getPostId(), $(this).find("span"));
            });

            $("#send_comment").click(function () {
                var content = $('#comment_content').val();
                sendComment(getPostId(), content);
            });

            $("#delete_post").click(function () {
                deletePost(getPostId());
            });

        });
    </script>
</head>
<body class="w3-theme-l5">
    <%--notification popup--%>
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
                    <a href="#" class="w3-bar-item w3-button w3-hide-small w3-left w3-padding-large w3-hover-white" title="My Account">
                        <img src="${sessionScope.user.image}" class="w3-circle" style="height:23px;width:23px">
                            ${sessionScope.user.name}
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="/login" class="w3-right w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" title="ورود"><i class="fa fa-sign-up fa-flip-horizontal"></i> ورود</a>
                    <a href="/sign-up" class="w3-right w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" title="Login"><i class="fa fa-user-plus"></i> ثبت‌نام</a>

                </c:otherwise>
            </c:choose>

        </div>
    </div>

    <!-- Page Container -->
    <div class="w3-container w3-content" style="max-width:1400px;margin-top:80px">

        <!-- The Grid -->
        <div class="w3-row">
            <!-- Middle Column -->
            <div class="w3-col m9">

            <div class="w3-container w3-card w3-white w3-round w3-margin"><br>
                <h2>${post.title}</h2>
                <p>${post.content}</p>
                <c:forEach items="${post.categories}" var="cat">
                    <a href="/category/${cat}"><span class="w3-tag w3-small w3-theme-d3" >${cat}</span></a>
                </c:forEach>
                <hr class="w3-clear">
                <button id="${post.id}" type="button" class="w3-button w3-theme-d1 w3-margin-bottom hate_button"><i class="fa fa-thumbs-down"></i>  متنفرم<span class="badge">${post.hatesCount}</span></button>
            </div>

            <c:choose>
                <c:when test="${!empty sessionScope.user}">
                    <div class="w3-container w3-card w3-white w3-round w3-margin">
                        <div class="w3-container w3-padding">
                            <textarea id="comment_content" name="content" style="width: 100%; height: 100px"></textarea>
                            <br/>
                            <br/>
                            <button id="send_comment" type="button" class="w3-button w3-theme"><i class="fa fa-pencil"></i>  ابراز تنفر</button>
                        </div>
                    </div>
                    <br>
                </c:when>
                <c:otherwise>
                    <!-- Alert Box -->
                    <div class="w3-container w3-card w3-white w3-round w3-margin w3-container w3-display-container w3-round w3-theme-l4 w3-border w3-theme-border w3-margin-bottom w3-hide-small">
                        <span onclick="this.parentElement.style.display='none'" class="w3-button w3-theme-l3 w3-display-topleft">
                          <i class="fa fa-remove"></i>
                        </span>
                        <H2>سلام!</H2>
                        <p>برای ابراز تنفر لطفا <a href="/login.jsp">وارد</a> شوید</p>
                    </div>
                </c:otherwise>
            </c:choose>
            <div id="comment_container">
                <c:forEach items="${post.comments}" var="comment">
                    <div class="w3-container w3-card w3-white w3-round w3-margin">

                        <div class="media">
                            <div class="media-right">
                                <img src="${comment.user.image}" class="media-object" style="width:60px">
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">${comment.user.name}</h4>
                                <h6>${comment.date}</h6>
                                <p>${comment.content}</p>
                            </div>
                        </div>

                    </div>
                </c:forEach>
            </div>
                <!-- End Middle Column -->
            </div>

            <!-- Left Column -->
            <div class="w3-col m3">
                <!-- Profile -->
                <div class="w3-card w3-round w3-white">
                    <div class="w3-container">
                        <h4 class="w3-center">${post.user.name}</h4>
                        <c:choose>
                            <c:when test="${empty post.user.image}">
                                <p class="w3-center"><img src="https://www.w3schools.com/w3images/avatar1.png" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
                            </c:when>
                            <c:otherwise>
                                <p class="w3-center"><img src="${post.user.image}" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
                            </c:otherwise>

                        </c:choose>
                        <hr>
                        <p><i class="fa fa-pencil fa-fw w3-margin-left w3-text-theme"></i> ${post.user.shortDescription}</p>
                        <p><i class="fa fa-home fa-fw w3-margin-left w3-text-theme"></i> ${post.user.location}</p>
                        <p><i class="fa fa-heart fa-fw w3-margin-left w3-text-theme"></i> ${post.user.age} سال</p>
                        <p><i class="fa fa-star fa-fw w3-margin-left w3-text-theme"></i> ${post.user.value} امتیاز دارد</p>
                    </div>
                </div>
                <br>

                <div class="w3-card w3-round w3-white w3-hide-small">
                    <div class="w3-container">
                        <h3>موضوع‌ها</h3>
                        <p>
                            <c:forEach items="${post.categories}" var="cat">
                                <a href="/category/${cat}"><span class="w3-tag w3-small w3-theme-d5" >${cat}</span></a>
                            </c:forEach>
                        </p>
                    </div>
                </div>
                <br>
                <c:if test="${sessionScope.user.canDelete == true}">
                    <div class="w3-card w3-round w3-white w3-center">
                        <div class="w3-container">
                            <p>تبریک!</p>
                            <p>شما می‌توانید این پست را حذف کنید!</p>
                            <p><button id="delete_post" class="w3-button w3-block" style="background-color: indianred;">حذف</button></p>
                        </div>
                    </div>
                    <br>
                </c:if>

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
