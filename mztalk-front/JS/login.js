// Elements
const el = {
    signUpHome: document.getElementById('sign-up'),
    signInHome: document.getElementById('sign-in'),
    btnHome: document.querySelector('.btn-back'),
    pageMain: document.querySelector('.main'),
    pageHome: document.querySelector('.home'),
    pageSignUp: document.querySelector('.sign-up'),
    formArea: document.querySelector('.form-area'),
    sideSignLeft: document.querySelector('.signup-left'),
    sideSignRight: document.querySelector('.signup-right'),
    formSignUp: document.querySelector('.form-area-signup'),
    formSignIn: document.querySelector('.form-area-signin'),
    linkUp: document.querySelector('.link-up'),
    linkIn: document.querySelector('.link-in'),
    btnSignUp: document.querySelector('.btn-up'),
    btnSignIn: document.querySelector('.btn-in'),
    labels: document.getElementsByTagName('label'),
    inputs: document.getElementsByTagName('input'),
  };
  
  
  // ADD Events
  // Show the page Sign Up
  el.signUpHome.addEventListener('click', function(e) {
    showSign(e, 'signup');
  });
  el.linkUp.addEventListener('click', function(e) {
    showSign(e, 'signup');
  });
  
  // Show the page sign in
  el.signInHome.addEventListener('click', function(e) {
    showSign(e, 'signin');
  });
  el.linkIn.addEventListener('click', function(e) {
    showSign(e, 'signin');
  });
  el.btnSignUp.addEventListener('click', function(e) {
    showSign(e, 'signin');
  });
  
  // Show the page Home
  el.btnHome.addEventListener('click', showHome);
  
  
  // Functions Events
  // function to show screen Home
  function showHome(event) {
    
    
    setTimeout(function() {
      el.sideSignLeft.style.padding = '0';
      el.sideSignLeft.style.opacity = '0';
      el.sideSignRight.style.opacity = '0';
      el.sideSignRight.style.backgroundPositionX = '235%';
  
      el.formArea.style.opacity = '0';
      setTimeout(function() {
        el.pageSignUp.style.opacity = '0';
        el.pageSignUp.style.display = 'none';
        for (input of el.inputs)  {
          input.value = '';
        }
      }, 900);
  
    }, 100);
  
    setTimeout(function() {
      el.pageHome.style.display = 'flex';
    },1100);
    
    setTimeout(function() {
      el.pageHome.style.opacity = '1';
    }, 1200);
  
  }
  // function to show screen Sign up/Sign in
  function showSign(event, sign) {
  
    if (sign === 'signup') {
      el.formSignUp.style.display = 'flex';
      el.formSignIn.style.opacity = '0';
      setTimeout(function() {
        el.formSignUp.style.opacity = '1';
      }, 100);
      el.formSignIn.style.display = 'none';
  
    } else {
      el.formSignIn.style.display = 'flex';
      el.formSignUp.style.opacity = '0';
      setTimeout(function() {
        el.formSignIn.style.opacity = '1';
      }, 100);
      el.formSignUp.style.display = 'none';
    }
  
    el.pageHome.style.opacity = '0';
    setTimeout(function() {
      el.pageHome.style.display = 'none';
    }, 700);
    
    setTimeout(function() {
      el.pageSignUp.style.display = 'flex';
      el.pageSignUp.style.opacity = '1';
      
      setTimeout(function() {
        el.sideSignLeft.style.padding = '20px';
        el.sideSignLeft.style.opacity = '1';
        el.sideSignRight.style.opacity = '1';
        el.sideSignRight.style.backgroundPositionX = '230%';
  
        el.formArea.style.opacity = '1';
      }, 10);
  
    }, 900);
  }
  
  // Behavior of the inputs and labels
  for (input of el.inputs) {
    console.log(input)
    input.addEventListener('keydown', function() {
      this.labels[0].style.top = '10px';
    });
    input.addEventListener('blur', function() {
      if (this.value === '') {
        this.labels[0].style.top = '25px';
      }
    })
  }
  


const nameBtn = document.querySelector('.userName');
const passwordBtn = document.querySelector('.userPassword');
const loginBtn = document.querySelector('.loginBtn');

function goToMain(){
    location.href = "main.html"
}

nameBtn.addEventListener('keyup', function(){
    let nameLength = nameBtn.value.length;
    let passwordLength = passwordBtn.value.length;

    if(nameLength >= 1 && passwordLength >= 5 && nameBtn.value.indexOf('@') !== -1 ){
        loginBtn.style.backgroundColor = "#0095f6"; 
        loginBtn.addEventListener('click', goToMain)       
    } else {
        loginBtn.style.backgroundColor = "#b2dffc";
    }
});

passwordBtn.addEventListener('keyup', function(){
    let nameLength = nameBtn.value.length;
    let passwordLength = passwordBtn.value.length;

    if(nameLength >= 1 && passwordLength >= 5 && nameBtn.value.indexOf('@') !== -1){
        loginBtn.style.backgroundColor = "#0095f6"; 
        loginBtn.addEventListener('click', goToMain)       
    } else {
        loginBtn.style.backgroundColor = "#b2dffc";
    }
});




