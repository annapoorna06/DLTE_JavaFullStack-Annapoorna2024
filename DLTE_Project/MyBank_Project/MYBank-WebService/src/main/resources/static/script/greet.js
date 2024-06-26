$(document).ready(function () {
    function getUserName() {
        $.ajax({
            type: "GET",
            url: "/loans/name", // Assuming your endpoint is mapped to /web/name
            dataType: 'text', // Expecting text response
            contentType:"application/json;charset=utf-8",

            success: function (response) {
                $('#username').text("Welcome, " + response); // Display the name
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText);
                $('#username').text("Error fetching name");
            }
        });
    }
    getUserName();
});