$(document).ready(function() {
    jQuery.loadCSS = function(url) {
        if (!$('link[href="' + url + '"]').length)
            $('head').append('<link rel="stylesheet" type="text/css" href="' + url + '">');
    }
    $(function () {
        $("header").empty();
        $("header").load('/header');


        $.loadCSS('css/quienes-somos.css');


        $.getScript('js/enterprise/index.js', function() {
            alert('Load was performed.');
        });
    });

});