$(document).ready(function() {
    //Formulario de registro de creador
    let form = $( this );
    document.getElementsByName("imagen").forEach(function(item){
        item.style.display ="none";
    });
    document.getElementsByName("password").forEach(function(item){
        item.style.display ="none";
    })

    ApiController.get("auth/sessionId","",true).then(function(id){
        ApiController.get("creator/id/"+id,"",true).then(function(creator){
            document.getElementById("nombre-usuario").innerHTML = "Bienvenido, "+ creator.username;
            document.getElementById("username").setAttribute("value",creator.username);
            document.getElementById("name").setAttribute("value",creator.name);
            document.getElementById("email").setAttribute("value",creator.email);
            document.getElementById("lastName").setAttribute("value",creator.lastName);
            document.getElementById("telephone").setAttribute("value",creator.telephone);
            document.getElementById("address").setAttribute("value",creator.address);
            var date  = creator.birthDate.split("T");
            document.getElementById("birthDate").setAttribute("value",date[0]);
        });
    });  

    $( ".btn-success" ).submit(function( event ) {
        let params = {};

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
    });

    $("#change-pass").click(function(){
        changePass();
    });
})


function enableFields()
{
    let fields = ["username", "password", "email", "name", "lastName", "birthDate", "telephone", "address"];

    document.getElementsByName("imagen").forEach(function(item){
        item.style.display ="block";
    });
    document.getElementsByName("password").forEach(function(item){
        item.style.display ="block";
    });

    for (let i in fields)
    {
        document.getElementById(fields[i]).readOnly = false;
        document.getElementById(fields[i]).type = "text";
    }

    document.getElementById("birthDate").type = "date";

    let edit = document.getElementById("editIcon");
    edit.style.display = "none";


    let buttons = ["btn-success", "btn-cancel"];
    for (let i in buttons){
        let button = document.getElementById(buttons[i]);
        button.style.display = "block";
    }

}

function changePass(){
    var pass = document.getElementById("password").value;
    var newpass =  document.getElementById("new-password").value;

    if(pass === newpass){
        alert("Las nueva contraseña no puede ser igual a la anterior.");
    }else{
        //comprobar si la contrasena introducida es correcta 
        //proceder a cambiarla
        if(confirm("Esta seguro?")){
            alert("Proceder a cambiarla");
        }
    }
}


function confirmar(nomIdea,idIdea) {
    $.confirm({
        title: nomIdea,
        content: '¿Desea eliminar '+  nomIdea + '?',
        buttons: {
            Aceptar: function () {
                $.alert(nomIdea + ' borrada');
            },
            Cancelar: function () {
               window.location.href = "/contractor";
            },

        }

    });

}