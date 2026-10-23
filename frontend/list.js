const listContainer = document.querySelector(".list-container");

const getShortenedUrls = async () => {
    try {
        const response = await fetch("/url_shortener/url");
        const json = await response.json();

        json.forEach(item => {
            listContainer.innerHTML += `<p>${item.shortId}</p>`
        });
    } catch(err){
        listContainer.innerHTML += "<p style='color:red;'>Error fetching list</p>";
        console.log(err);
    }
}

getShortenedUrls();