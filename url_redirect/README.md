# url_redirect
## toiminnallisuus
Root sivu (/) näyttää toiminnassa olevat endpointit (/s/{short-id}, /err):

- /s/{short-id} : yrittää redirectata käyttäjän hakemalla short-id kautta URL osoitteen http://url_shortener:8080/url?shortId={short-id}
  (jos tämä epäonnistuu, ohjataan /err)
- /err : näyttää geneerisen virheen joko URL haun takia, taikka jos haettu short-id eroaa tietokannasta saadun short-id:n kanssa

## dockerfile
Dockerfile rakentaa maven+jdk25 avulla applikaation, jonka jälkeen se käynnistetään käyttämällä jre25.