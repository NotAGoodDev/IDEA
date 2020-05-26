var token = location.search;

function changePassword() {
    var pass = document.getElementById("password").value;
    var confirmPass = document.getElementById("confirmPassword").value;
    if(pass == confirmPass){
            ApiController.put("users/changepass/?pass="+pass+"&token="+token,"",true).then(function(done){
                if(done){
                    alert("Contraseña cambiada con exito");
                }else{
                    alert("No se pudo cambiar la contraseña");
                }
            });
    }
}