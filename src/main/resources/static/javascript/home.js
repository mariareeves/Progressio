console.log("test")

//logout
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

// get goals
async function getGoals(userId){
    await fetch(`${baseUrl}/student/${userId}`,{
        method: "GET",
        headers: headers
    }).then(res => res.json()).then(data=> displayGoal(data)).catch(err=> console.log(err))
}

const displayGoal = (array) => {
     console.log("testing array line 27", array)

    // Check if the user has goals; remove the "welcome" message if necessary
    if (array.length > 0) {
        const welcomeMsgElements = document.getElementsByClassName("welcomeMsg");
        console.log("WelcomeMsg elements found:", welcomeMsgElements.length);

        // Convert the HTMLCollection to an array
        const welcomeMsgArray = Array.from(welcomeMsgElements);

        // Loop through the array and remove each element
        for (let i = 0; i < welcomeMsgArray.length; i++) {
            console.log("Removing element:", welcomeMsgArray[i]);
            welcomeMsgArray[i].remove();
        }
    }

        const displayGoals = document.getElementById("display-goals")
        let goalElement = ''

        array.forEach((obj)=> {
            console.log("testing obj line 49", obj)
            console.log("initial date", obj.initialDate)
            console.log("final date", obj.finalDate )

            goalElement += `
                        <p class="block mb-2 text-lg font-semibold text-gray-900 dark:text-gray-500 ">From  <span
                        class="text-md px-2 font-semibold text-amber-300 dark:text-amber-500" >${obj
                        .initialDate}</span>  To
                       <span class="text-md px-2 font-semibold text-amber-300 dark:text-amber-500" > ${obj.finalDate}
                       </span> </p>
                        <p class="block py-2 text-md font-medium text-gray-900 dark:text-gray-500 text-left">${obj
                        .goalsDescription}</p>
                    `
        })

        displayGoals.innerHTML += goalElement


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

                if (Array.isArray(array)) {
                    array.forEach((obj) => {
                        console.log('line 80', obj.goal);

                        const cardElement = `
                                <div class="block rounded-lg bg-teal-500 shadow-lg dark:bg-teal-500 mb-4">
                                    <h5
                                            class="border-neutral-50 px-6 py-3 text-xl font-medium leading-tight dark:text-neutral-50">
                                        ${obj.courseName} Course
                                    </h5>
                                    <div class="p-6">
                                        <h5
                                                class="mb-2 text-lg font-medium leading-tight text-neutral-800 dark:text-neutral-50">
                                            ${obj.institutionPlatform}
                                        </h5>
                                        <p class="mb-2 text-base text-neutral-600 dark:text-neutral-200">
                                            Total ${obj.totalHours} hours
                                        </p>
                                        <p class="text-base text-neutral-600 dark:text-neutral-200">
                                            Priority ${obj.priority}
                                        </p>

                                        <button type="button" href="#" class="flex ml-auto">
                                            <img src="images/edit.png" class="h-8" />
                                        </button>
                                    </div>
                        `;


                        displayDiv.innerHTML += cardElement;

                    });
                } else {
                    console.error('Array is not valid:', array);
                }
            }


            getNotes(userId)

}

getGoals(userId)







