     // HOME PAGE START --------------------->
    $(document).ready(function() {
            $("#loggedInTab").hide();
          
    });

     $(document).ready(function() {
        $("#Start").click(function() {
            alert('yes, the click actually happened');
            $('.nav-tabs a[href="#Generator"]').tab('show');
            });
    });
    $(document).ready(function() {
        $("#LearnMore").click(function() {
            alert('yes, the click actually happened');
            $('.nav-tabs a[href="#Learn"]').tab('show');
            });
    });
    $(document).ready(function() {
        $("#forgotPassword").click(function() {
            alert('yes, the click actually happened');
            $('.nav-tabs a[href="#ForgotPassword"]').tab('show');
            });
    });

    // HOME PAGE END ----------------------------->
 $(document).ready(function() {
        $("#loggedIn").click(function() {
            alert('yes, the click actually happened');
            $("#logTab").hide();
            $("#loggedInTab").show();
            });
    });
$(document).ready(function() {
        $("#logOut").click(function() {
            alert('yes, the click actually happened');
            $("#logTab").show();
            $("#loggedInTab").hide();
            });
    });


function regular_map() {
    var var_location = new google.maps.LatLng(40.725118, -73.997699);

    var var_mapoptions = {
        center: var_location,
        zoom: 14
    };

    var var_map = new google.maps.Map(document.getElementById("map-container"),
        var_mapoptions);

    var var_marker = new google.maps.Marker({
        position: var_location,
        map: var_map,
        title: "New York"
    });
}
// Initialize maps
google.maps.event.addDomListener(window, 'load', regular_map);