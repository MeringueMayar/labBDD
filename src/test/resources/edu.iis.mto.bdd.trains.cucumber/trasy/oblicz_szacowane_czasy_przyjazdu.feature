# language: pl
Właściwość: Informacja dla podróżnych o czasie przybycia do stacji docelowej
	W celu bardziej efektywnego planowania podróży
	Jako podróżny
	Chcę wiedzieć, o której godzinie dotrę do stacji docelowej

Scenariusz: Szacowanie czasu przyjazdu 
 	Zakładając chcę się dostać z "Parramatta" do "TownHall"
 	I następny pociąg na linii "Western" odjeżdża o
 	| 8:02 | 
 	Jeżeli zapytam o godzinę przyjazdu
 	Wtedy powinienem uzyskać następujący szacowany czas przyjazdu: 
 	| 8:29 | 

Szablon scenariusza: Szacowanie czasu przyjazdu
    Zakładając chcę się dostać <start> do <stop>
    I następny pociąg odjeżdża o <odjazd> na linii <linia>
    Jeżeli zapytam o godzinę przyjazdu
    Wtedy powinienem uzyskać następujący szacowany czas przyjazdu: <przyjazd>
    Przykłady:
      | start  | stop     | odjazd  | linia     | przyjazd  |
      | Epping |  Central | 8:03    | Northern  |  8:48     |
      | Epping |  Central | 8:07    | Newcastle |  8:37     |
      | Epping |  Central | 8:13    | Epping    |  8:51     |