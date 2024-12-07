const apiBaseUrl = "http://localhost:8080";

async function startGame() {
    try {
        const response = await fetch(`${apiBaseUrl}/start`);
        const result = await response.text();
        console.log("Start Game Response:", result);
        document.getElementById("game-status").innerText = result;
    } catch (error) {
        console.error("Error in startGame:", error);
    }
}

async function pullEventCard() {
    try {
            const response = await fetch(`${apiBaseUrl}/drawEventCard`, { method: 'POST' });
            const result = await response.text();
            console.log("Event draw Response:", result);
            document.getElementById("game-status").innerText = result;
        } catch (error) {
            console.error("Error in draw:", error);
        }
}

async function sponsorQuest() {
    try {
            const response = await fetch(`${apiBaseUrl}/sponsor`, { method: 'POST' });
            const result = await response.text();
            console.log("Sponsor yes Response:", result);
            document.getElementById("game-status").innerText = result;
        } catch (error) {
            console.error("Error in sponsor:", error);
        }
}

async function declineSponsor() {
    try {
            const response = await fetch(`${apiBaseUrl}/dontSponsor`, { method: 'POST' });
            const result = await response.text();
            console.log("Sponsor decline Response:", result);
            document.getElementById("game-status").innerText = result;
        } catch (error) {
            console.error("Error in decline sponsor:", error);
        }
}

//async function hit() {
//    try {
//        const response = await fetch(`${apiBaseUrl}/hit`, { method: "POST" });
//        const result = await response.text();
//        console.log("Hit Response:", result);
//        document.getElementById("game-status").innerText = result;
//
//    } catch (error) {
//        console.error("Error in hit:", error);
//    }
//}
//
//async function stand() {
//    try {
//        const response = await fetch(`${apiBaseUrl}/stand`, { method: "POST" });
//        const result = await response.text();
//        console.log("Stand Response:", result);
//        document.getElementById("game-status").innerText = result;
//    } catch (error) {
//        console.error("Error in stand:", error);
//    }
//}
