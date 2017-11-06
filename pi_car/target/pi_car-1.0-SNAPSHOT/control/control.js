$(document).ready(function(){
    $('#toggle-event').on('switchChange.bootstrapSwitch', function (event, state) {   
    $('#console-table').append('<tr><td style="color:blue;">Control was switched from Automatic to Manual</td></tr>');
    $('#control-field').on('input', function() {
        switch($("#control-field").val().toLowerCase()){
            case "w":
                manual_control_from_keyboard("forward");
            break;
            case "a":
                manual_control_from_keyboard("left");
            break;
            case "s":
                manual_control_from_keyboard("backward");
            break;
            case "d":
                manual_control_from_keyboard("right");
            break;
        }
    });
        jQuery(':button').click(function () {
            switch(state === true){
                case(this.id === 'arrow-up'):
                    manual_control_from_button("forward");
                    break;
                case(this.id === 'arrow-down'):
                    manual_control_from_button("backward");
                    break;
                case(this.id === 'arrow-left'):
                    manual_control_from_button("left");
                    break;
                case(this.id === 'arrow-right'):
                        manual_control_from_button("right");
                    break;
            }
        });
    });
        function manual_control_from_button(direction){
        fetch('../api/control/manual?direction='+direction+'',{
            method: 'POST', 
            body : JSON.stringify({"direction":direction}),
            headers: {'Content-Type' : 'application/json; charset=UTF-8'}
        });
        write_log(direction);
    }
    
    function manual_control_from_keyboard(direction){
        fetch('../api/control/manual?direction='+direction+'',{
            method: 'POST', 
            body : JSON.stringify({"direction":direction}),
            headers: {'Content-Type' : 'application/json; charset=UTF-8'}
        });
        $('#control-field').val('');
        write_log(direction);
    }
    
    function write_log(direction){
        $('#console-table').append('<tr><td style="color:grey;">Moving '+direction+'</td></tr>');
    }
});