$(document).ready(function () {
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });

    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
});

const removeHtmlElement = (arr) => {
    for (var i = arr.length - 1; i >= 0; --i) {
        arr[i].remove();
    }
}

// Wait for window load
$(window).on("load", function() {
    console.log("window loading");
    // Animate loader off screen
    $("#loader").fadeOut("slow");
    $("body .wrapper").fadeIn(600);
});