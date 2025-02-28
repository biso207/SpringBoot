Progetto assegnazione ID creato da Diego Ferventi e Luca Bisognin.
PRIMA DI RUNNARE:
Il progetto necessita che mysql si attivo nel indirizzo di loopback alla porta 3306 (127.0.0.1:3306)
Eventuali configurazioni alternative possono tranquillamente essere modificate in src/main/resources/application.properties sotto la sezione <CONNESSIONE DATABASE>.

L'App necessita solo che sia già creato uno schema (di default è nodi), esso può essere modificato sempre in connessione database
modificando il campo spring.datasource.url=jdbc:mysql://localhost:3306/nodi in spring.datasource.url=jdbc:mysql://localhost:3306/<nome_schema>
Per generare lo schema inserire la query CREATE SCHEMA `nodi` ; nel caso di default e CREATE SCHEMA `<nome_schema>` ; nel caso personalizzato.

Tutto il resto (Quindi la tabella identificator con le suo colonne) viene generato automaticamente tramite la query presente in src/main/resources/data.sql .

APP:
L'app mette ha disposizione principalmente 2 servizi:
 - La possibilità di ottenere un id univoco (idNodo) che va da 0 a N per un determinato periodo di tempo
 - La possibilità di rinnovare il tuo id univoco

Sia il periodo di tempo sia N sono modificabili in src/main/resources/application.properties sotto le seguenti sezioni:
- application.service.max-id, è il numero massimo di idNodo che uno si può ottenere;
- application.service.max-inactivity-time, il tempo che si ha per rinnovare l'id univoco dalla sua creazione o dal suo ultimo rinnovo;
- application.scheduled.control-time, ogni quanti millisecondi l'App controllerà i ronnovi dell'id univoco

Inoltre per accedere alle API dell'app è necessario usare un api key anche questa modificable in src/main/resources/application.properties
nel campo application.security.api-key.
