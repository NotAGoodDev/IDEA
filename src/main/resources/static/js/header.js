$(document).ready(function() {
    jQuery.loadCSS = function(url) {
        if (!$('link[href="' + url + '"]').length)
            $('head').append('<link rel="stylesheet" type="text/css" href="' + url + '">');
    }
    $(function () {
        $("header").empty();
        $("header").load('/header');

        $.ajax({
            url:    "/api/auth/user",
            type:   "GET",
            headers: {
                'Content-Type': 'application/json'
            },
            contentType : "application/json; charset=utf-8",
            mimeType: "application/json",
            success: function(data){
                console.log("Username: " + data.username +"\nCredentials NON expired:" + data.credentialsNonExpired);
                document.getElementById("register-header").style.display="none";
                document.getElementById("drop-header").style.display="block";
                document.getElementById("navbardrop").innerHTML = data.username;
            },
            error:  function(){
                document.getElementById("register-header").style.display="block";
                document.getElementById("drop-header").style.display="none";
            }
        });

        $.loadCSS('css/quienes-somos.css');


        $.getScript('js/enterprise/index.js', function() {
            alert('Load was performed.');
        });
    });

});