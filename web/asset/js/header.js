var i = 0;
var txt = 'GameCD';
var speed = 150;

function typeWriter() {
    if (i < txt.length) {
      document.getElementById("logoText").innerHTML += txt.charAt(i);
      i++;
      setTimeout(typeWriter, speed);
    }
}

document.addEventListener('DOMContentLoaded', function() {
    // your code here
    typeWriter()
 }, false);