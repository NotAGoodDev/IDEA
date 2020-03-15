$(document).ready(function() {
    //Formulario de registro de creador
    $( "#form-register" ).submit(function( event ) {
        event.preventDefault();
        var params = {};

        var form = $( this );
        params.username = form.find( "input[name='username']" ).val();
        params.password = form.find( "input[name='password']" ).val();
        params.email = form.find( "input[name='email']" ).val();
        params.type = "1";
        params.roles = ["ROLE_ADMIN", "ROLE_USER"];

        ApiController.post("auth/register", params, function (response) {
        })
    });
})