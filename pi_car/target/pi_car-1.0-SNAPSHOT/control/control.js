$(document).ready(function(){
    $('#toggle-event').on('switchChange.bootstrapSwitch', function (event, state) {   
    $('#console-table').append('<tr><td style="color:blue;">Control was switched from Automatic to Manual</td></tr>');
    $('#control-field').on('input', function() {
        switch($("#control-field").val().toLowerCase()){
            case "w":
                fetch('../api/control/manual?direction=forward',{
                 method: 'POST', 
                 body : JSON.stringify({"direction":"forward"}),
                 headers: {'Content-Type' : 'application/json; charset=UTF-8'}
                });
                $('#control-field').val('');
                $('#console-table').append('<tr><td style="color:grey;">Moving forward</td></tr>');
            break;
            case "a":
                fetch('../api/control/manual?direction=left',{
                 method: 'POST', 
                 body : JSON.stringify({"direction":"forward"}),
                 headers: {'Content-Type' : 'application/json; charset=UTF-8'}
                });
                $('#control-field').val('');
                $('#console-table').append('<tr><td style="color:grey;">Moving left</td></tr>');
            break;
            case "s":
                fetch('../api/control/manual?direction=backward',{
                 method: 'POST', 
                 body : JSON.stringify({"direction":"forward"}),
                 headers: {'Content-Type' : 'application/json; charset=UTF-8'}
                });
                $('#console-table').append('<tr><td style="color:grey;">Moving backward</td></tr>');
            break;
            case "d":
                $('#control-field').val('');
                fetch('../api/control/manual?direction=right',{
                 method: 'POST', 
                 body : JSON.stringify({"direction":"forward"}),
                 headers: {'Content-Type' : 'application/json; charset=UTF-8'}
                });
                $('#control-field').val('');
                $('#console-table').append('<tr><td style="color:grey;">Moving right</td></tr>');
            break;
        }
    });
    jQuery(':button').click(function () {
        switch(state === true){
            case(this.id === 'arrow-up'):
                fetch('../api/control/manual?direction=forward',{
                 method: 'POST', 
                 body : JSON.stringify({"direction":"forward"}),
                 headers: {'Content-Type' : 'application/json; charset=UTF-8'}
                });
                $('#console-table').append('<tr><td style="color:grey;">Moving forward</td></tr>');
                break;
            case(this.id === 'arrow-down'):
                fetch('../api/control/manual?direction=backward',{
                 method: 'POST', 
                 body : JSON.stringify({"direction":"forward"}),
                 headers: {'Content-Type' : 'application/json; charset=UTF-8'}
                });
                $('#console-table').append('<tr><td style="color:grey;">Moving backward</td></tr>');
                break;
            case(this.id === 'arrow-left'):
                fetch('../api/control/manual?direction=left',{
                 method: 'POST', 
                 body : JSON.stringify({"direction":"forward"}),
                 headers: {'Content-Type' : 'application/json; charset=UTF-8'}
                });
                $('#console-table').append('<tr><td style="color:grey;">Moving left</td></tr>');
                break;
            case(this.id === 'arrow-right'):
                fetch('../api/control/manual?direction=right',{
                 method: 'POST', 
                 body : JSON.stringify({"direction":"forward"}),
                 headers: {'Content-Type' : 'application/json; charset=UTF-8'}
                });
                $('#console-table').append('<tr><td style="color:grey;">Moving right</td></tr>');
                break;
            }
        });
    });
});