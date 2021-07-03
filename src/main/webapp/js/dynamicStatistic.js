$(document).ready(function() {
    $(".state .btn").click(
        function () {
            $.ajax({
                url: $(this).children(".url").html(),
                dataType: "json",
                type: "POST",
                success: function (res) {
                    $(".chart .item").remove();
                    for (let index in res) {
                        let appendstr = "<tr class='item'>"
                        appendstr += "<td>" + res[index]["userName"] + "</td>";
                        appendstr += "<td>" + res[index]["userNum"] + "</td>";
                        appendstr += "<td>" + res[index]["sex"] + "</td>";
                        appendstr += "<td>" + res[index]["state"] + "</td>";
                        appendstr += "<td>" + res[index]["telephone"] + "</td>";
                        appendstr += "</tr>";
                        $(".chart").append(appendstr);
                    }
                },
                error: function (error) {
                    $(".chart .item").remove();
                    console.log(error);
                },
                beforeSend: function () {
                    $(".loader").css("visibility", "visible");
                },
                complete: function () {
                    $(".loader").css("visibility", "hidden");
                }
            });
        }
    );

    $("#changePwd").click(
        function () {
            $.ajax({
                url: "/user/changePassword",
                type: "POST",
                dateType: "json",
                data: {
                    pwd: $("#pwd").val(),
                    repwd: $("#repwd").val()
                },
                success: function (res) {
                    res = JSON.parse(res);
                    if (res.state == 0) {
                        $("#hide").html("修改成功");
                    } else {
                        $("#hide").html("修改失败");
                    }
                },
                error: function (error) {
                    console.log(error);
                },
                complete: function () {
                    $("#pwd").val("");
                    $("#repwd").val("");
                    $("#hide").css("display","block");
                }
            });
        }
    );

    $("#modal-button").click(
        function() {
            $(".modal").css("display","flex");
        }
    );

    $(".modal .close").click(
        function() {
            $(".modal").css("display","none");
        }
    );

    $(window).click(
        function(event) {
            if (event.target == $(".modal")[0]) {
                $(".modal").css("display","none");
            }
        }
    );
});