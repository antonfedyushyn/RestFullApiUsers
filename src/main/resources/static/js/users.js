
function getUser(id){
    $('#get_user_response').html("");
    $.ajax({
        url: 'users/'+id,
        type: 'GET',
        dataType: 'json',
        error: function(jqXHR, textStatus, errorThrow){
            $('#get_user_response').html('error send data! ' + JSON.stringify(jqXHR) + '|' + textStatus + '|'+errorThrow);
        },
        success: function(data){
            if (data.code == 0) {
                var res = JSON.stringify(data.user);
            } else {
                var res = "<font color='red'><b>"+data.status+"</b></font>";
            }
            $('#get_user_response').html(res);
        }
    });
}


function getAllUsers(){
    $.ajax({
        url: 'users/',
        type: 'GET',
        dataType: 'json',
        error: function(jqXHR, textStatus, errorThrow){
            var sres = "<tr><td align='center' colspan='5'> Error send data! " + JSON.stringify(jqXHR) + "|" + textStatus + "|" + errorThrow + "</td></tr>";
            $('#tusers').html(s_res);
        },
        success: function(data){
            var s_res = "";
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++){

                    s_res = s_res + "<tr>" +
                        "<td align = 'center'>" +
                            "<input type='hidden' id='user_id_"+data[i].id+"' name='user_id' value = '"+data[i].id+"'/>" +
                            "<input type='hidden' id='user_first_name_"+data[i].id+"' name='user_first_name' value = '"+data[i].first_name+"'/>"+data[i].first_name+
                        "</td>" +
                        "<td align = 'center'>" +
                            "<input type='hidden' id='user_last_name_"+data[i].id+"' name='user_last_name' value = '"+data[i].last_name+"'/>"+data[i].last_name+
                        "</td>" +
                        "<td align = 'center'>" +
                            "<input type='hidden' id='user_e_mail_"+data[i].id+"' name='user_e_mail' value = '"+data[i].e_mail+"'/>"+data[i].e_mail+
                        "</td>" +
                        "<td align = 'center'>" +
                            "<input type='hidden' id='user_phone_"+data[i].id+"' name='user_phone' value = '"+data[i].phone+"'/>"+data[i].phone+
                        "</td>" +
                        "<td align='center'>" +
                            "<div id='"+data[i].id+"' name='edit_user' class='edit_user' onclick='return modifyUserFrm(this.id)'>" +
                                "<img src='img/edit.png' border='0' width='25' title='Modify user'>" +
                            "</div>&nbsp;&nbsp;&nbsp;" +
                            "<div id='"+data[i].id+"' name='delete_user' class='delete_user' onclick='return deleteUser(this.id)'>" +
                                "<img src='img/delete.png' border='0' width='25' title='Delete user'>" +
                            "</div>" +
                        "</td></tr>";
                }
            }
            $('#tusers').html(s_res);
        }
    });
}

function addUser(first_name, last_name, email, phone){
    $('#loader_div').show();
    $.ajax({
        url: 'users/',
        type: 'POST',
        dataType: 'json',
        data: {
            'firstName' : first_name,
            'lastName'  : last_name,
            'email'     : email,
            'phone'     : phone
        },
        error: function(jqXHR, textStatus, errorThrow){
            $('#loader_div').hide();
            alert('error send data! ' + JSON.stringify(jqXHR) + '|' + JSON.stringify(textStatus) + '|'+errorThrow);
        },
        success: function(data){
            $('#loader_div').hide();
            if (data.code == 0) {
                window.location.reload();
            } else {
                alert(data.status);
            }
        }
    });
}

function modifyUser(id, first_name, last_name, email, phone){
    $('#loader_div').show();
    $.ajax({
        url: 'users/',
        type: 'PUT',
        dataType: 'json',
        data: {
            'id'        : id,
            'firstName' : first_name,
            'lastName'  : last_name,
            'email'     : email,
            'phone'     : phone
        },
        error: function(jqXHR, textStatus, errorThrow){
            $('#loader_div').hide();
            alert('error send data! ' + JSON.stringify(jqXHR) + '|' + JSON.stringify(textStatus) + '|'+errorThrow);
        },
        success: function(data){
            $('#loader_div').hide();
            if (data.code == 0) {
                window.location.reload();
            } else {
                alert(data.status);
            }
        }
    });
}

function deleteUser(id){
    if (confirm("Do you really want to remove user with id "+id+"?")) {
        $.ajax({
            url: 'users/',
            type: 'DELETE',
            dataType: 'json',
            data: {
                'id': id
            },
            error: function (jqXHR, textStatus, errorThrow) {
                alert('Ошибка отправки данных! ' + JSON.stringify(jqXHR) + '|' + textStatus + '|' + errorThrow);
            },
            success: function (data) {
                if (data.code == 0) {
                    window.location.reload();
                } else {
                    alert(data.status);
                }
            }
        });
    }
}

function modifyUserFrm(id_user) {
    var id = "#frm_modify_user";
    $('#modify_user_id').val(id_user);
    $('#modify_user_first_name').val($('#user_first_name_'+id_user).val());
    $('#modify_user_last_name').val($('#user_last_name_'+id_user).val());
    $('#modify_user_email').val($('#user_e_mail_'+id_user).val());
    $('#modify_user_phone').val($('#user_phone_'+id_user).val());
    $('#modify_user_title').html("Modify User");
    $('#btn_modify_user').val("Modify");
    //Get the screen height and width
    var maskHeight = $(document).height();
    var maskWidth = $(window).width();
    //Set heigth and width to mask to fill up the whole screen
    $('#mask').css({'width':maskWidth,'height':maskHeight});
    //transition effect
    $('#mask').fadeIn(0);
    //$('#mask').fadeTo("slow",0.8);

    //Get the window height and width
    var winH = Math.min(screen.height, document.documentElement.clientHeight);
    var winW = screen.width; //$(window).screen.width();
    //Set the popup window to center
    $(id).css('top',  winH/2-$(id).height()/2);
    $(id).css('left', winW/2-$(id).width()/2);
    //transition effect
    $(id).fadeIn(0);
    $('#modify_user_first_name').focus();
}

function closeModalForm(){
    $('#mask, .window').hide();
}