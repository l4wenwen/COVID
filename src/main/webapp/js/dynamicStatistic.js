$(document).ready(function() {
    $(".state .btn").click(
        function () {
            $.ajax({
                url: $(this).children(".url").html(),
                dataType: "json",
                type: "POST",
                success: function (res) {
                    $(".chart .item").remove();
                    for(let index in res) {
                        let appendstr = "<tr class='item'>"
                        appendstr += "<td>" + res[index]["userName"] + "</td>";
                        appendstr += "<td>" + res[index]["userNum"] + "</td>";
                        appendstr += "<td>" + res[index]["sex"] + "</td>";
                        appendstr += "</tr>";
                        $(".chart").append(appendstr);
                    }
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }
    );
});