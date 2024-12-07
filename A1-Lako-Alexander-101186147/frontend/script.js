const apiBaseUrl = "http://localhost:8080";

async function startGame() {
    try {
        const response = await fetch(`${apiBaseUrl}/start`);
        const result = await response.text();
        console.log("Start Game Response:", result);
        document.getElementById("numShields").textContent = result;
        //document.getElementById("game-status").innerText = result;
        //changeTextWithHTML();
    } catch (error) {
        console.error("Error in startGame:", error);
    }
}

async function hit() {
    try {
        const response = await fetch(`${apiBaseUrl}/hit`, { method: "POST" });
        const result = await response.text();
        console.log("Hit Response:", result);
        document.getElementById("game-status").innerText = result;

    } catch (error) {
        console.error("Error in hit:", error);
    }
}

async function stand() {
    try {
        const response = await fetch(`${apiBaseUrl}/stand`, { method: "POST" });
        const result = await response.text();
        console.log("Stand Response:", result);
        document.getElementById("game-status").innerText = result;
    } catch (error) {
        console.error("Error in stand:", error);
    }
}

function changeTextWithHTML() {
            counter++; // Increment the counter
            numShields.textContent = "Number of Shields: " + counter;

//                let labelElement = document
//                    .getElementById("numShields");
//                labelElement.innerHTML =
//                    "<em>New Text</em> using <strong>innerHTML</strong>";
            }
