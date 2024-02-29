Feature: Selezione e Spostamento delle Celle

  Background:
    Given una logica per il sistema ed una griglia di dimensione prefissata 10

  Scenario Outline: Utente seleziona una cella
  When l'utente clicca su una cella in posizione <x> and <y>
  Then quella cella <x>,<y> dovrebbe essere numerata in modo incrementale
  And la cella in posizione <x>,<y> dovrebbe mostrare il numero
  Examples:
  | x | y |
  | 1 | 1 |
  | 2 | 2 |
  | 3 | 3 |

  Scenario Outline: Utente seleziona più celle non adiacenti
  Given con alcune celle già selezionate <pre-selectedX>,<pre-selectedY>
  When l'utente clicca su una cella in posizione <x>,<y> che non è adiacente a nessuna cella selezionata <pre-selectedX>,<pre-selectedY>
  Then quella cella <x>,<y> dovrebbe essere numerata in modo incrementale
  And la cella in posizione <x>,<y> dovrebbe mostrare il numero
  Examples:
  | x | y | pre-selectedX | pre-selectedY |
  | 1 | 3 | 1             | 1             |
  | 2 | 4 | 2             | 2             |
  | 3 | 5 | 3             | 3             |

  Scenario Outline: Utente seleziona una cella adiacente a una cella selezionata
  Given con alcune celle già selezionate <pre-selectedX>,<pre-selectedY>
  When l'utente clicca su una cella in posizione <x>,<y> adiacente a una qualsiasi cella selezionata <pre-selectedX>,<pre-selectedY>
  Then tutte le celle selezionate <x>,<y> dovrebbero spostarsi di una posizione verso l'alto e verso destra
  Examples:
  | x | y | pre-selectedX | pre-selectedY |
  | 1 | 2 | 1             | 1             |
  | 2 | 3 | 2             | 2             |
  | 3 | 4 | 3             | 3             |

  Scenario Outline: La cella selezionata raggiunge il limite della griglia
  Given con alcune celle già selezionate <x>,<y>
  When una cella <x>,<y> selezionata raggiunge il limite della griglia
  Then l'applicazione dovrebbe chiudersi
  Examples:
  | x   | y   |
  | 5   | -1  |
  | 10  | -1  |
  | 10  | 5   |