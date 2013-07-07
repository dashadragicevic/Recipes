Recipes
=======
# 1. Opis problema

Predmet ovog rada je izrada aplikacije koja vrši ekstrakciju struktuiranih podataka o receptima i čuva ih u RDF bazu.
Recepti se nalaze na web stranici za pretragu recepata [Food.com](http://food.com). Svaki recept sa ovog web sajta opisan je pomoću 
meta-podataka koji su umetnuti u sam HTML stranice, a sami meta-podaci su definisani pomoću [Microdata](http://en.wikipedia.org/wiki/Microdata_(HTML)) standarda. Ekstrakovane podatke je zatim potrebno sačuvati u RDF 
repozitorijumu i omogućiti da im se pristupa putem REST servisa i SPARQL Endpoint-a.

Dakle, osnovni koraci u izradi ove aplikacije bili su:

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


# 4. Tehnička realizacija
