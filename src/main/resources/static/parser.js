
function handleButtonClick(event) {
    let form = $("#sql-form")[0];
    let formData = new FormData(form);

    if (formData.get("inputQuery") === ""){
        alert("입력값을 넣어주세요!");
        event.preventDefault();
    }else {
        let action = "";
        let alarm = ""
        if (event.target.id === "execute-button") {
            action = "/";
            alarm = "쿼리 파싱 완료"
        } else if (event.target.id === "addKeyword-button") {
            action = "/addKeyword";
            alarm = "예약어 추가 완료"
        }else {
            action = "/addFunction";
            alarm = "함수 추가 완료"
        }

        $.ajax({
            url: action,
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function(result) {
                console.log(result);
                alert(alarm);
                location.href = "/";
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error("Error submitting the form: " + textStatus, errorThrown);
            }
        });
    }
}

// execute-button 클릭 이벤트 핸들러
$("#execute-button").click(handleButtonClick)

// addKeyword-button 클릭 이벤트 핸들러
$("#addKeyword-button").click(handleButtonClick);

// addFunction-button 클릭 이벤트 핸들러
$("#addFunction-button").click(handleButtonClick);
