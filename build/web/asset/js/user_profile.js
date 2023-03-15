function ChangeInfo(
   parentSelector,
   inputSelector,
   buttonSelector,
   actionSelector
) {
   var action = document.querySelector(actionSelector);
   var button = document.querySelector(buttonSelector);
   var parent = document.querySelector(parentSelector);
   console.log(parentSelector);
   button.onclick = (e) => {
      e.preventDefault();
      parent.querySelectorAll(inputSelector).forEach((element) => {
         enableInput(element.querySelector("input"));
         if (element.classList.contains("change")) {
            element.style = "max-height: 100px;";
         } else {
            console.log(element.querySelector('input').value)
            element.setAttribute('oldinput', element.querySelector("input").value) ;
            console.log(element.getAttribute('oldinput'));
         }
      });
      document.querySelector('#input_avatar').style = "max-height: 100px;";
      action.style = "max-height: 100px;";
   };
}

function enableActionChange() {}
function enablePasswordsChange() {}
function enableInput(input) {
   input.removeAttribute("disabled");
   input.classList.add("on_input");
}

function disableInput(input) {
   input.setAttribute("disabled", true);
   input.classList.remove("on_input");
}

function Cancel(parentSelector, inputSelector, buttonSelector, actionSelector, tmp) {
   var action = document.querySelector(actionSelector);
   var button = document.querySelector(buttonSelector);
   var parent = document.querySelector(parentSelector);
   button.onclick = (e) => {
      e.preventDefault();
      parent.querySelectorAll(inputSelector).forEach((element) => {
         disableInput(element.querySelector("input"));
         if (element.classList.contains("change")) {
            element.style = "max-height: 0";
         } else {
            element.querySelector("input").value = element.getAttribute('oldinput');
         }
      });
      document.getElementById('file_input').value = '';
      document.querySelector('#input_avatar').style = "max-height: 0;";
      clearError();
      action.style = "max-height: 0";
   };
}

function clearError(){
    document.querySelectorAll('.error_info').forEach((e) => {
        e.innerHTML = '';
    })
}

function OpenChangeInfo(status){
   if(status){
       console.log(status);
      document.querySelector('#change_info_button').click();
   }
}