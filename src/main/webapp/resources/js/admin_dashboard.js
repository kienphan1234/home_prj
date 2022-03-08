function searchHomeAdmin(e) {
    var data = $("#frmSearchAdmin").serializeObject();
    data["pageNumber"] = e.getAttribute("data-current_page");
    data["type"] = "1";
    $.ajax({
        url: "/admin/dashboard",
        cache: true,
        dataType: "json",
        type: "GET",
        data: data,
        beforeSend: function () {
            $("body .wrapper").css('opacity', 0.3);
            $("#loader").show();
        },
        success: function (result) {
            const priceListTable = document.getElementById("priceListTable");
            const oldTrTag = priceListTable.getElementsByTagName("tr");
            removeHtmlElement(oldTrTag);

            const priceList = result.homePriceList;
            for (const key in priceList) {
                const newTrTag = document.createElement("tr");
                newTrTag.id = "row-" + priceList[key].id;
                var html = "<td>Tháng <span>" + priceList[key].createdAt + "</span></td>";
                html += "<td><span>" + priceList[key].roomDescription + "</span></td>";
                html += "<td><span>" + priceList[key].roomPrice + "</span></td>";
                html += "<td><span>" + priceList[key].electricPrice + "</span></td>";
                html += "<td><span>" + priceList[key].waterPrice + "</span></td>";
                html += "<td><span>" + priceList[key].internetPrice + "</span></td>";
                html += "<td><span>" + priceList[key].garbagePrice + "</span></td>";
                html += "<td><span>" + priceList[key].livingPrice + "</span></td>";
                html += "<td><span>" + priceList[key].total + "</span></td>";
                html += "<td><span><button type='button' id='btnStatus-" + priceList[key].id + "'" + " class='btn " + (priceList[key].status == 0 ? "btn-danger" : "btn-search") + "'" +
                    " data-status='" + priceList[key].status + "'" +
                    " onclick='confirmDeposit(this)'" +
                    " data-id='" + priceList[key].id + "'" +
                    " data-roomName='" + priceList[key].roomDescription + "'" +
                    " data-createdAt='" + priceList[key].createdAt + "'>" + (priceList[key].status == 0 ? "Chưa nộp" : "Đã nộp")  + "</button></span></td>";
                html += "<td><span>" + priceList[key].note + "</span></td>";
                if (priceList[key].depositedAt != null) {
                    html += "<td><span>" + priceList[key].depositedAt + "</span></td>";
                }
                newTrTag.innerHTML = html;
                priceListTable.appendChild(newTrTag);
            }

            const ulPaginations = document.getElementById("pagination");
            const oldLiTags = ulPaginations.getElementsByTagName("li");
            removeHtmlElement(oldLiTags);
            if (result.totalPage > 1) {
                var html = "";
                if (result.currentPage != 1) {
                    html += "<li class='page-item'><a class='page-link' href='#' onclick='searchHomeAdmin(this)' data-current_page='" + (result.currentPage - 1) + "'>Previous</a></li>";
                }
                for (let i = 1; i <= result.totalPage; i++) {
                    const activeClass = (i === result.currentPage) ? 'active' : '';
                    html += "<li class='page-item " + activeClass + "'><a class='page-link' href='#' onclick='searchHomeAdmin(this)' data-current_page='" + i + "'>" + i + "</a></li>";
                }
                if (result.currentPage < result.totalPage) {
                    html += "<li class='page-item'><a class='page-link' href='#' onclick='searchHomeAdmin(this)' data-current_page='" + (result.currentPage + 1) + "'>Next</a></li>";
                }
                ulPaginations.innerHTML = html;
            }
        },
        error: function(jqXhr, textStatus, errorMessage) {
            console.log("Error: ", errorMessage);
        },
        complete: function () {
            $("#loader").fadeOut("slow");
            $("body .wrapper").fadeIn(600);
            $("body .wrapper").css('opacity', 1);
        }
    });
}

function confirmDeposit(e) {
    const roomName = e.getAttribute("data-roomName");
    const createdAt = e.getAttribute("data-createdAt");
    const id = e.getAttribute("data-id");
    const data = {'id' : id};
    if (confirm("Bạn chắc chắn muốn chuyển trạng thái đóng tiền của \n" + roomName.trim() + ", tháng " + createdAt.trim() + " sang ĐÃ NỘP không?")) {
        $.ajax({
            url: "/admin/dashboard",
            cache: true,
            dataType: "json",
            type: "POST",
            data: $.param(data),
            beforeSend: function () {
                $("body .wrapper").css('opacity', 0.3);
                $("#loader").show();
            },
            success: function (result) {
                console.log(result);
                if (result.code == "200") {
                    const btn = document.getElementById("btnStatus-" + id);
                    btn.className = "btn btn-search";
                    btn.innerText = "Đã nộp";
                    btn.disabled = true;

                    const row = document.getElementById("row-" + id);
                    const tdTag = row.lastElementChild;
                    tdTag.innerHTML = result.depositedDate;
                }
            },
            error: function(jqXhr, textStatus, errorMessage) {
                console.log("Error: ", errorMessage);
            },
            complete: function () {
                $("#loader").fadeOut("slow");
                $("body .wrapper").fadeIn(600);
                $("body .wrapper").css('opacity', 1);
            }
        });
    }
}