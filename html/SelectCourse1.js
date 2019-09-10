// '.tbl-content' consumed little space for vertical scrollbar, scrollbar width depend on browser/os/platfrom. Here calculate the scollbar width .
$(window).on("load resize ", function() {
  var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
  $('.tbl-header').css({'padding-right':scrollWidth});
}).resize();
var vec = courseInfo.getAll();
var time = new Array();
var cr = new Array();
for (var i = 0; i<vec.size(); i++) {
  time[i] = vec.elementAt(i).getCourseTime();
  cr[i] = vec.elementAt(i).getClassroom();
  var b = vec.elementAt(i).getCourseTime();
  var c = b % 1000;
  var d = 0;
  var e = 0;

  if (Math.floor(b / 1000) != 0) {
    d = (Math.floor(b / 1000)) % 1000;
    if (Math.floor(b / 1000000) != 0) {
      e = (Math.floor(b / 1000000)) % 1000;
    }
  }

  if (e != 0) {
    $("#Course").append("<tr><td>" + vec.elementAt(i).getIdx() + "</td><td>" + vec.elementAt(i).getCourseName() + "</td><td>" + vec.elementAt(i).getTeacher() + "</td><td>" +
        "周" + Math.floor(e / 100) + "[" + Math.floor(e / 10) % 10 + "-" + e % 10 + "]" + "<br/>" + "周" + Math.floor(d / 100) + "[" + Math.floor(d / 10) % 10 + "-" + d % 10 + "]" + "<br/>" + "周" + Math.floor(c / 100) + "[" + Math.floor(c / 10) % 10 + "-" + c % 10 + "]" + "<br/>"
        + "</td><td>" + "J" + Math.floor(vec.elementAt(i).getClassroom() / 1000) + "-" + vec.elementAt(i).getClassroom() % 1000 + "<br/>" + "</td><td>" +
        "<button id='select'>选课</button>" + "</td></tr>"
    )
  } else if (d != 0) {
    $("#Course").append("<tr><td>" + vec.elementAt(i).getIdx() + "</td><td>" + vec.elementAt(i).getCourseName() + "</td><td>" + vec.elementAt(i).getTeacher() + "</td><td>" +
        "周" + Math.floor(d / 100) + "[" + Math.floor(d / 10) % 10 + "-" + d % 10 + "]" + "<br/>" + "周" + Math.floor(c / 100) + "[" + Math.floor(c / 10) % 10 + "-" + c % 10 + "]" + "<br/>" +
        "</td><td>" + "J" + Math.floor(vec.elementAt(i).getClassroom() / 1000) + "-" + vec.elementAt(i).getClassroom() % 1000 + "<br/>" + "</td><td>" +
        "<button id='select'>选课</button>" + "</td></tr>"
    )
  }

  else{
        $("#Course").append("<tr><td>" + vec.elementAt(i).getIdx() + "</td><td>" + vec.elementAt(i).getCourseName() + "</td><td>" + vec.elementAt(i).getTeacher() + "</td><td>" +
             "周" + Math.floor(c / 100) + "[" + Math.floor(c / 10) % 10 + "-" + c % 10 + "]" + "<br/>"+
        "</td><td>" + "J" + Math.floor(vec.elementAt(i).getClassroom() / 1000) + "-" + vec.elementAt(i).getClassroom() % 1000 + "<br/>" + "</td><td>" +
        "<button id='select'>选课</button>" + "</td></tr>"
        );
}

}

$(document).on("click", '#select',function () {
  {
    var idx = $(this).parents("tr").find("td").eq(0).text();
    var name = $(this).parents("tr").find("td").eq(1).text();
    var teacher = $(this).parents("tr").find("td").eq(2).text();
    //var ctime = $(this).parents("tr").find("td").eq(3).text();
    //var room = $(this).parents("tr").find("td").eq(4).text();
    var i = parseInt(idx);
    var ctime = time[i-1];
    var room = cr[i-1];
    var n = parseInt(ctime);
    var m = parseInt(room);
    if($(this).text() == "选课") {
      var a =selectCourseinfo.insert("213171645", idx, name, n, teacher, m);
      if(a==1){
      alert("选课成功");
      $(this).text("退课");}
      else if(a==-2){
        alert("选课冲突");
      }
      else if(a==-3){
        alert("该课已满");
      }
      else if(a==0)
      {
        alert("该课已选");
        $(this).text("退课");
      }
    }
    else if($(this).text() == "退课") {
      selectCourseinfo.delete("213171645", name);
      alert("退课成功");
      $(this).text("选课");
    }
  }})
function Back()
{
  window.location.href="bar.html";
}






