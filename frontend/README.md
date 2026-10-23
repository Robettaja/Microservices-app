# URL lyhentäjä websivu

## Toteutus
- Toteutettu perus HTML/CSS/JS sivuna. 
- Luo pyynnöt url_shortener palvelimeen JavaScriptin fetchillä.
- Pyörii nginx web-palvelimella

## Toiminta
- Sivulla on kaksi näkymää, index listaa tallennetut lyhennetyt URL linkit ja Shortener sivu mahdollistaa URL:n lyhentämisen.
- Index sivu lähettää pyynnön palvelimelle, joka vastaa JSON datana tietokantaan tallennetut URL:it.
- URL lyhennys lähettää käyttäjän lisäämän URL linkin palvelimelle ja saa vastauksena lyhennetyn version.
- nginx konfiguraatio tiedosto:
    - Pyynnöt on määritelty käyttämään service nimeä
    - Lyhennetyt linkit toimivat url_redirect palvelun kautta.

## Dockerfile
- Käyttää nginx alpine kuvaa
- Kopioi valmiiksi luodun nginx konfiguraatio tiedoston
- Kopioi websivun tiedostot nginxin html kansioon 