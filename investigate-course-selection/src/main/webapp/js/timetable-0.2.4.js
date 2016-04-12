// JavaScript Document 

var html_head = '<div id="time_table_container"> 		<div id="time_table_head">     	<div id="time_table_title">       	<span style="color:#090;font-size:14px; font-weight:bold;">空&nbsp;闲&nbsp;时&nbsp;间&nbsp;表</span>       </div>     	<div id="time_table_room">    ';

var html_select = ' <select id="time_table_room_select"> 	<option>A201</option>  </select> ';

var html_tail = '  </div>     </div>     <div id="time_table_content">		     <table width="538px" height="255px" cellpadding="2px" border="0" cellspacing="1" bgcolor="#66FF66">      <tr>       <th style="background-color: #aaffaa;" scope="col">节次</th>       <th style="background-color: #aaffaa;" scope="col">星期一</th>       <th style="background-color: #aaffaa;" scope="col">星期二</th>       <th style="background-color: #aaffaa;" scope="col">星期三</th>       <th style="background-color: #aaffaa;" scope="col">星期四</th>       <th style="background-color: #aaffaa;"" scope="col">星期五</th>       <th style="background-color: #aaffaa;" scope="col">星期六</th>       <th style="background-color: #aaffaa;" scope="col">星期日</th>      </tr>      <tr>       <th style="background-color: #aaffaa;" scope="row">1</th>       <td id="1,1" class="time_table_td_empty">&nbsp;</td>       <td id="2,1" class="time_table_td_empty">&nbsp;</td>       <td id="3,1" class="time_table_td_empty">&nbsp;</td>       <td id="4,1" class="time_table_td_empty">&nbsp;</td>       <td id="5,1" class="time_table_td_empty">&nbsp;</td>       <td id="6,1" class="time_table_td_empty">&nbsp;</td>       <td id="7,1" class="time_table_td_empty">&nbsp;</td>      </tr>      <tr>       <th style="background-color: #aaffaa;" scope="row">2</th>       <td id="1,2" class="time_table_td_empty">&nbsp;</td>       <td id="2,2" class="time_table_td_empty">&nbsp;</td>       <td id="3,2" class="time_table_td_empty">&nbsp;</td>       <td id="4,2" class="time_table_td_empty">&nbsp;</td>       <td id="5,2" class="time_table_td_empty">&nbsp;</td>       <td id="6,2" class="time_table_td_empty">&nbsp;</td>       <td id="7,2" class="time_table_td_empty">&nbsp;</td>      </tr>      <tr>       <th style="background-color: #aaffaa;" scope="row">3</th>       <td id="1,3" class="time_table_td_empty">&nbsp;</td>       <td id="2,3" class="time_table_td_empty">&nbsp;</td>       <td id="3,3" class="time_table_td_empty">&nbsp;</td>       <td id="4,3" class="time_table_td_empty">&nbsp;</td>       <td id="5,3" class="time_table_td_empty">&nbsp;</td>       <td id="6,3" class="time_table_td_empty">&nbsp;</td>       <td id="7,3" class="time_table_td_empty">&nbsp;</td>      </tr>      <tr>       <th style="background-color: #aaffaa;" scope="row">4</th>       <td id="1,4" class="time_table_td_empty">&nbsp;</td>       <td id="2,4" class="time_table_td_empty">&nbsp;</td>       <td id="3,4" class="time_table_td_empty">&nbsp;</td>       <td id="4,4" class="time_table_td_empty">&nbsp;</td>       <td id="5,4" class="time_table_td_empty">&nbsp;</td>       <td id="6,4" class="time_table_td_empty">&nbsp;</td>       <td id="7,4" class="time_table_td_empty">&nbsp;</td>      </tr>      <tr>       <th style="background-color: #aaffaa;" scope="row">5</th>       <td id="1,5" class="time_table_td_empty">&nbsp;</td>       <td id="2,5" class="time_table_td_empty">&nbsp;</td>       <td id="3,5" class="time_table_td_empty">&nbsp;</td>       <td id="4,5" class="time_table_td_empty">&nbsp;</td>       <td id="5,5" class="time_table_td_empty">&nbsp;</td>       <td id="6,5" class="time_table_td_empty">&nbsp;</td>       <td id="7,5" class="time_table_td_empty">&nbsp;</td>      </tr>      <tr>       <th style="background-color: #aaffaa;" scope="row">6</th>       <td id="1,6" class="time_table_td_empty">&nbsp;</td>       <td id="2,6" class="time_table_td_empty">&nbsp;</td>       <td id="3,6" class="time_table_td_empty">&nbsp;</td>       <td id="4,6" class="time_table_td_empty">&nbsp;</td>       <td id="5,6" class="time_table_td_empty">&nbsp;</td>       <td id="6,6" class="time_table_td_empty">&nbsp;</td>       <td id="7,6" class="time_table_td_empty">&nbsp;</td>      </tr>      <tr>       <th style="background-color: #aaffaa;" scope="row">7</th>       <td id="1,7" class="time_table_td_empty">&nbsp;</td>       <td id="2,7" class="time_table_td_empty">&nbsp;</td>       <td id="3,7" class="time_table_td_empty">&nbsp;</td>       <td id="4,7" class="time_table_td_empty">&nbsp;</td>       <td id="5,7" class="time_table_td_empty">&nbsp;</td>       <td id="6,7" class="time_table_td_empty">&nbsp;</td>       <td id="7,7" class="time_table_td_empty">&nbsp;</td>      </tr>      <tr>       <th style="background-color: #aaffaa;" scope="row">8</th>       <td id="1,8" class="time_table_td_empty">&nbsp;</td>       <td id="2,8" class="time_table_td_empty">&nbsp;</td>       <td id="3,8" class="time_table_td_empty">&nbsp;</td>       <td id="4,8" class="time_table_td_empty">&nbsp;</td>       <td id="5,8" class="time_table_td_empty">&nbsp;</td>       <td id="6,8" class="time_table_td_empty">&nbsp;</td>       <td id="7,8" class="time_table_td_empty">&nbsp;</td>      </tr>      <tr>       <th style="background-color: #aaffaa;" scope="row">9</th>       <td id="1,9" class="time_table_td_empty">&nbsp;</td>       <td id="2,9" class="time_table_td_empty">&nbsp;</td>       <td id="3,9" class="time_table_td_empty">&nbsp;</td>       <td id="4,9" class="time_table_td_empty">&nbsp;</td>       <td id="5,9" class="time_table_td_empty">&nbsp;</td>       <td id="6,9" class="time_table_td_empty">&nbsp;</td>       <td id="7,9" class="time_table_td_empty">&nbsp;</td>      </tr>      <tr>       <th style="background-color: #aaffaa;" scope="row">10</th>       <td id="1,10" class="time_table_td_empty">&nbsp;</td>       <td id="2,10" class="time_table_td_empty">&nbsp;</td>       <td id="3,10" class="time_table_td_empty">&nbsp;</td>       <td id="4,10" class="time_table_td_empty">&nbsp;</td>       <td id="5,10" class="time_table_td_empty">&nbsp;</td>       <td id="6,10" class="time_table_td_empty">&nbsp;</td>       <td id="7,10" class="time_table_td_empty">&nbsp;</td>      </tr>      <tr>       <th style="background-color: #aaffaa;" scope="row">11</th>       <td id="1,11" class="time_table_td_empty">&nbsp;</td>       <td id="2,11" class="time_table_td_empty">&nbsp;</td>       <td id="3,11" class="time_table_td_empty">&nbsp;</td>       <td id="4,11" class="time_table_td_empty">&nbsp;</td>       <td id="5,11" class="time_table_td_empty">&nbsp;</td>       <td id="6,11" class="time_table_td_empty">&nbsp;</td>       <td id="7,11" class="time_table_td_empty">&nbsp;</td>      </tr>     </table> 		</div> 		<div id="time_table_tail">       <div style=" display:inline-block; margin-right:40px;"> 				<button style="width:50px;" id="time_table_ok">确定</button>       </div>       <div style=" display:inline-block;"> 				<button style="width:50px;" id="time_table_cancel">取消</button>       </div>     </div> 	</div>';

var html = html_head + html_select + html_tail;
document.write(html);	

var roomURL = "";
var timeURL = "";

var room = "";
var time = null;
var used = null;  

$(document).ready(function(e) {
	
	$("#time_table_ok").click(function(e) {
		$("#time_table_select_room").attr("value",room);
		$("#time_table_select_time").attr("value",time.join('-'));
        $("#time_table_container").hide(1000);
    });
	
	$("#time_table_cancel").click(function(e) {
        $("#time_table_container").hide(1000);
    });
	$(".time_table_td_empty").click(function(e) {
		var id = $(this).attr("id");
		var status = false;
		for(var i = 0; i < used.length; i++){
			if(used[i] == id){
				status = true;
			}
		}
		
		if(!status) return;
		
		var index = null;
		for(var i = 0; i < time.length; i++){
			if(time[i] == id){
				index = i;
				break;
			}
		}
		if(index != null){
			time.splice(index,1);
			$(this).removeClass("time_table_td_empty_selected").addClass("time_table_td_empty"); 
		}else{
			time[time.length] = id;
			$(this).removeClass("time_table_td_empty").addClass("time_table_td_empty_selected");
		}
    }); 
	$("#time_table_room_select").change(function(e) {
		var value = $(this).children("option:selected").val();
		if(value != ""){
			$(".time_table_td_empty").removeClass("time_table_td_empty").addClass("time_table_td_empty_used");
			request_time_period(value);
			room = value;
		}
    });
});

function show_callback(){

	var currentRoom = "";
	
	$.ajax({
		url:roomURL,
		dataType:"json",
		async:false,
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert(errorThrown);
		},
		success: function(data){
			$("#time_table_room_select").html("");
			$.each(data['names'],function(index,value){
				if(index == 0){
					$("#time_table_room_select").append("<option value='"+value+"' selected='selected' >"+ value+"</option>");
					currentRoom = value;
					room = value;					
				}else{
					$("#time_table_room_select").append("<option value='"+value+"'>"+ value+"</option>");
				}
			});
		}
	});
	
	request_time_period(currentRoom);

}

function request_time_period(roomName){
	
	$.ajax({
		url:timeURL,
		data:{"roomName":roomName},
		dataType:"json",
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert(errorThrown);
		},
		success: function(data){
			used = new Array();
			$.each(data['list'],function(index,value){
				var element = document.getElementById(value);
				$(element).removeClass("time_table_td_empty_used").addClass("time_table_td_empty");
				used[used.length] = $(element).attr("id");
			});
		}
	});
}

function time_table_onclick(ru,tu){
	room = "";
	time = new Array();
	roomURL = ru;
	timeURL = tu;
	var x = event.clientX;
	var y = event.clientY;
	$(".time_table_td_empty").removeClass("time_table_td_empty").addClass("time_table_td_empty_used");
	$(".time_table_td_empty_selected").removeClass("time_table_td_empty_selected").addClass("time_table_td_empty_used");
    $("#time_table_container").css("position","absolute").css("top",y).css("left",x).show(1000,show_callback);	
}
