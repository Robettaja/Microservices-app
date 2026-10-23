# Url shortener palvelu 
Palvelu on toteudettu ASP.net 10.0 C# ohjelmointi kielellä. Palvelu käyttää taustallaan sqlite tietokantaa.

## Suorittaminen

Vaatimukset Dotnet SDK <br/>
Asentaa tarvittavat paketit.
```bash
dotnet Restore 
```
Käynnistää sovelluksen release tilassa.
```bash
dotnet run -c Release
```


## Docker imagen toteutus 

Image on jaettu kahteen osaa builderiin ja lopputulokseen. Builderissa rakennetaan applikaation binaari tiedostot. Lopputulos kohtassa määritetään tarpeelliset environment muuttujat portitt sekä sqlite kansio. Lopuksi asetetaan .dll tiedosto entrypointiksi.
