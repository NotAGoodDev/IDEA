$(document).ready(function() {
    //Formulario de registro de empresa
     let form = $( this );
     document.getElementsByName("imagen").forEach(function(item){
         item.style.display ="none";
     });
     document.getElementsByName("password").forEach(function(item){
         item.style.display ="none";
     })

     ApiController.get("auth/sessionId","",true).then(function(id){
        ApiController.get("enterprises/id/"+id,"",true).then(function(enterprise){
            document.getElementById("nombre-usuario").innerHTML = "Bienvenido, "+ enterprise.username;
            document.getElementById("username").setAttribute("value",enterprise.username);
            document.getElementById("name").setAttribute("value",enterprise.name);
            document.getElementById("email").setAttribute("value",enterprise.email);
            document.getElementById("telephone").setAttribute("value",enterprise.telephone);
            document.getElementById("address").setAttribute("value",enterprise.address);
            document.getElementById("cif").setAttribute("value",enterprise.cif);
        });
    });  

    $( "#btn-success" ).click(function( event ) {
        let params = {};

        params.username = form.find( "input[name='username']" ).val();
        params.password = form.find( "input[name='password']" ).val();
        params.email = form.find( "input[name='email']" ).val();
        params.name = form.find( "input[name='name']" ).val();
        params.lastName = form.find( "input[name='cif']" ).val();
        params.telephone = form.find( "input[name='telephone']" ).val();
        params.address = form.find( "input[name='address']" ).val();
        params.active = true;
        params.type = "Enterprise";

        ApiController.put("users/profiles/", params, false).then(function(done){
            if(done){
                alert("Cambios realizados con exito en el perfil.");            
            }else{
                alert("Error al realizar los cambios en el perfil.");
            }window.location.href = "/enterprise/profile";
        });
    });

    $("#change-pass").click(function(){
        changePass();
    });
})


function enableFields()
{
    let fields = ["username", "password", "email", "name", "cif", "telephone", "address"];

    document.getElementsByName("password").forEach(function(item){
        item.style.display ="block";
    });


    for (let i in fields)
    {
        if(fields[i] != "username" && fields[i] != "email"){
            document.getElementById(fields[i]).readOnly = false;
            document.getElementById(fields[i]).type = "text";
        }
    }

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
        if(confirm("Esta seguro?")){
            var newPass = document.getElementById("new-password").value;
            var pass = document.getElementById("password").value;
            ApiController.put("users/changepass/?pass="+pass+"&newPass="+newPass,"",true).then(function(done){
                if(done){
                    alert("Contraseña cambiada con exito");
                }else{
                    alert("No se pudo cambiar la contraseña");
                }
            });
        }
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