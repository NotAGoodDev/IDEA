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
        params.lastName = form.find( "input[name='cif']" ).val();
        params.telephone = form.find( "input[name='telephone']" ).val();
        params.address = form.find( "input[name='address']" ).val();
        params.active = true;
        params.type = "Enterprise";

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
    let fields = ["username", "password", "email", "name", "cif", "telephone", "address"];

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

/*
    //CAN USE FOR THE FUTURE
function enableCardFields() {
    let fields = ["nameCard", "cardNumber", "dateExp", "cvv"];

    for (let i in fields) {

        document.getElementById(fields[i]).readOnly = false;
        document.getElementById(fields[i]).type = "text";
    }

    let cd = document.getElementsByClassName("hidden");            //Show card data
    for (let j = 0; j < cd.length; j++) {                                     //Dont foreach -> it loses
        cd[j].style.display = "block";
    }

    let icon = document.getElementById("editCard");
    icon.style.display = "none";

    let buttons = ["btn-success", "btn-cancel"];
    for (let k in buttons) {
        let button = document.getElementById(buttons[k]);
        button.style.display = "block";
    }
}
*/