# URL lyhentäjä aplikaatio
Aplikaation on toteutettu mikropalvelu arkkitehtuurina.
Palvelut ovat:
- [Frontend](frontend) joka vastaa käyttöliittymästä.
- [URL_Shortener](/url_shortener/) joka luo ja tallentaa lyhennetyt URL:it.
- [URL_Redirect](/url_shortener/) joka ohjaa lyhennetyn URL:an avulla oikeaan osoitteeseen.

## Aplikaation käynnistys

Esivaatimukset docker ja docker compose.
Suorittaminen tapahtuu seuraavalla komennolla.
```
docker compose up -d --build
```


