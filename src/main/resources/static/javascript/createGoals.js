console.log("test")

//Cookies
const cookieArr = document.cookie.split("=")
console.log("testing cookiesArr", cookieArr)
const userId = cookieArr[1]
console.log("testing userID",  userId)


//base url
const baseUrl = "http://localhost:8080/api/goals"

//headers
const headers = {
    'Content-Type': 'application/json'
}

document.addEventListener("DOMContentLoaded", function() {
    // grabbing the elements values
   const goalForm = document.getElementById("goal-form")
   const startDate = document.getElementById("start")
   const endDate = document.getElementById("end")
   const goalDescription = document.getElementById("message")


    // add course
    const handleSubmit = async(evt) => {
        evt.preventDefault();


        console.log("testing goal message", goalDescription.value);
        console.log("testing date start", startDate.value);
        console.log("testing date end", endDate.value);


        let body = {
           initialDate: startDate.value,
           finalDate: endDate.value,
           goalDescription: goalDescription.value
        };

        console.log("test body line 28", body)
        await addGoal(body)
        goalDescription.value = ''


        async function addGoal(obj) {
            const res = await fetch(`${baseUrl}/student/${userId}`, {
                method: "POST",
                body: JSON.stringify(obj),
                headers: headers
            }).catch(err => console.error(err.message))
            if (res.status === 200) {
                alert("Goals created!")
            }
        }
    }

    goalForm.addEventListener("submit", handleSubmit);


    /// get courses
    const courseUrl = "http://localhost:8080/api/courses"

   async function getNotes(userId) {
       await fetch(`${courseUrl}/student/goal/${userId}`, {
           method: "GET",
           headers: headers
       })
       .then(res => {
           if (res.status === 200) {
               return res.json();
           } else {
               throw new Error(`Request failed with status ${res.status}`);
           }
       })
       .then(data => displayCourses(data))
       .catch(err => console.error(err));
   }

    const displayCourses = (array) => {
        console.log("testing array line 41", array);

        const displayDiv = document.getElementById("display-courses");
        displayDiv.className = "pt-6";

        if (Array.isArray(array)) {
            array.forEach((obj) => {
                console.log('line 80', obj.goal);

                const cardElement = `
                    <div class="block p-3 mb-3 w-full text-md font-medium border-teal-600 bg-teal-600 rounded-md border hover:bg-teal-500 focus:border-teal-600 dark:bg-teal-600 dark:border-teal-700 dark:text-white dark:focus:ring-teal-600 dark:focus:border-teal-600">
                        ${obj.courseName} Course
                    </div>
                `;


                displayDiv.innerHTML += cardElement;

            });
        } else {
            console.error('Array is not valid:', array);
        }
    }


    getNotes(userId)
});


