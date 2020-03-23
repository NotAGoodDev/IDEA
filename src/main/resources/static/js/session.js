$(document).ready(function(){

    ApiController.get("auth/session","",false).then(function(data){
        console.log("Username: " + data.username +"\nUsuario activo:" + data.active);
        sesion = data;
        var role;
        if(data.type == "Creador")
            role = "creator";
        else
            role = "enterprise";

        document.getElementById("navbardrop").innerHTML = data.username;
        document.getElementById("drop-header-item-profile").setAttribute("href","/"+role+"/profile");
        document.getElementById("header-home").setAttribute("href","/"+role+"/home");
    });
    
});