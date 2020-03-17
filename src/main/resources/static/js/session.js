$(document).ready(function(){
    $.ajax({async:false,
        url:    "/api/auth/user",
        type:   "GET",
        headers: {
            'Content-Type': 'application/json'
        },
        contentType : "application/json; charset=utf-8",
        mimeType: "application/json",
        success: function(data){
            console.log("Username: " + data.username +"\nCredentials NON expired:" + data.credentialsNonExpired);
            var role = "user";
            for(var i = 0; i < data.authorities.length; i++){
                var auth = data.authorities[i].authority;
                if(auth != "ROLE_USER"){
                    var str = auth.split('_');
                    role = str[1].toLowerCase();
                }
            }
            document.getElementById("register-header").style.display="none";
            document.getElementById("login-header").style.display="none";
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