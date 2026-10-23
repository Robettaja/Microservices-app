# URL lyhentäjä aplikaatio
Aplikaation on toteutettu mikropalvelu arkkitehtuurina. <br/>
Palvelut ovat:
- [Frontend](frontend) joka vastaa käyttöliittymästä.
- [URL Shortener](/url_shortener/) joka luo ja tallentaa lyhennetyt URL:it.
- [URL Redirect](/url_redirect/) joka ohjaa lyhennetyn URL:an avulla oikeaan osoitteeseen.

## Aplikaation käynnistys

Esivaatimukset git, docker ja docker compose.
Suorittaminen tapahtuu seuraavalla komennoilla.

```bash
git clone https://github.com/Robettaja/Microservices-app
cd Microservices-app
docker compose up -d --build
```


## Docker osuus
- Jokainen palvelu yrittää käynnistää itsensä maksimi viisi kertaa ongelma tilanteessa. 
- Palvelut käyttävät portteja 80,7777 ja 8080.
- Frontend ja Url rediret palvelu käynnistyvät Url shortener palvelun jälkeen, koska ne ovat riippuvaisia kyseisestä palvelusta.

