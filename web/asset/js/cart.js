

Array.from(document.getElementsByClassName("cart_action")).forEach(
        (element) => {
    element.onclick = (e) => {
        e.preventDefault();
        console.log();
        let url = `http://localhost:8080/GameCD/cart-game?button=take&id=${e.target.value}`;
        fetch(url)
                .then((response) => response.json())
                .then((response) => {
                    console.log(response.message);
                    Toast({
                        title: response.title,
                        message: response.message,
                        type: response.type,
                        duration: 2000,
                        status: response.status
                    });
                })
                .catch((err) => console.error(err));
    };
}
);

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

// document.getElementById("btn--success").onclick = (e) => {
//    Toast({
//       title: "Success",
//       message: "This is good connection<3",
//       type: "success",
//       duration: 2000,
//    });
// };


