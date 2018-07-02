
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
        }
    });
}

function showNotification(message) {

    $('#notification_message').text(message);
    $('#notification_modal').modal('show');
}

function update_cats(cat_text, cat_container)
{
    cat_container.empty();
    var cat_arr = cat_text.split(" ");
    var i;
    for (i = 0; i < cat_arr.length; i++) {
        if(cat_arr[i].length > 0)
            cat_container.append("<a href=\"/category/" + cat_arr[i] +"\"><span class=\"w3-tag w3-small w3-theme-d3\" >" +cat_arr[i]+" </span></a>\n")
    }
}