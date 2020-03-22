$(document).ready(function() {
    jQuery.loadCSS = function(url) {
        if (!$('link[href="' + url + '"]').length)
            $('head').append('<link rel="stylesheet" type="text/css" href="' + url + '">');
    }
    $(function () {
        $("header").empty();
        $("header").load('/header');

        ApiController.get("api/ideas/","",true).then(function(data){
            var x = data;
            x = data;
        })

        $.loadCSS('/css/general.css');
    });
});