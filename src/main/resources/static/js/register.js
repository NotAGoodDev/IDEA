$(document).ready(function() {
    //Formulario de registro de creador
    $( "#form-register" ).submit(function( event ) {
        event.preventDefault();
        var c = {};

        var form = $( this );
        url = "auth/register";
        c.username = form.find( "input[name='username']" ).val();
        c.password = form.find( "input[name='password']" ).val();
        c.email = form.find( "input[name='email']" ).val();
        c.roles = ["ROLE_ADMIN", "ROLE_USER"];

        ApiController.post(url, c, function (response) {
        })
    });
})