function changePassword() {
    let params = {};
    var repeatNewPassword;
    params.newPassword = document.getElementById("newPassword").value;
    repeatNewPassword = document.getElementById("confirmPassword").value;
    params.token = location.search;
    params.active = true;
    if(params.newPassword == repeatNewPassword){
            ApiController.post("auth/updatePassword",params).then(function(data){
                if(done){
                    alert("Contraseña cambiada con exito");
                }else{
                    alert("No se pudo cambiar la contraseña");
                }
            });
    }
}