# Url shortener palvelu 
Palvelu on toteudettu ASP.net 10.0 C# ohjelmointi kielellä. Palvelu käyttää taustallaan sqlite tietokantaa.

## Suorittaminen

Vaatimukset Dotnet SDK <br/>
Asentaa paketit.
```bash
dotnet Restore 
```
Käynnistää sovelluksen Release tilassa.
```bash
dotnet run -c Release
```


## Docker imagen toteudettus

Image on jaettu kahteen osaa builderiin ja lopputulokseen. Builderissa rakennetaan applikaation binaari tiedostot. Lopputulos kohtassa määritetään tarpeelliset environment muuttujat portitt sekä sqlite kansio. Lopuksi asetetaan .dll tiedosto entrypointiksi.
