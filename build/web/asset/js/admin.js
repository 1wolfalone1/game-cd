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