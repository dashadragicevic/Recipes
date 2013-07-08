# 1. Opis problema

Predmet ovog rada je izrada aplikacije koja vrši ekstrakciju struktuiranih podataka o receptima i čuva ih u RDF bazu.
Recepti se nalaze na web stranici za pretragu recepata [Food.com](http://food.com). Svaki recept sa ovog web sajta opisan je pomoću 
meta-podataka koji su umetnuti u sam HTML stranice, a sami meta-podaci su definisani pomoću [Microdata](http://en.wikipedia.org/wiki/Microdata_(HTML)) standarda. Ekstrakovane podatke je zatim potrebno sačuvati u RDF 
repozitorijumu i omogućiti da im se pristupa putem REST servisa i SPARQL Endpoint-a.

Dakle, osnovni zahtevi u izradi ove aplikacije bili su:

- pravljenje web crawlera koji prikuplja stranice sa sajta Food.com,
- analiziranje svake prikupljene web stranice kako bi se ekstrakovali umetnuti Microdata podaci o receptu,
- kreiranje RDF baze i čuvanje ekstrakovanih podataka o receptu u skladu sa RDF vokabularom,
- omogućavanje pristupa podacima u bazi pomoću odgovarajućih REST servisa i SPARQL Endpoint-a.

# 2. Domenski model

U skladu sa RDF vokabularom [Recipe](http://schema.org/Recipe) kreiran je domenski model, predstavljen sledećim dijagramom:

![Slika 1 - Domenski model](docs/images/model.jpg)
Slika 1 - Domenski model

Klasa *Recipe* je klasa koja sadrži osnovne podatke o receptu. Ona vodi računa o adresi recepta, nazivu, autoru, datumu objavljivanja,
kategorijama kojima taj recept pripada, slikama jela, vremenu kuvanja, vremenu pripreme, ukupnom vremenu,opisu recepta,
neophodnim sastojcima i njihovim količinama, detaljnim uputstvima o spremanju, i količini porcije koja se dobija receptom.

Klasa *NutritionInformation* sadrži podatke o nutritivnim informacijama datog recepta, konkretno o količini ugljenih hidrata,
zasićenih masti, holesterola, natrijuma, vlakana, masti, šećera, proteina i kalorija. Količina kalorija iskazuje se
pomoću klase *Energy*, dok se sve ostale nutritivne informacije izražavaju pomoću klase *Mass*.

Klasa *Review* predstavlja komentar korisnika na određeni recept. Tu se pamte podaci o temi komentara, autoru, datumu
objavljivanja i opisu, odnosno samom tekstu komentara. Prilikom davanja kometara, korisnik daje i ocenu samog recepta,
koja se predstavlja klasom *Rating*. Ona sadrži podake o najmanjoj i najvećoj mogućoj oceni, i o oceni koju je dao autor
komentara.

Klasa *AggregateRating* je klasa koja sadrži podatke o trenutnoj oceni recepta, tako što vodi računa o broju komentara i 
prosečnoj oceni svih komentara.

# 3. Rešenje

Kreirana je aplikacija koja prikuplja meta-podatke o receptima predstavljenim preko Microdata standarda sa web sajta
[Food.com](http://food.com), na osnovu tih podataka kreira odgovarajuće objekte domenskog modela, a zatim te objekte
čuva u lokalnu RDF bazu. Aplikacija dalje omogućava pristup sačuvanim podacima pomoću RESTful servisa.

Aplikacija omogućava tri REST servisa:

* **GET /api/recipes** - servis koji vraća podatke o receptima. Opcioni parametri su:
  * *name* – reč u nazivu recepta
  * *author* – reč u nazivu autora recepta
  * *recipeCategory* – kategorije recepta
  * *hasImage* – samo recepti sa slikom
  * *totalTime* – ukupno vreme spremanja (priprema i kuvanje)
  * *prepTime* – ukupno vreme pripreme
  * *cookTime* – ukupno vreme kuvanja
  * *ingredients* – glavni sastojci u jelu
  * *minAggregateRatingValue* – minimalna ocena koju bi recept trebalo da ima
  * *minCarbs* – minimalna količina ugljenih hidrata (g)
  * *maxCarbs* – maksimalna količina ugljnih hidrata (g)
  * *minSatFat* – minimalna količina zasićenih masti (g)
  * *maxSatFat* – maksimalna količina zasićenih masti (g)
  * *minCholesterol* – minimalna količina holesterola (mg)
  * *maxCholesterol* – maksimalna količina holesterola (mg)
  * *minSodium* – minimalna količina natrijuma (mg)
  * *maxSodium* – maksimalna količina natrijuma (mg)
  * *minFat* – minimalna količina masti (g)
  * *maxFat* – maksimalna količina masti (g)
  * *minFiber* – minimalna količina vlakana (g)
  * *maxFiber* – maksimalna količina vlakana (g)
  * *minSugar* – minimalna količina šećera (g)
  * *maxSugar* – maksimalna količina šećera (g)
  * *minProtein* – minimalna količina proteina (g)
  * *maxProtein* – maksimalna količina protein (g)
  * *minCalories* – minimalna količina kalorija (cal)
  * *maxCalories* – maksimalna količina kalorija (cal)
  
  Primer poziva ovog servisa:

  > GET/recipes?name=chili&recipeCategory=american&hasImage=yes&ingredients=jalapeno,honey
  
* **GET /api/recipes/id** - servis koji vraća podatke o jednom receptu sa zadatim id-jem.
  
  Primer poziva ovog servisa:

  > GET/recipes/0517b1e2-e3b3-48b0-8f2f-23af6dc9f646
  
* **GET /api/recipes/id/reviews** - servis koji vraća komentare recepta sa zadatim id-jem.
  
  Primer poziva ovog servisa:

  > GET/recipes/0517b1e2-e3b3-48b0-8f2f-23af6dc9f646/reviews

# 4. Tehnička realizacija

Aplikacija je rađena u Javi.

Za analiziranje web stranica i prikupljanje podataka sa njih korišćena je Jsoup biblioteka. U pitanju je biblioteka koja
omogućava parsiranje HTML stranica pomoću pogodnog API-a za ekstrakciju i manipulaciju podacima.
