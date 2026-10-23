const listContainer = document.querySelector(".list-container");

const getShortenedUrls = async () => {
    try {
        const response = await fetch("/api/url");
        const json = await response.json();

        json.forEach(item => {
            listContainer.innerHTML += `<p>${item.ShortId}</p>`
        });
    } catch(err){
        listContainer.innerHTML += "<p style='color:red;'>Error fetching list</p>";
        console.log(err);
    }
}

getShortenedUrls();