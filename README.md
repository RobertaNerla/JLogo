# JLogo
Logo è un linguaggio di programmazione didattico sviluppato alla fine degli anni '60, con 
la finalità di fornire uno strumento per programmare dei disegni. 
L'obiettivo del progetto del corso di Programmazione Avanzata è quello di sviluppare un 
ambiente di esecuzione per il Logo in Java

# Definizione delle responsabilità
In fase di progettazione dell’applicazione JLogo sono state individuate le seguenti 
responsabilità:
1) Tenere traccia di tutte le linee o figure disegnate dal cursore sul foglio di lavoro. Mettere a 
disposizione le funzionalità necessarie per:
- individuare una nuova figura qualora venisse disegnata;
- aggiornare le linee a disposizione per la creazione di ulteriori nuove figure.
2) Rappresentare un cursore di un’applicazione Logo in grado di muoversi all’interno del foglio 
di lavoro, eventualmente disegnando linee o figure ed eseguendo i comandi assegnati;
3) Rappresentare genericamente il concetto di linea;
4) Rappresentare il concetto di figura, come composizione di una sequenza di linee 
consecutive ed adiacenti;
5) Rappresentare il concetto di coordinata, così da creare un sistema di riferimento all’interno 
del foglio di lavoro;
6) Rappresentare un comando generico di un’applicazione Logo, così da permettere di
eseguire una funzionalità ed impostare i parametri necessari all’esecuzione;
7) Eseguire il parsing di una stringa contenente una lista di comandi per un programma Logo
ed invocare la loro esecuzione;
8) Interpretare stringhe di comandi ed impostare i loro parametri;
9) Produrre in output una stringa che rappresenta l’esecuzione di un programma Logo e che 
descrive tutti gli elementi disegnati sul foglio di lavoro;
10) Controllare l’esecuzione di un programma Logo;

# Implementazione delle responsabilità
Una volta individuate le responsabilità sopra descritte, sono state intraprese le seguenti scelte 
di implementazione:
1) Responsabilità: Tenere traccia delle linee e delle figure disegnate sul foglio di lavoro.
A questo scopo sono state implementate:
- Un’interfaccia Worksheet, che rappresenta un generico foglio di lavoro per un 
programma Logo;
- Una classe DefaultWorksheet che implementa l’interfaccia appena descritta.
2) Responsabilità: Rappresentare il cursore di un programma logo.
A questo scopo sono state implementate:
- Un’interfaccia Turtle che rappresenta un generico cursore;
- Una classe DefaultTurtle che definisce un cursore di default.
3) Responsabilità: Rappresentazione del concetto di linea.
A questo scopo sono state implementate:
- Un’interfaccia Line per rappresentare una generica linea con due estremi;
- Una classe Segment che implementa l’interfaccia sopra citata.
4) Responsabilità: Rappresentazione del concetto di figura.
Per far ciò è stato implementato un record Figure;
personalmente non ho ritenuto strettamente necessaria la creazione di un’interfaccia che 
permettesse un’estendibilità ulteriore del concetto di “figura”, dato che una figura 
generalmente è composta da una sequenza di linee consecutive ed adiacenti. 
5) Responsabilità: Rappresentazione del concetto di coordinata.
A questo scopo sono state implementate:
- Un’interfaccia Coordinate;
- Una classe SimpleCoordinate.
6) Responsabilità: Rappresentazione del concetto di comando.
A questo scopo sono state implementate:
- Un’interfaccia Command che rappresenta un generico comando;
- Una classe astratta AbstractCommandWithName per fare in modo che tutti i comandi 
estendano questa classe e acquisiscano il campo “name”, così da riuscire ad attribuire 
un nome ad ogni istruzione;
- Un’enum CommandName, che definisce i nomi dei comandi di default messi a 
disposizione dall’applicazione;
- Una classe astratta MotionCommand, per raggruppare le funzionalità dei comandi di 
movimento;
- Una classe astratta ColoringCommand, per raggruppare le funzionalità dei comandi che 
hanno lo scopo di cambiare colore ad un oggetto;
- Infine, è stata costruita una classe per ogni comando che le specifiche di progetto 
chiedevano di implementare.


