function ViewImg(buttonSelector, containerSelector) {
   document.querySelectorAll(buttonSelector).forEach(function (e) {
      e.onclick = (e) => {
         e.preventDefault();
         let container = document.querySelector(containerSelector);
         container.removeChild(container.querySelector("img"));
         let value = e.target.value;
         console.log(value, "-----------");
         console.log(e);
         let newImg = document.createElement("img");
         newImg.src = value;
         container.appendChild(newImg);
         

      };
   });
}
