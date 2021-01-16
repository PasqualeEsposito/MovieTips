CREATE TABLE Utente
(
    id_utente INT IDENTITY (1, 1) PRIMARY KEY,
    username  VARCHAR(255) NOT NULL,
    nome      VARCHAR(255) NOT NULL,
    cognome   VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    genere    VARCHAR(255) NOT NULL,
    ddn       DATE         NOT NULL,
    ruolo     INT          NOT NULL
);

CREATE TABLE Film
(
    id_film       INT IDENTITY (1, 1) PRIMARY KEY,
    titolo        VARCHAR(255) NOT NULL,
    produzione    VARCHAR(255) NOT NULL,
    musiche       VARCHAR(255) NOT NULL,
    fotografia    VARCHAR(255) NOT NULL,
    sceneggiatura VARCHAR(255) NOT NULL,
    distribuzione VARCHAR(255) NOT NULL,
    durata        INT          NOT NULL,
    paese         VARCHAR(255) NOT NULL,
    attori        VARCHAR(255) NOT NULL,
    regia         VARCHAR(255) NOT NULL,
    genere        VARCHAR(255) NOT NULL,
    trama         VARCHAR(255) NOT NULL
);

CREATE TABLE Film_seguiti
(
    id_utente INT NOT NULL,
    id_film   INT NOT NULL,
    FOREIGN KEY (id_utente) REFERENCES Utente (id_utente)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_film) REFERENCES Film (id_film)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Recensione
(
    id_recensione       INT IDENTITY (1, 1) PRIMARY KEY,
    valutazione         INT  NOT NULL,
    testo               text NOT NULL,
    numero_segnalazioni INT  NOT NULL,
    id_utente           INT  NOT NULL,
    id_film             INT  NOT NULL,
    FOREIGN KEY (id_utente) REFERENCES Utente (id_utente)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_film) REFERENCES Film (id_film)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Notizia
(
    id_notizia INT IDENTITY (1, 1) PRIMARY KEY,
    titolo     VARCHAR(255) NOT NULL,
    testo      VARCHAR(255) NOT NULL,
    fonte      VARCHAR(255) NOT NULL,
    id_film    INT          NOT NULL,
    FOREIGN KEY (id_film) REFERENCES Film (id_film)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

INSERT INTO Film VALUES ("La città incantata", "Studio Ghibli, Nippon Television Network (NTV), DENTSU Music And Entertainment", "Joe Hisaishi", "Atsushi Okui", "Hayao Miyazaki", "Lucky Red", 122, "Giappone", "", "Hayao Miyazaki", "Animazione, Avventura, Fantasy",
"La città incantata è un film d''animazione del 2001 scritto e diretto da Hayao Miyazaki. La famiglia di Chihiro, una bambina di 10 anni, è viaggio verso la nuova città dove abiteranno; durante il percorso, il papà sbaglia strada e si trova all''imbocco di un tunnel che sbuca su una vallata su alcune case. Incuriositi, il papà e la mamma si addentrano alla scoperta del posto in cui sono capitati, contro il parere di Chihiro, che ha un brutto presentimento. Superando un fiume il cui letto è in secca, arrivano in una città piena di ristoranti e si siedono a mangiare, mentre la bambina incontra un ragazzo, Haku, che le ordina di andarsene subito. Spaventata, Chihiro torna dai genitori ma scopre che si sono trasformati in maiali, e non riesce a fuggire perchè il fiume è in piena. Si rende conto che sta diventando invisibile, ma Haku decide di aiutarla e la fa assumere con il nome di Sen da Yubaba, la potente strega che controlla tutta la città, che usa questo stratagemma (privare del proprio nome) per impedire alle persone di scappare. Lavorando nella città incantata, Chihiro incontra creature fantastiche e magiche, e proprio lei che è una ragazzina capricciosa e viziata, imparerà il valore dell''amore, dell''amicizia e della solidarietà, affrontando come un''adulta le difficoltà e le scelte che le si presenteranno, cercando di tornare a casa senza dimenticare chi è.");

INSERT INTO Film VALUES ("Il Pianista", "Studio Canal", "Wojciech Kilar", "Pawel Edelman", "Ronald Harwood", "01 Distribution", 148, "Francia, Polonia, Germania, Gran Bretagna", "Adrien Brody, Thomas Kretschmann, Frank Finlay, Maureen Lipman, Ed Stoppard, Julia Rayner, Jessica Kate Meyer, Emilia Fox, Ruth Platt, Valentine Pelka, Ronan Vibert", "Roman Polanski", "Drammatico, Biografico",
"Il Pianista, il film da Roman Polanski e basato sull''autobiografia del musicista Wladyslaw Szpilman, narra il dramma delle persecuzioni razziali.È il 1° settembre del 1939. La Germania ha appena dichiarato guerra alla Polonia. Władysław Szpilman (Adrien Brody) è un giovane e prodigioso pianista ebreo che sta eseguendo il Notturno di Chopin nella sala di registrazione radiofonica presso la quale lavora, quando una raffica di violente esplosioni distrugge la facciata del palazzo annunciando l''inizio del l''invasione nazista della Polonia: la vita di Władysław e della sua famiglia è improvvisamente sconvolta. Con l''occupazione di Varsavia la libertà individuale e quella collettiva viene definitivamente messa al bando. Iniziano subito le prime vessazioni accompagnate dalle disumane leggi razziali fino ad arrivare all''insensata recinzione del Ghetto, all''interno del quale il giovane viene esiliato insieme ai suoi familiari e a tutti gli ebrei della città. Il talentuoso pianista, che non può far altro che assistere impotente e attonito all''orrore che si sta propagando, tuttavia non si lascia schiacciare dalle atrocità del dominio nazista e affronta con dignità la fame, la miseria, le angherie e le umiliazioni, lottando per tenere unita tutta la famiglia. Anche quando è costretto a separarsi dai suoi cari, a causa della loro deportazione nei campi di sterminio, il musicista non perde la forza e la speranza e seppur rimasto solo nel ghetto, non smette di combattere per la sua sopravvivenza sostenuto dalla sua ancestrale passione per la musica. Il talento musicale di Władysław non rimane soffocato neanche in questo terribile e desolante scenario, arrivando a colpire perfino i cuori più induriti e riportando la luce anche negli abissi più tenebrosi. La sua straordinaria e coinvolgente musica conquisterà clamorosamente un ufficiale tedesco (Thomas Kretschmann), che avrà un ruolo cruciale nella vita precaria e incerta del pianista sino all''arrivo dell''Armata Rossa.");

INSERT INTO Film VALUES ("Ratatouille", "Pixar Animation Studios, Walt Disney Pictures", "Michael Giacchino", "Robert Anderson (III), Sharon Calahan", "Brad Bird", "Buena Vista International Italia", 110, "USA", "Patton Oswalt, Lou Romano, Brad Garrett, Ian Holm", "Brad Bird", "Animazione",
"Ratatouille, è un film d''animazione Pixar del 2007 diretto da Brad Bird e Jan Pinkava. Francia, 1970. Rémy è un piccolo topo delle campagne parigine dotato di un olfatto straordinario e un gusto raffinato, che ha un insolito sogno per essere un roditore: diventare un vero cuoco e cucinare in un ristorante rinomato. Nonostante la sua famiglia lo inviti ad accontentarsi dello stile di vita della colonia di topi, Remy non vuole continuare a rovistare nella spazzatura ma vuole sperimentare nuovi sapori. Le circostanze faranno sì che il topo si ritrovi da solo a Parigi nel ristorante che prende il nome dal celeberrimo Auguste Gusteau, il suo chef prediletto il cui motto "chiunque può cucinare" ha ispirato Rémy per tutta la vita. Il topolino capisce ben presto di non essere il benvenuto in un cucina e nel momento in cui i suoi sogni sembrano sul punto di andare in fumo, Rémy fa amicizia con Alfredo Linguini, il maldestro e introverso sguattero che lavora nel ristorante stellato. Linguini infatti scopre l''incredibile talento del topo per la cucina dopo averlo osservato nell''intento di modificare una zuppa con diversi ingredienti. Dopo che la zuppa di Rémy riceve una recensione positiva da una critica gastronomica, lui e Linguini decideranno di formare la più improbabile delle coppie dal momento. Tutti crederanno che l''autore della zuppa sia stato proprio l''addetto alle pulizie. Sullo sfondo di un''affascinante Parigi, i due amici si ritroveranno a vivere un''incredibile avventura fatta di svolte comiche, strani sospetti e sviluppi emotivi, per riportare il ristorante di Gusteau al suo antico splendore.");

INSERT INTO Film VALUES ("Il Cavaliere Oscuro", "Warner Bros. Pictures, Legendary Pictures, DC Comics, Syncopy", "James Newton Howard, Hans Zimmer", "Wally Pfister", "Christopher Nolan, Jonathan Nolan", "Warner Bros. Pictures Italia", 152, "USA", "Christian Bale, Michael Caine, Heath Ledger, Maggie Gyllenhaal, Gary Oldman, Morgan Freeman, Eric Roberts, Aaron Eckhart, Anthony Michael Hall, Monique Gabriela Curnen, Michael Jai White, Nathan Gamble, Joshua Harto, Chin Han, Melinda McGraw, Nestor Carbonell, Cillian Murphy", "Christopher Nolan", "Azione, Fantasy",
"Il Cavaliere Oscuro, il film diretto da Christopher Nolan, con Christian Bale, e basato sul fumetto della DC Comics, Batman, è il secondo capitolo della trilogia diretta da Nolan, e narra le vicende del celebre supereroe alle prese con il cattivissimo e folle Joker (Heath Ledger). Ad aiutare Batman ci sono il commissario Gordon (Gary Oldman) e il procuratore distrettuale Harvey Dent (Aaron Eckhart), attuale fidanzato dell''amica di infanzia di Bruce Wayne, Rachel (Maggie Gyllenhaal). Dopo un periodo di tranquillità per la città di Gotham, in cui i cattivi sono tenuti a bada da Batman, entra in scena Joker, l''inquietante pagliaccio dall''identità segreta, che mette a segno una rapina ad una banca collusa con la mafia. Si presenta ad una riunione dei boss mafiosi e propone un''alleanza per eliminare Batman, ma questi si convincono solo dopo che Batman e Dent riescono ad incastrarli. I malavitosi aderiscono quindi al piano di Joker, che prevede che Batman riveli la sua identità, pena la morte di una persona ogni giorno; inizia così ad eliminare personaggi di spicco quali un giudice, un commissario e prova con lo stesso Dent, che però viene salvato da Wayne. Quest''ultimo, visto il precipitare degli eventi, si decide ad uscire allo scoperto, ma viene preceduto da Dent che si costituisce come Batman. Joker cerca di attaccare Dent, ma si scontrerà con il vero Batman. Nel frattempo la mafia rapisce Dent e Rachel per vendicarsi, e l''uomo pipistrello, mettendo sotto torchio Joker, viene a sapere che i due si trovano in due magazzini diversi e pronti a saltare in aria; dall''esplosione, Dent sopravvive con un''ustione alla metà del viso, mentre la ragazza muore; distrutto dalla perdita della fidanzata e dai fallimenti per difendere la città dalla criminalità, Dent abbandona il suo lato umano e assume l''identità del cattivo Due Facce, che si contraddistinguerà per affidare le sue decisioni (e le sorti delle sue vittime) al lancio di una moneta. Nel frattempo Joker continua a seminare panico, divertendosi a costringere Gotham a decisioni assurde e disperate, mentre Due Facce mette in atto la sua personale vendetta per vendicare la morte di Rachel. La fine della città sembra segnata, finché un''insperata giustizia e solidarietà trionfano, grazie anche all''aiuto di Batman, che per salvare Gotham sacrificherà la sua reputazione di eroe buono. Il Cavaliere Oscuro è il sequel di Batman Begins (2005) e verrà seguito dalla terza e ultima parte, Il Cavaliere Oscuro - Il Ritorno (2012).");

INSERT INTO Film VALUES ("WALL•E", "Walt Disney Pictures, Pixar Animation Studios", "Thomas Newman", "", "Andrew Stanton", "Walt Disney Pictures Italia", 98, "USA", "", "Andrew Stanton", "Animazione",
"WALL•E è un film del 2008 diretto da Andrew Stanton. In un distopico 2015, la Terra è diventata inospitale a causa dei rifiuti prodotti dagli umani, che ricoprono interamente il pianeta. L’umanità trova rifugio su una flotta di navi spaziali, mentre attende che la BnL Corporation ripulisca il pianeta entro il 2110, grazie ai moderni e tecnologici robot spazzini chiamati WALL•E. Purtroppo, tutti i robot smettono ben presto di funzionare. Tutti, ad eccezione di uno che trascorre i seguenti 700 anni in completo isolamento, continuando la sua opera di pulizia e imballaggio dei rifiuti. Grazie alla visione compulsiva del film Hello, Dolly!, l’unico WALL•E sopravvissuto ha sviluppato dei sentimenti umani e ha trascorso ben sette secoli desideroso di incontrare la sua anima gemella. La solitudine del robot è improvvisamente interrotta dall’arrivo di EVE, robot di livello tecnologico superiore, il cui compito è constatare se vi sia vita sulla Terra. Quando il robot le mostra una piantina, EVE completa la sua missione e si disattiva. WALL•E, che inizia a nutrire un forte sentimento per lei, non ha intenzione di lasciarla andare e la segue a bordo della base spaziale Axiom. Qui, a causa dell’estremo lusso e dell’inattività secolare, la razza umana è diventata obesa e si muove a bordo di assurde poltrone volanti, comunicando tramite ologrammi. A causa di una serie di malintesi, la sicurezza considera i due robot come una minaccia e EVE perde misteriosamente la piantina. Tra rocambolesche fughe e divertenti inseguimenti, WALL•E si innamorerà completamente di EVE che, per la prima volta, potrà costatare l’intensità dei sentimenti. Quando il capitano della base spaziale dà ordine di iniziare l’esodo sulla Terra, il robot pilota AUTO, convinto che la decisione rappresenti un pericolo per l’intero equipaggio, si oppone fermamente e WALL•E dovrà dimostrare tutto il suo eroismo.");

INSERT INTO Film VALUES ("Parasite", "Barunson E&A, CJ E&M Film Financing & Investment Entertainment & Comics, CJ Entertainment, Frontier Works Comic", "Jaeil Jung", "Hong Kyung-pyo", "Bong Joon Ho, Jin Won Han", "Academy Two", 132, "Corea del Sud", "", "Bong Joon Ho", "Drammatico",
"Parasite, film diretto da Joon-ho Bong, è un dramma che racconta la storia della famiglia Kim, formata dal padre Ki-taek (Kang-ho Song), un uomo privo di stimoli, una madre, Chung-sook (Hye-jin Jang), senza alcuna ambizione e due figli, la 25enne Ki-jung (So-dam Park) e il minore, Ki-woo (Woo-sik Choi). Vivono in uno squallido appartamento, sito nel seminterrato di un palazzo, e sono molto legati tra loro, ma senza un soldo in tasca né un lavoro né una speranza per un futuro roseo. A Ki-woo viene la perversa idea di falsificare il suo diploma e la sua identità per reinventarsi come tutor e impartire lezioni a Yeon-kyuo (Yeo-jeong Jo), la figlia adolescente dei Park. Quest''ultimi sono una ricca famiglia, che, al contrario dei Kim, vivono in una grande villa, grazie ai guadagni del patriarca, dirigente di un''azienda informatica. Ki-woo insegna principalmente inglese alla ragazza a un ottimo prezzo, cosa che genera entusiasmo e speranza nella suoi parenti. Il ragazzo, notando come alla figlia minore dei Park piaccia disegnare, ha la subdola idea di inventare che sua sorella Ki-jung è un''insegnante d''arte, permettendo anche a lei di infiltrarsi nella loro vita. Le due famiglie non sanno, però, che questo incontro è solo l''inizio di una storia strana, che porterà i Kim a introdursi sempre più nella routine dei Park, come un parassita fa con un organismo estraneo.");

INSERT INTO Utente VALUES ("username", "nome", "cognome", "email", "password", "genere", "2005-05-22", 2);