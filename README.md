# URL lyhentäjä aplikaatio
Aplikaation on toteutettu mikropalvelu arkkitehtuurina.
Palvelut ovat:
- [Frontend](frontend) joka vastaa käyttöliittymästä.
- [URL Shortener](/url_shortener/) joka luo ja tallentaa lyhennetyt URL:it.
- [URL Redirect](/url_redirect/) joka ohjaa lyhennetyn URL:an avulla oikeaan osoitteeseen.

## Aplikaation käynnistys

Esivaatimukset git, docker ja docker compose.
Suorittaminen tapahtuu seuraavalla komennoilla.

```
git clone https://github.com/Robettaja/Microservices-app
cd Microservices-app
docker compose up -d --build
```


