
function sendHate(post_id, span_tag) {
    console.log(post_id);
    $.ajax({
        url: "/hate",
        type: "POST",
        cache: false,
        async: true,
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify({
            "postId": post_id
        }),
        datatype: "json",
        success: function (data) {
            data = JSON.parse(data);
            if (data["status"])
                span_tag.html(data["hate_count"]);
            else
                showNotification(data["status_message"])
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            showNotification("مشکلی پیش‌ آمده، لطفا دوباره امتحان کنید!");
        }
    });
}

function showNotification(message) {

    $('#notification_message').text(message);
    $('#notification_modal').modal('show');
}
