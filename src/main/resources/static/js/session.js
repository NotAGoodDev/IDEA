$(document).ready(function(){

    ApiController.get("auth/session","",false).then(function(data){
        console.log("Username: " + data.username +"\nUsuario activo:" + data.active);
        sesion = data;
        var role = "USER";
        for(i=0;i<data.roles.length;i++){
            var str = data.roles[i].name.split("_");
            role = str[1];
            if(role != "USER"){
                break;
            }
        }
        role = role.toLowerCase();
        document.getElementById("logo-img").setAttribute("href","/");//+role+"/home");
        document.getElementById("navbardrop").innerHTML = data.username;
        document.getElementById("drop-header-item-profile").setAttribute("href","/"+role+"/profile");
        document.getElementById("header-home").setAttribute("href","/");//+role+"/home");
    });
    
});
