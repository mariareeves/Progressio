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

document.addEventListener("DOMContentLoaded", function() {
    // grabbing the elements values
    const formCourses = document.getElementById("create-form");
    const courseName = document.getElementById("course-name");
    const coursePlatform = document.getElementById("platform-name");
    const courseHours = document.getElementById("course-hours");
    const selectedStatus = document.getElementById("status");

    // add course
    const handleSubmit = async(evt) => {
        evt.preventDefault();

        // Move this line inside the event handler
        const selectedPriority = document.querySelector('input[name="priorityOptions"]:checked');
        const selectedPriorityValue = selectedPriority ? selectedPriority.value : null;

        console.log("testing course name", courseName.value);
        console.log("testing nome da escola", coursePlatform.value);
        console.log("testing curso horas", courseHours.value);
        console.log("testing selectedPriority", selectedPriorityValue);
        console.log("testing status", selectedStatus.value);

        let body = {
            courseName: courseName.value,
            institutionPlatform: coursePlatform.value,
            totalHours: courseHours.value,
            priority: selectedPriorityValue,
            status: selectedStatus.value
        };

        console.log("test body line 28", body)
        await addCourse(body)
        courseName.value = ''
        coursePlatform.value = ''
        courseHours.value = ''

        async function addCourse(obj) {
            const res = await fetch(`${baseUrl}/student/${userId}`, {
                method: "POST",
                body: JSON.stringify(obj),
                headers: headers
            }).catch(err => console.error(err.message))
            if (res.status === 200) {
                alert("Courses created!")
            }
        }
    }

    formCourses.addEventListener("submit", handleSubmit);
});
