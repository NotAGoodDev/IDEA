$(document).ready(function() {
    //Formulario de registro de creador
    $( "#form-registro-creador" ).submit(function( event ) {
        event.preventDefault();
        var c = {};

        var form = $( this ),
        name = form.find( "input[name='name']" ).val(),
        url = "auth/creator/register";
        c.name = form.find( "input[name='name']" ).val();
        c.lastName = form.find( "input[name='lastName']" ).val();
        c.birthDate = form.find( "input[name='birthDate']" ).val();
        c.telephone = form.find( "input[name='telephone']" ).val();
        c.address = form.find( "input[name='address']" ).val();
        c.email = form.find( "input[name='email']" ).val();
        c.username = form.find( "input[name='username']" ).val();
        c.password = form.find( "input[name='password']" ).val();
        c.active = true;

        ApiController.post(url, c, function (response) {

        })
    });
})