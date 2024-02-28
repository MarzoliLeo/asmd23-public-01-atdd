Feature: Selezione e Spostamento delle Celle

  Background:
    Given una griglia di celle di dimensione prefissata 10
    Given una logica per il sistema di dimensione prefissata 10

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
  Given che una griglia di celle con alcune celle già selezionate
  When l'utente clicca su una cella in posizione "<x>,<y>" adiacente a una qualsiasi cella selezionata
  Then tutte le celle selezionate dovrebbero spostarsi di una posizione verso l'alto e verso destra
  And la griglia dovrebbe aggiornarsi per riflettere le nuove posizioni delle celle
  Examples:
  | x | y |
  | 1 | 2 |
  | 2 | 3 |
  | 3 | 4 |

  Scenario: Utente seleziona qualsiasi cella dopo che le celle hanno iniziato a muoversi
  Given che una griglia di celle con alcune celle già in movimento
  When l'utente clicca su qualsiasi cella
  Then tutte le celle selezionate dovrebbero spostarsi di una posizione verso l'alto e verso destra
  And la griglia dovrebbe aggiornarsi per riflettere le nuove posizioni delle celle

  Scenario: La cella selezionata raggiunge il limite della griglia
  Given che una griglia di celle con alcune celle già in movimento
  When una cella selezionata raggiunge il limite della griglia
  Then l'applicazione dovrebbe chiudersi
