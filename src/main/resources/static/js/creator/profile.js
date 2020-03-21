$(document).ready(function() {
    //Formulario de registro de creador

    ApiController.get("0", "");                             //Falta poner el {id} que no le tenemos

    $( ".btn-success" ).submit(function( event ) {
        let params = {};
        let form = $( this );

        params.username = form.find( "input[name='username']" ).val();
        params.password = form.find( "input[name='password']" ).val();
        params.email = form.find( "input[name='email']" ).val();
        params.name = form.find( "input[name='name']" ).val();
        params.lastName = form.find( "input[name='lastName']" ).val();
        params.birthDate = form.find( "input[name='birthDate']" ).val();
        params.telephone = form.find( "input[name='telephone']" ).val();
        params.address = form.find( "input[name='address']" ).val();
        params.active = true;
        params.type = "Creador";

        ApiController.put("", params, function (response) {     //Falta poner el {id} que no le tenemos
        });
    })


    document.getElementById("btn-cancel").onclick = function()
    {
        window.location.href = "/";
    };
})


function enableFields()
{
    let fields = ["username", "password", "email", "name", "lastName", "birthDate", "telephone", "address"];

    for (let i in fields)
    {
        document.getElementById(fields[i]).readOnly = false;
        document.getElementById(fields[i]).type = "text";
    }

    let edit = document.getElementById("editIcon");
    edit.style.display = "none";


    let buttons = ["btn-success", "btn-cancel"];
    for (let i in buttons){
        let button = document.getElementById(buttons[i]);
        button.style.display = "block";
    }
}