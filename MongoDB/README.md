# Come creare il container

```bash
# creare un volume docker con il nome
docker volume create Laboratorio3
# per fare il build assicurarsi di essere nella cartella con il Dockerfile
# ai/lab2 è il nome dell'immagine
docker pull mongo
# controllare che il container esista listando le images
docker images
# esegue l'immagine dando nome postgis all'istanza in esecuzione
# -d modalità detached
docker run --name mongodb -v Laboratorio3:/data/db -p 27017:27017 -d ai/lab3
# controllare che sia in esecuzione e con la porta 5432
docker ps


# eseguire bash in modalità interattiva dentro il container
docker exec -it postgis bash
# aprire la shell interattiva di mongo
mongo
```
