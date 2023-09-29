console.log("test")

//toggle forms
function toggleForms() {
    const loginDiv = document.getElementById("loginDiv")
    const registerDiv = document.getElementById("registerDiv")
    if (loginDiv.style.display === "block" || loginDiv.style.display === "") {
        loginDiv.style.display = "none";
        registerDiv.style.display = "block";
    } else {
        loginDiv.style.display = "block";
        registerDiv.style.display = "none";
    }
}

//*****----------------------------------******

const baseUrl = 'http://localhost:8080/api/students'
const headers = {
    'Content-Type': 'application/json'
}

//Login Form
const loginForm = document.getElementById("login-form")
const loginUsername = document.getElementById("username")
const loginPassword = document.getElementById("password")

//const registerForm = document.getElementById("register-form")
//const registerUsername = document.getElementById("register-username")
//const registerPassword = document.getElementById("register-password")

//// Function to handle both registration and login
//const handleUserAction = async (evt) => {
//    evt.preventDefault();
//
//    // Determine if the login form or register form is submitted
//    const isLogin = evt.target === loginForm;
//
//    let body = {
//        username: isLogin ? loginUsername.value : registerUsername.value,
//        password: isLogin ? loginPassword.value : registerPassword.value
//    };
//
//    const endpoint = isLogin ? 'login' : 'register';
//
//    try {
//        const response = await fetch(`${baseUrl}/${endpoint}`, {
//            method: "POST",
//            body: JSON.stringify(body),
//            headers: headers
//        });
//
//        const responseArr = await response.json();
//
//        if (response.status === 200) {
//            document.cookie = `userId=${responseArr[1]}`;
//            console.log('testing login linha 57', document.cookie)
//            window.location.replace(responseArr[0]);
//        }
//    } catch (err) {
//        console.error(err.message);
//    }
//}
//
//// Add event listeners to both forms to handle user actions
//loginForm.addEventListener("submit", handleUserAction);
//registerForm.addEventListener("submit", handleUserAction);

// handle Login form
const login = async(evt)=>{
    evt.preventDefault()


    let body = {
        username: loginUsername.value,
        password: loginPassword.value
    }

    console.log("username", loginUsername.value)
    console.log("corpo", body)
    const response = await fetch(`${baseUrl}/login`, {
        method: "POST",
        body: JSON.stringify(body),
        headers: headers
    }).catch(err=> console.error(err.message))

    const responseArr = await response.json()

    if(response.status === 200){
        document.cookie = `userId=${responseArr[1]}`
        console.log("testing cookie linha 87", document.cookie)
        window.location.replace(responseArr[0])
    }
}

//login form event listener
loginForm.addEventListener("submit", login)


//*****----------------------------------******

//Register Form
const registerForm = document.getElementById("register-form")
const registerUsername = document.getElementById("register-username")
const registerPassword = document.getElementById("register-password")

// handle the register form
const register = async(evt) => {
    evt.preventDefault()

    let body = {
        username: registerUsername.value,
        password: registerPassword.value
    }

    const res = await fetch(`${baseUrl}/register`,{
        method: "POST",
        body: JSON.stringify(body),
        headers: headers
    }).catch(err=> console.error(err.message))

    const responseArr = await res.json()
    console.log("testing", responseArr[0])
    if(res.status === 200){
        document.cookie = `userId=${responseArr[1]}`
        console.log("testing cookie linha 122 register", document.cookie)
        window.location.replace("http://localhost:8080/login.html")
//        window.location.replace(responseArr[0])
    }
}
// register form event listener
registerForm.addEventListener("submit", register)


