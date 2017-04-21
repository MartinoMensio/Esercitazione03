# Esercitazione03
Esercitazione 03 del corso di Applicazioni Internet

## Contenuto

- PostGisDBCreator: app java standalone per popolare la versione geografica della tabella a partire dalla tabella BusStop (lat, long)
- MongoDB: i file per creare il container di docker con mongoDB
- MinPathCalc: app java standalone per calcolare le distanze fra le varie fermate (leggendo GeoDB) e memorizzarle in MongoDB
- PathFinder: app web estensione della precedente MapViewer che interroga postgis e mongoDB per restituire i percorsi più brevi
