const shortUrlInput = document.querySelector("#short-url-input");
const shortenUrlBtn = document.querySelector("#shorten-url-button");
const shortenerContainer = document.querySelector(".shortener-container");

const shortenUrl = async () => {
    const url = shortUrlInput.value.trim();
    if(url === "") return;
    try {
        const response = await fetch("/api/url", {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify({url})
        });

        const json = response.json(); 
        shortenerContainer.innerHTML += 
            `<div class="shortener-result">
                <h2>Your shortened URL</h2>
                <p>${json.shortId}</p>
            </div>`;
    } catch(err){
        shortenerContainer.innerHTML += `<p style='color:red;'>Fetch failed</p>`;
        console.log(err);
    }
}

shortenUrlBtn.addEventListener("click", shortenUrl);