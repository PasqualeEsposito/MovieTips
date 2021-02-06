DROP DATABASE movietipsdb;


CREATE DATABASE movietipsdb;


USE movietipsdb;


CREATE TABLE utente
(
    username     varchar(35)  NOT NULL,
    mail         varchar(255) NOT NULL,
    password     varchar(255) NOT NULL,
    nome         varchar(35)  NOT NULL,
    cognome      varchar(35)  NOT NULL,
    genere       varchar(35)  NOT NULL DEFAULT 'Preferisco non specificarlo',
    data_nascita date         NOT NULL,
    ruolo        char(6)      NOT NULL DEFAULT '010000',
    PRIMARY KEY (username),
    UNIQUE KEY email (mail),
    UNIQUE KEY username_UNIQUE (username)
);


CREATE TABLE film
(
    id_film       int          NOT NULL AUTO_INCREMENT,
    titolo        varchar(255) NOT NULL,
    genere        varchar(255) NOT NULL,
    anno          int          NOT NULL,
    regia         varchar(255) NOT NULL,
    attori        text,
    paese         varchar(255) NOT NULL,
    durata        int          NOT NULL,
    distribuzione varchar(255) NOT NULL,
    sceneggiatura varchar(255) NOT NULL,
    fotografia    varchar(255) NOT NULL,
    musiche       varchar(255) NOT NULL,
    produzione    varchar(255) NOT NULL,
    trama         text         NOT NULL,
    PRIMARY KEY (id_film),
    UNIQUE KEY id_film_UNIQUE (id_film)
);


CREATE TABLE recensione
(
    id_recensione   int         NOT NULL AUTO_INCREMENT,
    valutazione     int         NOT NULL,
    testo           text,
    segnalazione    tinyint     NOT NULL DEFAULT '0',
    id_film         int         NOT NULL,
    username_utente varchar(35) NOT NULL,
    PRIMARY KEY (id_recensione),
    UNIQUE KEY id_recensione_UNIQUE (id_recensione),
    KEY id_film (id_film),
    KEY username_utente (username_utente),
    CONSTRAINT recensione_ibfk_1 FOREIGN KEY (id_film) REFERENCES film (id_film) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT recensione_ibfk_2 FOREIGN KEY (username_utente) REFERENCES utente (username) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO movietipsdb.utente
(username,
 mail,
 password,
 nome,
 cognome,
 genere,
 data_nascita,
 ruolo)
VALUES ('francesca_mauro', 'francesca.mauro@unisa.it', SHA1('Francesca1!'), 'Francesca', 'Mauro', 'Donna',
        '1999-04-13', '100000'),
       ('roberta_esposito', 'roberta.esposito@unisa.it', SHA1('Roberta1!'), 'Roberta', 'Esposito', 'Donna',
        '2000-03-30', '010000'),
       ('fabrizio_ceriello', 'fabrizio.ceriello@unisa.it', SHA1('Fabrizio1!'), 'Fabrizio', 'Ceriello', 'Uomo',
        '1999-12-30',
        '001000'),
       ('marco_bellamico', 'marco.bellamico@unisa.it', SHA1('Marcoo1!'), 'Marco', 'Bellamico', 'Uomo', '1990-03-01',
        '000001'),
       ('luca_ciao', 'luca.ciao@unisa.it', SHA1('Lucaaa1!'), 'Luca', 'Ciao', 'Uomo', '1991-03-02', '001001');


INSERT INTO movietipsdb.film
(titolo,
 genere,
 anno,
 regia,
 paese,
 durata,
 distribuzione,
 sceneggiatura,
 fotografia,
 musiche,
 produzione,
 trama)
VALUES ('La città incantata', 'Animazione, Avventura, Fantasy', 2001, 'Hayao Miyazaki', 'Giappone', 122,
        'Lucky Red', 'Hayao Miyazaki', 'Atsushi Okui', 'Joe Hisaishi',
        'Studio Ghibli, Nippon Television Network (NTV), DENTSU Music And Entertainment',
        'La città incantata è un film d''animazione del 2001 scritto e diretto da Hayao Miyazaki. La famiglia di Chihiro, una bambina di 10 anni, è viaggio verso la nuova città dove abiteranno; durante il percorso, il papà sbaglia strada e si trova all''imbocco di un tunnel che sbuca su una vallata su alcune case. Incuriositi, il papà e la mamma si addentrano alla scoperta del posto in cui sono capitati, contro il parere di Chihiro, che ha un brutto presentimento. Superando un fiume il cui letto è in secca, arrivano in una città piena di ristoranti e si siedono a mangiare, mentre la bambina incontra un ragazzo, Haku, che le ordina di andarsene subito. Spaventata, Chihiro torna dai genitori ma scopre che si sono trasformati in maiali, e non riesce a fuggire perchè il fiume è in piena. Si rende conto che sta diventando invisibile, ma Haku decide di aiutarla e la fa assumere con il nome di Sen da Yubaba, la potente strega che controlla tutta la città, che usa questo stratagemma (privare del proprio nome) per impedire alle persone di scappare. Lavorando nella città incantata, Chihiro incontra creature fantastiche e magiche, e proprio lei che è una ragazzina capricciosa e viziata, imparerà il valore dell''amore, dell''amicizia e della solidarietà, affrontando come un''adulta le difficoltà e le scelte che le si presenteranno, cercando di tornare a casa senza dimenticare chi è.');

INSERT INTO movietipsdb.film
(titolo,
 genere,
 anno,
 regia,
 attori,
 paese,
 durata,
 distribuzione,
 sceneggiatura,
 fotografia,
 musiche,
 produzione,
 trama)
VALUES ('Ratatouille', 'Animazione', 2007, 'Brad Bird', 'Patton Oswalt, Lou Romano, Brad Garrett, Ian Holm', 'USA', 110,
        'Buena Vista International Italia', 'Brad Bird', 'Robert Anderson (III), Sharon Calahan', 'Michael Giacchino',
        'Pixar Animation Studios, Walt Disney Pictures',
        'Ratatouille, è un film d''animazione Pixar del 2007 diretto da Brad Bird e Jan Pinkava. Francia, 1970. Rémy è un piccolo topo delle campagne parigine dotato di un olfatto straordinario e un gusto raffinato, che ha un insolito sogno per essere un roditore: diventare un vero cuoco e cucinare in un ristorante rinomato. Nonostante la sua famiglia lo inviti ad accontentarsi dello stile di vita della colonia di topi, Remy non vuole continuare a rovistare nella spazzatura ma vuole sperimentare nuovi sapori. Le circostanze faranno sì che il topo si ritrovi da solo a Parigi nel ristorante che prende il nome dal celeberrimo Auguste Gusteau, il suo chef prediletto il cui motto ''chiunque può cucinare'' ha ispirato Rémy per tutta la vita. Il topolino capisce ben presto di non essere il benvenuto in un cucina e nel momento in cui i suoi sogni sembrano sul punto di andare in fumo, Rémy fa amicizia con Alfredo Linguini, il maldestro e introverso sguattero che lavora nel ristorante stellato. Linguini infatti scopre l''incredibile talento del topo per la cucina dopo averlo osservato nell''intento di modificare una zuppa con diversi ingredienti. Dopo che la zuppa di Rémy riceve una recensione positiva da una critica gastronomica, lui e Linguini decideranno di formare la più improbabile delle coppie dal momento. Tutti crederanno che l''autore della zuppa sia stato proprio l''addetto alle pulizie. Sullo sfondo di un''affascinante Parigi, i due amici si ritroveranno a vivere un''incredibile avventura fatta di svolte comiche, strani sospetti e sviluppi emotivi, per riportare il ristorante di Gusteau al suo antico splendore.'),
       ('Il Cavaliere Oscuro', 'Azione, Fantasy', 2008, 'Christopher Nolan',
        'Christian Bale, Michael Caine, Heath Ledger, Maggie Gyllenhaal, Gary Oldman, Morgan Freeman, Eric Roberts, Aaron Eckhart, Anthony Michael Hall, Monique Gabriela Curnen, Michael Jai White, Nathan Gamble, Joshua Harto, Chin Han, Melinda McGraw, Nestor Carbonell, Cillian Murphy',
        'USA', 152, 'Warner Bros. Pictures Italia', 'Christopher Nolan, Jonathan Nolan', 'Wally Pfister',
        'James Newton Howard, Hans Zimmer', 'Warner Bros. Pictures, Legendary Pictures, DC Comics, Syncopy',
        'Il Cavaliere Oscuro, il film diretto da Christopher Nolan, con Christian Bale, e basato sul fumetto della DC Comics, Batman, è il secondo capitolo della trilogia diretta da Nolan, e narra le vicende del celebre supereroe alle prese con il cattivissimo e folle Joker (Heath Ledger). Ad aiutare Batman ci sono il commissario Gordon (Gary Oldman) e il procuratore distrettuale Harvey Dent (Aaron Eckhart), attuale fidanzato dell''amica di infanzia di Bruce Wayne, Rachel (Maggie Gyllenhaal). Dopo un periodo di tranquillità per la città di Gotham, in cui i cattivi sono tenuti a bada da Batman, entra in scena Joker, l''inquietante pagliaccio dall''identità segreta, che mette a segno una rapina ad una banca collusa con la mafia. Si presenta ad una riunione dei boss mafiosi e propone un''alleanza per eliminare Batman, ma questi si convincono solo dopo che Batman e Dent riescono ad incastrarli. I malavitosi aderiscono quindi al piano di Joker, che prevede che Batman riveli la sua identità, pena la morte di una persona ogni giorno; inizia così ad eliminare personaggi di spicco quali un giudice, un commissario e prova con lo stesso Dent, che però viene salvato da Wayne. Quest''ultimo, visto il precipitare degli eventi, si decide ad uscire allo scoperto, ma viene preceduto da Dent che si costituisce come Batman. Joker cerca di attaccare Dent, ma si scontrerà con il vero Batman. Nel frattempo la mafia rapisce Dent e Rachel per vendicarsi, e l''uomo pipistrello, mettendo sotto torchio Joker, viene a sapere che i due si trovano in due magazzini diversi e pronti a saltare in aria; dall''esplosione, Dent sopravvive con un''ustione alla metà del viso, mentre la ragazza muore; distrutto dalla perdita della fidanzata e dai fallimenti per difendere la città dalla criminalità, Dent abbandona il suo lato umano e assume l''identità del cattivo Due Facce, che si contraddistinguerà per affidare le sue decisioni (e le sorti delle sue vittime) al lancio di una moneta. Nel frattempo Joker continua a seminare panico, divertendosi a costringere Gotham a decisioni assurde e disperate, mentre Due Facce mette in atto la sua personale vendetta per vendicare la morte di Rachel. La fine della città sembra segnata, finché un''insperata giustizia e solidarietà trionfano, grazie anche all''aiuto di Batman, che per salvare Gotham sacrificherà la sua reputazione di eroe buono. Il Cavaliere Oscuro è il sequel di Batman Begins (2005) e verrà seguito dalla terza e ultima parte, Il Cavaliere Oscuro - Il Ritorno (2012).');

INSERT INTO movietipsdb.film
(titolo,
 genere,
 anno,
 regia,
 paese,
 durata,
 distribuzione,
 sceneggiatura,
 fotografia,
 musiche,
 produzione,
 trama)
VALUES ('WALL•E', 'Animazione', 2008, 'Andrew Stanton', 'USA', '98', 'Walt Disney Pictures Italia', 'Andrew Stanton',
        'Jeremy Lasky', 'Thomas Newman', 'Walt Disney Pictures, Pixar Animation Studios',
        'WALL•E è un film del 2008 diretto da Andrew Stanton. In un distopico 2015, la Terra è diventata inospitale a causa dei rifiuti prodotti dagli umani, che ricoprono interamente il pianeta. L’umanità trova rifugio su una flotta di navi spaziali, mentre attende che la BnL Corporation ripulisca il pianeta entro il 2110, grazie ai moderni e tecnologici robot spazzini chiamati WALL•E. Purtroppo, tutti i robot smettono ben presto di funzionare. Tutti, ad eccezione di uno che trascorre i seguenti 700 anni in completo isolamento, continuando la sua opera di pulizia e imballaggio dei rifiuti. Grazie alla visione compulsiva del film Hello, Dolly!, l’unico WALL•E sopravvissuto ha sviluppato dei sentimenti umani e ha trascorso ben sette secoli desideroso di incontrare la sua anima gemella. La solitudine del robot è improvvisamente interrotta dall’arrivo di EVE, robot di livello tecnologico superiore, il cui compito è constatare se vi sia vita sulla Terra. Quando il robot le mostra una piantina, EVE completa la sua missione e si disattiva. WALL•E, che inizia a nutrire un forte sentimento per lei, non ha intenzione di lasciarla andare e la segue a bordo della base spaziale Axiom. Qui, a causa dell’estremo lusso e dell’inattività secolare, la razza umana è diventata obesa e si muove a bordo di assurde poltrone volanti, comunicando tramite ologrammi. A causa di una serie di malintesi, la sicurezza considera i due robot come una minaccia e EVE perde misteriosamente la piantina. Tra rocambolesche fughe e divertenti inseguimenti, WALL•E si innamorerà completamente di EVE che, per la prima volta, potrà costatare l’intensità dei sentimenti. Quando il capitano della base spaziale dà ordine di iniziare l’esodo sulla Terra, il robot pilota AUTO, convinto che la decisione rappresenti un pericolo per l’intero equipaggio, si oppone fermamente e WALL•E dovrà dimostrare tutto il suo eroismo.');

INSERT INTO movietipsdb.film
(titolo,
 genere,
 anno,
 regia,
 attori,
 paese,
 durata,
 distribuzione,
 sceneggiatura,
 fotografia,
 musiche,
 produzione,
 trama)
VALUES ('Parasite', 'Drammatico', 2019, 'Bong Joon Ho',
        'Hye-jin Jang, Kang-ho Song, Lee Sun-kyun, Cho Yeo-Jeong, Choi Woo-sik, Park So-dam', 'Corea del Sud', 132,
        'Academy Two', 'Bong Joon Ho, Jin Won Han', 'Hong Kyung-pyo', 'Jaeil Jung',
        'Barunson E&A, CJ E&M Film Financing & Investment Entertainment & Comics, CJ Entertainment, Frontier Works Comic',
        'Parasite, film diretto da Joon-ho Bong, è un dramma che racconta la storia della famiglia Kim, formata dal padre Ki-taek (Kang-ho Song), un uomo privo di stimoli, una madre, Chung-sook (Hye-jin Jang), senza alcuna ambizione e due figli, la 25enne Ki-jung (So-dam Park) e il minore, Ki-woo (Woo-sik Choi). Vivono in uno squallido appartamento, sito nel seminterrato di un palazzo, e sono molto legati tra loro, ma senza un soldo in tasca né un lavoro né una speranza per un futuro roseo. A Ki-woo viene la perversa idea di falsificare il suo diploma e la sua identità per reinventarsi come tutor e impartire lezioni a Yeon-kyuo (Yeo-jeong Jo), la figlia adolescente dei Park. Quest''ultimi sono una ricca famiglia, che, al contrario dei Kim, vivono in una grande villa, grazie ai guadagni del patriarca, dirigente di un''azienda informatica. Ki-woo insegna principalmente inglese alla ragazza a un ottimo prezzo, cosa che genera entusiasmo e speranza nella suoi parenti. Il ragazzo, notando come alla figlia minore dei Park piaccia disegnare, ha la subdola idea di inventare che sua sorella Ki-jung è un''insegnante d''arte, permettendo anche a lei di infiltrarsi nella loro vita. Le due famiglie non sanno, però, che questo incontro è solo l''inizio di una storia strana, che porterà i Kim a introdursi sempre più nella routine dei Park, come un parassita fa con un organismo estraneo.'),
       ('The Midnight Sky', 'Fantascienza, Drammatico', 2020, 'George Clooney',
        'George Clooney, Felicity Jones, David Oyelowo, Sophie Rundle, Caoilinn Springall, Kyle Chandler, Ethan Peck, Miriam Shor, Demian Bichir, Tim Russ, Tiffany Boone, Edan Hayhurst, Atli Óskar Fjalarsson',
        'USA', 122, 'Netflix', 'Mark L. Smith', 'Martin Ruhe', 'Alexandre Desplat',
        'Anonymous Content, Netflix, Smokehouse Pictures, Syndicate Entertainment, Truenorth Productions',
        'The Midnight Sky, film diretto George Clooney, racconta la storia di Augustine (Clooney), uno scienziato solitario che vive in una base nell''Artico in un pianeta ormai disabitato. L''uomo si rende conto che un gruppo di astronauti, tra cui la giovane Sully (Felicity Jones), stanno per andare incontro a una catastrofe globale e tenta di dissuaderli dal tornare a casa.\nIl team di astronauti aveva in precedenza scoperto l''esistenza di un satellite di Giove, che potrebbe essere abitato dagli esseri umani, mentre sulla Terra non c''è più alcun segno di vita...');


INSERT INTO movietipsdb.recensione
(valutazione,
 testo,
 id_film,
 username_utente)
VALUES (4, 'Film molto bello', 1, 'fabrizio_ceriello'),
       (2, 'Non mi è piaciuto', 5, 'fabrizio_ceriello'),
       (5, 'Film stupendo!', 3, 'luca_ciao');