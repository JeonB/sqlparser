
$("#execute-button").click(function(event){

    let form = $("#sql-form")[0];
    let formData = new FormData(form);

    let action = "/";

    $.ajax({
        url: action,
        type: "POST",
        data: formData,
        processData: false, // jQuery가 데이터를 프로세싱하는 것을 방지
        contentType: false, // jQuery가 데이터 타입을 설정하는 것을 방지
        success: function(result) {
            console.log(result);
            location.href="/";
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("Error submitting the form: " + textStatus, errorThrown);
        }
    });
});