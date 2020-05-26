function changePassword() {
    let params = {};
    var repeatNewPassword;
    params.newPassword = document.getElementById("newPassword").value;
    repeatNewPassword = document.getElementById("confirmPassword").value;
    params.token =  location.search.substr(3);
    params.active = true;
    if(params.newPassword == repeatNewPassword){
            ApiController.put("auth/updatePassword",params, true).then(function(done){
                if(done){
                    alert("Contraseña cambiada con exito");
                }else{
                    alert("No se pudo cambiar la contraseña");
                }

                window.location.href = "/home";
            });
    }
}