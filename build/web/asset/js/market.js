function Slider(options) {
   var slider = document.querySelector(options.sliderSelector);
   let center = Math.ceil(options.maxSlides / 2);
   let side = options.maxSlides - center;
   for (let i = 0; i < options.maxSlides + 2; i++) {
      var item = document.createElement("button");
      item.classList.add("slider_");
      item.classList.add(`slider_index_${i}`);
      item.setAttribute("name","button");
      item.setAttribute("value",`${i}`);
      if (i == 0) {
         item.innerHTML = '<i class="fa-solid fa-chevron-left"></i>';
      } else if (i == options.maxSlides + 1) {
         item.innerHTML = '<i class="fa-solid fa-chevron-right"></i>';
      } else {
         item.innerHTML = i;
     }
      slider.appendChild(item);
   }
   document.querySelector(`.slider_index_${options.current}`).style =
      "background: rgba(65, 122, 159, 0.2);border-radius: 3px;box-shadow: 0 4px 10px rgba(0, 0, 0, 200);backdrop-filter: blur(5px); -webkit-backdrop-filter: blur(5px); border: 1px solid rgba(65, 122, 159, 0.13);";
   document.querySelector(`.slider_index_${options.current}`).onclick = (e) => {
         e.preventDefault();
      };
   if (options.current == 1) {
      document.querySelector(`.slider_index_0`).onclick = (e) => {
         e.preventDefault();
      };
     
   }
   if(options.current == options.maxSlides){
       document.querySelector(`.slider_index_${options.current + 1}`).onclick = (e) => {
         e.preventDefault();
      };
   }
}










var x, i, j, l, ll, selElmnt, a, b, c;
/* Look for any elements with the class "custom-select": */
x = document.getElementsByClassName("custom-select");
l = x.length;
for (i = 0; i < l; i++) {
  selElmnt = x[i].getElementsByTagName("select")[0];
  ll = selElmnt.length;
  /* For each element, create a new DIV that will act as the selected item: */
  a = document.createElement("DIV");
  a.setAttribute("class", "select-selected");
  a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
  x[i].appendChild(a);
  /* For each element, create a new DIV that will contain the option list: */
  b = document.createElement("DIV");
  b.setAttribute("class", "select-items select-hide");
  for (j = 1; j < ll; j++) {
    /* For each option in the original select element,
    create a new DIV that will act as an option item: */
    c = document.createElement("DIV");
    c.innerHTML = selElmnt.options[j].innerHTML;
    c.addEventListener("click", function(e) {
        /* When an item is clicked, update the original select box,
        and the selected item: */
        var y, i, k, s, h, sl, yl;
        s = this.parentNode.parentNode.getElementsByTagName("select")[0];
        sl = s.length;
        h = this.parentNode.previousSibling;
        for (i = 0; i < sl; i++) {
          if (s.options[i].innerHTML == this.innerHTML) {
            s.selectedIndex = i;
            h.innerHTML = this.innerHTML;
            y = this.parentNode.getElementsByClassName("same-as-selected");
            yl = y.length;
            for (k = 0; k < yl; k++) {
              y[k].removeAttribute("class");
            }
            this.setAttribute("class", "same-as-selected");
            break;
          }
        }
        h.click();
    });
    b.appendChild(c);
  }
  x[i].appendChild(b);
  a.addEventListener("click", function(e) {
    /* When the select box is clicked, close any other select boxes,
    and open/close the current select box: */
    e.stopPropagation();
    closeAllSelect(this);
    this.nextSibling.classList.toggle("select-hide");
    this.classList.toggle("select-arrow-active");
  });
}

function closeAllSelect(elmnt) {
  /* A function that will close all select boxes in the document,
  except the current select box: */
  var x, y, i, xl, yl, arrNo = [];
  x = document.getElementsByClassName("select-items");
  y = document.getElementsByClassName("select-selected");
  xl = x.length;
  yl = y.length;
  for (i = 0; i < yl; i++) {
    if (elmnt == y[i]) {
      arrNo.push(i)
    } else {
      y[i].classList.remove("select-arrow-active");
    }
  }
  for (i = 0; i < xl; i++) {
    if (arrNo.indexOf(i)) {
      x[i].classList.add("select-hide");
    }
  }
}

/* If the user clicks anywhere outside the select box,
then close all select boxes: */
document.addEventListener("click", closeAllSelect);