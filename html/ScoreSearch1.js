// '.tbl-content' consumed little space for vertical scrollbar, scrollbar width depend on browser/os/platfrom. Here calculate the scollbar width .
$(window).on("load resize ", function() {
  var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
  $('.tbl-header').css({'padding-right':scrollWidth});
}).resize();
window.onload = function()
{
  var e = "213171645";
  var vec = gradeInfo.getAll(e);
  for (var i = 0; i <vec.size() ; i++){
    var tabel = document.getElementById("Grade");
    var tr = document.createElement("tr");
    var td0 = document.createElement("td");
    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var a = vec.elementAt(i).getCourseSemeter();
    var text0 = document.createTextNode(Math.floor(a/1000)+"-"+Math.floor(a/10)%100+"-"+a%10);
    var text1 = document.createTextNode(vec.elementAt(i).getCourseName());
    var text2 = document.createTextNode(vec.elementAt(i).getCourseGrade());
    //var a = vec.elementAt(i).getECardName();
    //var b = vec.elementAt(i).getCourseName();
    //var c = vec.elementAt(i).getScore();
    //var d = vec.elementAt(i).getTeacher();
    td0.appendChild(text0);
    td1.appendChild(text1);
    td2.appendChild(text2);
    tr.appendChild(td0);
    tr.appendChild(td1);
    tr.appendChild(td2);
    tabel.appendChild(tr);
  }

}
function Back()
{
  window.location.href="bar.html";
}






