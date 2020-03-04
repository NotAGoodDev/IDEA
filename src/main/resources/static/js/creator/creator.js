$(document).ready(function() {
    //Formulario de registro de creador
    $( "#form-registro-creador" ).submit(function( event ) {
        event.preventDefault();
        var c = {};

        var form = $( this ),
        name = form.find( "input[name='name']" ).val(),
        url = "http://localhost:8080/api/auth/creator/register";
        c.name = form.find( "input[name='name']" ).val();
        c.lastName = form.find( "input[name='lastName']" ).val();
        c.birthDate = form.find( "input[name='birthDate']" ).val();
        c.telephone = form.find( "input[name='telephone']" ).val();
        c.address = form.find( "input[name='address']" ).val();
        c.email = form.find( "input[name='email']" ).val();
        c.username = form.find( "input[name='username']" ).val();
        c.password = form.find( "input[name='password']" ).val();
        c.active = true;
        $.ajax({
            type:"POST", // la variable type guarda el tipo de la peticion GET,POST,..
            url:url, //url guarda la ruta hacia donde se hace la peticion
            headers: {
                'Content-Type': 'application/json'
            },
            contentType : "application/json; charset=utf-8",
            mimeType: "application/json",
            dataType : 'json',
            data: JSON.stringify(c),
            success:function(datos){ //success es una funcion que se utiliza si el servidor retorna informacion
                 console.log(datos.promedio)
             },
             error: function() {
                console.log("No se ha podido obtener la información");
            }
        });
    });
})
