$(document).ready(function() {

//Formulario de registro de creador
    $( "#form-register" ).submit(function( event ) {
        event.preventDefault();
        let params = {};
        let form = $( this );

        params.username = form.find( "input[name='username']" ).val();
        params.password = form.find( "input[name='password']" ).val();
        params.email = form.find( "input[name='email']" ).val();
        params.type = $( "#selectType" ).val();


        if(params.type == "Creador")
        {
            params.name = form.find( "input[name='nameCreator']" ).val();
            params.lastName = form.find( "input[name='lastName']" ).val();
            params.birthDate = form.find( "input[name='birthDate']" ).val();
            params.telephone = form.find( "input[name='telephoneCreator']" ).val();
            params.address = form.find( "input[name='addressCreator']" ).val();
            params.active = true;
        }
        else if (params.type == "Empresa")
        {
            params.name = form.find( "input[name='nameEnterprise']" ).val();
            params.cif = form.find( "input[name='cif']" ).val();
            params.address = form.find( "input[name='addressEnterprise']" ).val();
            params.telephone = form.find( "input[name='telephoneEnterprise']" ).val();
            params.cardNumber = form.find( "input[name='cardNumber']" ).val();
            params.remaining_ideas = 0;
            params.active = true;
        }

        $.ajax({
            url         : "/api/auth/register",
            type        : "POST",
            data        : JSON.stringify(params),
            headers: {
                'Content-Type': 'application/json'
            },
            contentType : "application/json; charset=utf-8",
            mimeType: "application/json",
            dataType : 'json',
            success:    function(){window.location.href = "/login";},
            error:  function(){window.location.href = "/register";}

        }); //MOSTRAR MENSAJES DE ERROR O EXITO

        /**
         * "ApiController" funciona cuando no le sigue ninguna instruccion detras
         *  o en el metodo request de la Clase se establece 'Async=false'
         * 
         *      ApiController.post("auth/register", params, function (response) {})
         *      window.location.href = "/login";
         */
    });

    $( "#selectType" ).on('change',function () {
        let value = $(this).val();
        let x;

        if(value == "Creador")
        {
            x = document.getElementById("creator");
            x.style.display = "block";

            x = document.getElementById("enterprise");
            x.style.display = "none";
        }
        else if (value = "Empresa")
        {
            x = document.getElementById("enterprise");
            x.style.display = "block";

            x = document.getElementById("creator");
            x.style.display = "none";
        }

    })

    $( "#selectType" ).ready(function () {
        let x;

        x = document.getElementById("creator");
        x.style.display = "block";

        x = document.getElementById("enterprise");
        x.style.display = "none";


    })

})

