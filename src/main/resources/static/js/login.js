$(document).ready(function() {
    //Formulario de login
    $( "#form-login" ).submit(function( event ) {
        event.preventDefault();
        let params = {};
        let form = $(this);

        params.email = form.find("input[name='email']").val();
        params.password = form.find("input[name='password']").val();

        ApiController.post("auth/login", params, function (response) {
        })
    });
});