console.log("test")

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
async function getCourses(userId){
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
        console.log("testing id", obj.id)

        const cardElement1 = `
         <div class="block rounded-lg bg-teal-500 shadow-lg dark:bg-teal-500 mb-4">
                                <h5 id="courseName" class="border-neutral-50 px-6 py-3 text-xl font-medium leading-tight dark:text-neutral-50 flex items-center">
                                  ${obj.courseName} Course
                                  <div class="ml-auto">
                                    <button onclick="handleDelete(${obj.id})">
                                      <img src="images/trash-bin.png" class="h-8" />
                                    </button>
                                  </div>
                                </h5>
                                <div class="p-6">
                                    <h5 id="platform"
                                            class="mb-2 text-lg font-medium leading-tight text-neutral-800 dark:text-neutral-50">
                                        ${obj.institutionPlatform}
                                    </h5>
                                    <p id="totalHours"  class="mb-2 text-base text-neutral-600 dark:text-neutral-200">
                                        Total ${obj.totalHours} hours
                                    </p>
                                    <p id="priority"  class="text-base text-neutral-600 dark:text-neutral-200">
                                        Priority ${obj.priority}
                                    </p>
                                     <p  id="goal"class="text-base text-neutral-600 dark:text-neutral-200">
                                        Goal <span class="text-base text-neutral-600 dark:text-neutral-200 font-semibold">${obj.goal} </span>
                                     </p>

                                        <button type="button" class="flex ml-auto" onclick="toggleModalAndFetchCourse(${obj.id})">
                                            <img src="images/edit.png" class="h-8" />
                                        </button>
                                 </div>
                 </div>

        `;

        const cardElement2 = `
        <div class="block rounded-lg bg-gray-400 shadow-lg dark:bg-gray-400 mb-4">
                                   <h5 id="courseName" class="border-neutral-50 px-6 py-3 text-xl font-medium leading-tight dark:text-neutral-50 flex items-center">
                                                                ${obj.courseName} Course
                                                                <div class="ml-auto">
                                                                  <button onclick="handleDelete(${obj.id})">
                                                                    <img src="images/trash-bin.png" class="h-8" />
                                                                  </button>
                                                                </div>
                                                              </h5>
                                  <div class="p-6">
                                      <h5 id="platform"
                                              class="mb-2 text-lg font-medium leading-tight text-neutral-800 dark:text-neutral-50">
                                          ${obj.institutionPlatform}
                                      </h5>
                                      <p id="totalHours"  class="mb-2 text-base text-neutral-600 dark:text-neutral-200">
                                          Total ${obj.totalHours} hours
                                      </p>
                                      <p id="priority"  class="text-base text-neutral-600 dark:text-neutral-200">
                                          Priority ${obj.priority}
                                      </p>
                                       <p  id="goal"class="text-base text-neutral-600 dark:text-neutral-200">
                                          Goal <span class="text-base text-neutral-600 dark:text-neutral-200 font-semibold">${obj.goal} </span>
                                       </p>

                                              <button type="button" class="flex ml-auto" onclick="toggleModalAndFetchCourse(${obj.id})">
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

getCourses(userId)

//toggle edit button
function toggleModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.classList.toggle('hidden');

    // You can add the event listener for the close button here if needed.
    const closeButton = document.getElementById("updateModalBtn");
    closeButton.addEventListener('click', function () {
        modal.classList.add('hidden');

    });
}

function toggleModalAndFetchCourse(courseId) {
  toggleModal('updateModal');
  getCourseById(courseId);
}

////update courses

const courseName = document.getElementById("courseName");
const platform = document.getElementById("platform");
const priority = document.getElementById("priority");
const goal = document.getElementById("goals");
const updateBtn = document.getElementById("updateBtn")
const hours = document.getElementById("hours")
const updateForm = document.getElementById("update-form")


async function getCourseById(courseId){
    await fetch(`${baseUrl}/${courseId}`, {
        method: "GET",
        header: headers
    }).then(res => res.json()).then(data=> {
        console.log("test data", data)
        populateModal(data)
    }).catch(err => console.log(err))
}

async function handleEdit(courseId) {
  // Define the body with the properties you want to update.
  const body = {
    id: courseId,
    courseName: courseName.value,
    institutionPlatform: platform.value,
    totalHours: hours.value,
    priority: priority.value,
    goal: goals.value
  };

  console.log("testing body update", body);

  try {
    await fetch(`${baseUrl}`, {
      method: "PUT",
      body: JSON.stringify(body),
      headers: headers
    });

    // If the update was successful, redirect the user to myCourse.html
    window.location.href = "myCourses.html";
    return getCourses(userId);

  } catch (err) {
    console.log(err);
    // Handle the error if needed
  }
}


const populateModal = (obj) => {
    console.log('testing modal', obj)
    console.log('testing', obj.courseName)
    console.log(obj.institutionPlatform)
    console.log(obj.totalHours)
    console.log(obj.priority)
    console.log(obj.goal)


    courseName.value = obj.courseName;
    platform.value = obj.institutionPlatform;
    priority.value = obj.priority;
    hours.value = obj.totalHours;
    goals.value = obj.goal;

    updateBtn.setAttribute('data-course-id', obj.id)
}


updateBtn.addEventListener('click', (e)=> {
    let courseId = e.target.getAttribute('data-course-id')
    handleEdit(courseId)
})

// delete a course
async function handleDelete(courseId){
    await fetch(`${baseUrl}/${courseId}`, {
        method: "DELETE",
        headers: headers
    }).catch(err=> console.log(err))

    return getCourses(userId)
}