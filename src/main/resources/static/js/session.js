$(document).ready(function(){
    $.ajax({
        url:    "/api/auth/session",
        type:   "GET",
        headers: {
            'Content-Type': 'application/json'
        },
        contentType : "application/json; charset=utf-8",
        mimeType: "application/json",
        success: function(data){
            console.log("Username: " + data.username +"\nUsuario activo:" + data.active);
            var role = data.type.toLowerCase();
            
            document.getElementById("register-header").style.display="none";
            //document.getElementById("login-header").style.display="none";
            document.getElementById("drop-header").style.display="block";
            document.getElementById("navbardrop").innerHTML = data.username;
            document.getElementById("drop-header-item-profile").setAttribute("href","/"+role+"/profile");
        },
        error:  function(){
            document.getElementById("register-header").style.display="block";
            document.getElementById("login-header").style.display="block";
            document.getElementById("drop-header").style.display="none";
        }
    });
    
});