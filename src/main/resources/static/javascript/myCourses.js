console.log("test")

//// Initialization for ES Users
//import {
//  Ripple,
//  initTE,
//} from "tw-elements";
//
//initTE({ Ripple });

//Cookies
const cookieArr = document.cookie.split("=")
console.log("testing cookiesArr", cookieArr)
const userId = cookieArr[1]
console.log("testing userID",  userId)


//base url
const baseUrl = "http://localhost:8080/api/courses"

//headers
const headers = {
    'Content-Type': 'application/json'
}

//first user message remove when there is a course

// get notes
async function getNotes(userId){
    await fetch(`${baseUrl}/student/${userId}`,{
        method: "GET",
        headers: headers
    }).then(res => res.json()).then(data=> displayCourses(data)).catch(err=> console.log(err))
}

const displayCourses = (array) => {

    console.log("testing array line 41", array)
    // Check if the user has courses and remove the "first-user" div if necessary
    if (array.length > 0) {
        const firstUserDiv = document.getElementById("first-user");
        if (firstUserDiv) {
            firstUserDiv.remove();
        }
    }
    const displayDiv = document.getElementById("display-courses")
    displayDiv.className = "grid grid-cols-2 gap-4 border-t-4 border-x-4 container mx-auto py-8"

    displayDiv.innerHTML = `
        <div class="p-6 border-r-4 border-teal-600 " id="doing-courses">
            <h2 class="py-6 mb-3 text-left font-bold drop-shadow-xl text-gray-500 text-2xl">Doing</h2>
        </div> <!-- Close "doing-courses" div -->
        <div class="p-6" id="wishlist-courses">
            <h2 class="py-6 mb-3 text-left font-bold drop-shadow-xl text-zinc-500 text-2xl">Wishlist</h2>
        </div> <!-- Close "wishlist-courses" div>
    `;

    const doingList = document.getElementById("doing-courses");
    const wishlist = document.getElementById("wishlist-courses");

    array.forEach((obj) => {
        console.log('line 51', obj.goal);

        const cardElement1 = `
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
                                <p class="text-base text-neutral-600 dark:text-neutral-200">
                                   Goal <span class="text-base text-neutral-600 dark:text-neutral-200
                                   font-semibold">${obj.goal} </span>
                                </p>

                                   <button type="button" href="#" class="flex ml-auto">
                                       <img src="images/edit.png" class="h-8" />
                                   </button>
                            </div>
            </div>
        `;

        const cardElement2 = `
           <div class="block rounded-lg bg-zinc-400 shadow-lg dark:bg-zinc-400 mb-4">
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
            </div>
                `;


        if (obj.status === 'In Progress') {
            doingList.innerHTML += cardElement1;
        } else if (obj.status === 'Wishlist') {
            wishlist.innerHTML += cardElement2;
        }
    });
}

getNotes(userId)