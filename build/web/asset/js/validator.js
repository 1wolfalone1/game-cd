function Validator(validator) {
   this.allData = {};
   this.api = function () {};
   var formRules = {};
   var formElement = document.getElementById(validator.form);
   validator.rules.forEach((rule) => {
      if (Array.isArray(formRules[rule.selector])) {
         formRules[rule.selector].push(rule);
      } else {
         formRules[rule.selector] = [rule];
      }
   });
   var formRolesKeys = Object.keys(formRules);

   formRolesKeys.forEach((selector) => {
      var inputRules = formRules[selector];
      var input = document.querySelector(selector);
      inputRules.forEach((rule) => {
         if (rule.type === "text") {
            input.addEventListener("blur", (e) => {
               validate(input, rule, true);
            });
            input.addEventListener("input", (e) => {
               validate(input, rule, false);
            });
         } else if (rule.type === "choice") {
            if (input.type === "radio" || input.type === "checkbox") {
               console.log(rule.listControl);
               rule.listControl.forEach((inputItem) => {
                  inputItem.addEventListener("click", (e) => {
                     validate(inputItem, rule, true);
                  });
               });
            } else if (input.type === "select") {
            }
         }
      });
   });
   formElement.querySelector("#submit").onclick = (e) => {
      if (isValidated()) {
         if (document.getElementById("check-tos").checked) {
            console.log(document.getElementById("check-tos").checked);
         } else {
            console.log(document.getElementById("check-tos").checked);
            document.getElementById("checkstatus").innerHTML =
               "Need to agree with the term of service!";
            e.preventDefault();
         }
         // this.allData = getData();

         // this.api();
      } else {
         e.preventDefault();
      }
   };

   function isValidated() {
      var isValid = true;
      formRolesKeys.forEach((selector) => {
         var inputRules = formRules[selector];
         var input = document.querySelector(selector);
         inputRules.forEach((rule) => {
            if (!validate(input, rule, true)) {
               isValid = false;
            }
         });
      });
      return isValid;
   }

   function getData() {
      validator.data = {};
      var listData = formElement.querySelectorAll(validator.formDataSelector);
      listData.forEach((input) => {
         if (input.type == "radio" || input.type == "checkbox") {
            if (input.checked) {
               if (validator.data[input.name]) {
                  validator.data[input.name] = [validator.data[input.name]];
                  validator.data[input.name].push(input.value);
               } else {
                  validator.data[input.name] = input.value;
               }
            }
         } else {
            if (validator.data[input.name]) {
               validator.data[input.name] = [validator.data[input.name]];
               validator.data[input.name].push(input.value);
            } else {
               validator.data[input.name] = input.value;
            }
         }
      });
      console.log(this, "????)");
      return validator.data;
   }
   function getParentControl(element, paSelector) {
      while (element) {
         if (element.matches(paSelector)) {
            break;
         }
         element = element.parentElement;
      }
      return element;
   }
   function validate(inputElement, rule, isBlur) {
      var test = rule.getMesError(inputElement);
      var parentElement = getParentControl(inputElement, validator.formElement);
      var errorElement = parentElement.querySelector(validator.formStatus);
      if (test && isBlur) {
         errorElement.innerText = test;
         inputElement.status = rule;
      } else {
         if (inputElement.status === rule || rule.type === "choice") {
            errorElement.innerText = "";
         }
      }
      return rule.status;
   }
}

Validator.isRequired = (selector, message) => {
   return {
      selector: selector,
      type: "text",
      status: false,
      getMesError(inputElement) {
         var value = inputElement.value;
         if (value && value.trim()) {
            this.status = true;
            return undefined;
         }
         return message;
      },
   };
};
Validator.isEmail = (selector, message) => {
   return {
      selector: selector,
      type: "text",
      status: false,
      getMesError(inputElement) {
         var value = inputElement.value;
         var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
         if (regex.test(value)) {
            this.status = true;
            return undefined;
         }

         return message;
      },
   };
};
Validator.isPhoneNumber = (selector, message) => {
   return {
      selector: selector,
      type: "text",
      status: false,
      getMesError(inputElement) {
         var value = inputElement.value;
         var regex = /(84|0[3|5|7|8|9])+([0-9]{7,11})\b/g;
         if (regex.test(value)) {
            this.status = true;
            return undefined;
         }
         return message;
      },
   };
};

Validator.isTextInIntervalRange = (selector, min, max, message) => {
   return {
      selector: selector,
      type: "text",
      status: false,
      getMesError(inputElement) {
         var value = inputElement.value;
         if (value.length >= min && value.length <= max) {
            this.status = true;
            return undefined;
         }
         return message;
      },
   };
};
