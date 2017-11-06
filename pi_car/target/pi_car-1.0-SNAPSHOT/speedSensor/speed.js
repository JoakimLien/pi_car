$(document).ready(function(){
    $('#toggle-event').on('switchChange.bootstrapSwitch', function (event, state) {
        switch(state){
            case true:
                document.getElementById('speed_button').addEventListener('click',function(){
                    alert($("#speed_input").val());
                });
                break;
            case false:
                    
                break;
        }
    });
});