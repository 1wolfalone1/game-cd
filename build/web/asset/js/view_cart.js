var totalPriceOfOrder = document.querySelector('#total_price_order');

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
        let price = parent.querySelector(".price").value;
        let totalPrice = parent.nextElementSibling.querySelector(
                ".total_price_number"
                );
        dec.onclick = (e) => {
            e.preventDefault();
            if (Number(input.value) > Number(minValue)) {
                input.value = Number(input.value) - 1;
                apiDecreaseItem(id.value);
                totalPriceOfOrder.innerHTML = Number(totalPriceOfOrder.innerHTML) - Number(price);
                totalPrice.innerHTML = Number(totalPrice.innerHTML) - Number(price);
                if (input.value == 0) {
                    var event = new CustomEvent("click");
                    parent.nextElementSibling.nextElementSibling.dispatchEvent(
                            new CustomEvent("click", {bubbles: true})
                            );
                }
            }
        };
        input.onchange = (e) => {
            console.log(input.value);
            totalPriceOfOrder.innerHTML = Number(totalPriceOfOrder.innerHTML) - totalPrice.innerHTML;
            totalPrice.innerHTML = Number(input.value) * price;
            totalPriceOfOrder.innerHTML = Number(totalPriceOfOrder.innerHTML) + Number(totalPrice.innerHTML);
            apiChangeItem(id.value, input.value);
        };
        inc.onclick = (e) => {
            e.preventDefault();
            if (Number(input.value) < Number(maxValue)) {
                input.value = Number(input.value) + 1;
                apiIncrease(id.value);
                totalPrice.innerHTML = Number(totalPrice.innerHTML) + Number(price);
                totalPriceOfOrder.innerHTML = Number(totalPriceOfOrder.innerHTML) + Number(price);
            }
        };
    });
}

function apiChangeItem(id, quantity) {
    let url = `http://localhost:8080/GameCD/cart-game?button=change&id=${id}&quantity=${quantity}`;
    console.log(url);
    fetch(url)
            .then((response) => response.json())
            .then((response) => {
                console.log(response.message);
                if (response.status != 1) {
                    Toast({
                        title: response.title,
                        message: response.message,
                        type: response.type,
                        duration: 2000,
                        status: response.status,
                    });
                }
            })
            .catch((err) => console.error(err));
}
function apiDecreaseItem(id) {
    let url = `http://localhost:8080/GameCD/cart-game?button=decrease&id=${id}`;
    fetch(url)
            .then((response) => response.json())
            .then((response) => {
                console.log(response.message);
                if (response.status != 1) {
                    Toast({
                        title: response.title,
                        message: response.message,
                        type: response.type,
                        duration: 2000,
                        status: response.status,
                    });
                }
            })
            .catch((err) => console.error(err));
}
function apiIncrease(id) {
    let url = `http://localhost:8080/GameCD/cart-game?button=increase&id=${id}`;
    fetch(url)
            .then((response) => response.json())
            .then((response) => {
                console.log(response.message);
                if (response.status != 1) {
                    Toast({
                        title: response.title,
                        message: response.message,
                        type: response.type,
                        duration: 2000,
                        status: response.status,
                    });
                }
            })
            .catch((err) => console.error(err));
}

function RemoveItem(buttonSelector, parentSelector, maxnumberOfItems) {
    var currentItem = maxnumberOfItems;
    var indexItem = document.querySelector(".status_header");
    document.querySelectorAll(buttonSelector).forEach((button) => {
        button.onclick = (e) => {
            if (e)
                e.preventDefault();
            let parent = getParent(e.target, parentSelector);
            let id = parent.querySelector(".id");
            currentItem--;
            indexItem.innerHTML = currentItem;
            apiRemove(id.value);
            parent.style = "transform: translateX(-100%);";
            console.log(`error_quantity_${id.value}`);
            if (document.getElementById(`error_quantity_${id.value}`)) {
                console.log(document.getElementById(`error_quantity_${id.value}`));
                document.getElementById(`error_quantity_${id.value}`).remove();
            }
            setTimeout(() => {
                totalPriceOfOrder.innerHTML =
                        Number(totalPriceOfOrder.innerHTML) - Number(parent.querySelector('.total_price_number').innerHTML);
                parent.remove();
                if (currentItem == 0) {
                    changeState();
                }
            }, 500);
        };
    });
}

function apiRemove(id) {
    let url = `http://localhost:8080/GameCD/cart-game?button=remove&id=${id}`;
    fetch(url)
            .then((response) => response.json())
            .then((response) => {
                console.log(response.message);
                if (response.status != 1) {
                    Toast({
                        title: response.title,
                        message: response.message,
                        type: response.type,
                        duration: 2000,
                        status: response.status,
                    });
                }
            })
            .catch((err) => console.error(err));
}

function changeState() {
    document.querySelector(
            ".table_cart_game"
            ).innerHTML = `<div class="header_cart">
                    <h1 class="shopping_cart_title">Shopping cart</h1>

                        <h3 class="status_cart">None of game CD in your cart</h3>
  
               
                </div>
         
                    <div>
                        <img src="/GameCD/asset/img/empty-cart.png" alt="">
                    </div>`;
}

function getParent(element, parentSelector) {
    while (element) {
        if (element.parentElement.matches(parentSelector)) {
            return element.parentElement;
        }
        element = element.parentElement;
    }
}

function Toast( {
title = "",
        message = "",
        type = "info",
        duration = 5000,
        status = 1,
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


