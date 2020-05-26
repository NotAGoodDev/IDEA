function changePassword() {
    let params= {};
    let form = $( this );

    params.password = form.find( "input[name='password']" ).val();
    params.repassword = form.find( "input[name='confirmPassword']" ).val();
    params.active = true;
    params.token = location.search;

    if(params.password == params.repassword){
            ApiController.post("auth/updatePassword",params).then(function(data){
                if(done){
                    alert("Contraseña cambiada con exito");
                }else{
                    alert("No se pudo cambiar la contraseña");
                }
            });
    }
}