# URL lyhentäjä websivu

## Toteutus
- Toteutettu perus HTML/CSS/JS sivuna käyttäen nginx alpine kuvaa. 
- Yhdistää url_shortener palvelimeen JavaScriptin fetchillä.
- nginx konfiguraatio tiedostossa on määritelty proxy, jonka avulla pyynnöt käyttävät servicen nimeä.

## Toiminta
- Sivulla on kaksi näkymää, index listaa tallennetut lyhennetyt URL linkit ja Shortener sivu mahdollistaa URL:n lyhentämisen.
- Index sivu lähettää pyynnön palvelimelle, joka vastaa JSON datana tietokantaan tallennetut URL:it.
- URL lyhennys lähettää käyttäjän lisäämän URL linkin palvelimelle ja saa vastauksena lyhennetyn version.