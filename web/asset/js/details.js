
function Slider(list) {
    var listImg = list;
    var current = list[0];
    var blurImg = "filter: brightness(50%) contrast(80%) !important";
    var highlightImg = "filter: brightness(100%) contrast(100%) !important";
    var imgBottomList = [];
    const slider = document.getElementById("slider");
    const sliderBottom = slider.querySelector(".slider_bottom");
    const img = slider.querySelector(".img");
    const sliderPre = slider.querySelector(".control_left");
    const sliderNext = slider.querySelector(".control_right");
    const imgRepos = slider.querySelector(".img_repos");
    function firstAppend() {
        let img = slider.querySelector("#img");
        let newImg = document.createElement("img");
        newImg.src = list[0].link;
        img.querySelector(".img_repos").appendChild(newImg);

        list.forEach((element, i) => {
            let newImgBottom = document.createElement("img");
            newImgBottom.src = element.link;
            newImgBottom.classList.add(`img${i}`);
            sliderBottom.appendChild(newImgBottom);
            imgBottomList.push(newImgBottom);
            if (i == 0) {
                newImgBottom.setAttribute("style", highlightImg);
            }
        });
    }
    firstAppend();

    sliderPre.onclick = function (e) {
        let newImg = document.createElement("img");
        let i = current.index;
        sliderBottom.querySelector(`.img${i}`).setAttribute("style", blurImg);
        let pre = current;
        let preImg = imgRepos.querySelector(`img[src="${pre.link}"]`);
        console.log(preImg);
        if (i == 0) {
            i = list.length - 1;
            current = list[list.length - 1];
        } else {
            i = i - 1;
            current = list[i];
        }
        sliderBottom.querySelector(`.img${i}`).setAttribute("style", highlightImg);
        newImg.src = current.link;
        newImg.style.transform = "translateX(-100%)";
        console.log(current);
        imgRepos.appendChild(newImg);
        newImg.style.zIndex = "10";
        setTimeout(function () {
            newImg.style.transform = "";
            preImg.style.transform = "translateX(100%)";
        }, 1);
        setTimeout(function () {
            preImg.remove();
        }, 200);
    };

    sliderNext.onclick = function (e) {
        let newImg = document.createElement("img");
        let i = current.index;
        sliderBottom.querySelector(`.img${i}`).setAttribute("style", blurImg);
        let pre = current;
        let preImg = imgRepos.querySelector(`img[src="${pre.link}"]`);
        console.log(current.link, "link ne");
        if (i == list.length - 1) {
            i = 0;
            current = list[0];
        } else {
            i = i + 1;
            current = list[i];
        }
        sliderBottom.querySelector(`.img${i}`).setAttribute("style", highlightImg);
        newImg.src = current.link;
        newImg.style.transform = "translateX(100%)";
        console.log(current);
        imgRepos.appendChild(newImg);
        newImg.style.zIndex = "10";

        setTimeout(function () {
            newImg.style.transform = "";
            preImg.style.transform = "translateX(-100%)";
        }, 1);
        setTimeout(function () {
            preImg.remove();
        }, 200);
    };
    imgBottomList.forEach(function (img, i) {
        img.onclick = (e) => {
            if (e.target.classList.contains(`img${current.index}`)) {
            } else {
                let newImg = document.createElement("img");
                let pre = current;
                let preImg = imgRepos.querySelector(`img[src="${pre.link}"]`);
                sliderBottom.querySelector(`.img${current.index}`).
                        setAttribute("style", blurImg);

                current = {
                    link: e.target.src,
                    index: i,
                };
                sliderBottom.querySelector(`.img${i}`).setAttribute("style", highlightImg);
                newImg.src = current.link;
                let back = "translateX(-100%)";
                let next = "translateX(100%)";
                if (pre.index > current.index) {
                    let k = back;
                    back = next;
                    next = k;
                }

                newImg.style.transform = next;
                imgRepos.appendChild(newImg);
                newImg.style.zIndex = "10";
                setTimeout(function () {
                    newImg.style.transform = "";
                    preImg.style.transform = back;
                }, 1);
                setTimeout(function () {
                    preImg.remove();
                }, 200);
            }
        };
    });
}




function ButtonChangeNumber(
        parentSelector,
        deSelector,
        inSelector,
        inputSelector
        ) {
    var parents = document.querySelectorAll(parentSelector);
    parents.forEach(function (parent) {
        let dec = parent.querySelector(deSelector);
        let inc = parent.querySelector(inSelector);
        let input = parent.querySelector(inputSelector);
        let id = parent.querySelector(".id");
        let maxValue = input.max;
        let minValue = input.min;
        dec.onclick = (e) => {
            e.preventDefault();
            if (Number(input.value) > Number(minValue)) {
                input.value = Number(input.value) - 1;
            }
        };

        inc.onclick = (e) => {
            e.preventDefault();
            if (Number(input.value) < Number(maxValue)) {
                input.value = Number(input.value) + 1;
            }
        };
    });
}

function addToCart(id) {
    document.getElementById('add_to_cart').onclick = (e) => {
        e.preventDefault();
        let quantity = document.getElementById('quantity_to_cart').value;
        let url = `http://localhost:8080/GameCD/cart-game?button=change&id=${id}&quantity=${quantity}`;
        fetch(url)
                .then((response) => response.json())
                .then((response) => {
                    console.log(response.message);
                        Toast({
                            title: response.title,
                            message: response.message,
                            type: response.type,
                            duration: 2000,
                            status: response.status,
                        });
                })
                .catch((err) => console.error(err));
    };
}

function Toast({
title = "",
        message = "",
        type = "info",
        duration = 5000,
        status = 1
        }) {
    const main = document.getElementById("toast");
    if (main) {
        const toast = document.createElement("div");
        toast.classList.add("toast");
        toast.classList.add(`toast--${type}`);
        const delay = (duration / 1000).toFixed(3);
        toast.style.animation = `slideLeft ease 0.9s, fadeOut linear 1s ${delay}s forwards`;
        if (status == 1) {
            toast.innerHTML = `
        <div class="toast_icon">
           <i class="fa-solid fa-check"></i>
        </div>
        <div class="toast_body">
           <h3 class="toast_title">${title}</h3>
           <p class="toast_msg">${message}</p>
        </div>
        <div class="toast_close">
           <i class="fa-solid fa-xmark"></i>
        </div>
        `;
        } else if (status == 0) {
            toast.innerHTML = `
        <div class="toast_icon">
        <i class="fa-solid fa-exclamation"></i>
        </div>
        <div class="toast_body">
           <h3 class="toast_title">${title}</h3>
           <p class="toast_msg">${message}</p>
        </div>
        <div class="toast_close">
           <i class="fa-solid fa-xmark"></i>
        </div>
        `;
        } else if (status == -1) {
            toast.innerHTML = `
        <div class="toast_icon">
                               <i class="fa-solid fa-bug"></i>

        </div>
        <div class="toast_body">
           <h3 class="toast_title">${title}</h3>
           <p class="toast_msg">${message}</p>
        </div>
        <div class="toast_close">
           <i class="fa-solid fa-xmark"></i>
        </div>
        `;
        }

        var timeOutID = setTimeout(() => {
            main.removeChild(toast);
        }, duration + 1000);
        main.appendChild(toast);
        toast.querySelector(".toast_close i").onclick = function () {
            main.removeChild(toast);
            clearTimeout(timeOutID);
        };
}
}