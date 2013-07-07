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
- kreiranje RDF baze i čuvanje ekstrakovanih podataka o receptu u skladu sa [RDF vokabularom](http://schema.org/Recipe),
- omogućavanje pristupa podacima u bazi pomoću odgovarajućih REST servisa i SPARQL Endpoint-a.

# 2. Domenski model




# 3. Rešenje


# 4. Tehnička realizacija
