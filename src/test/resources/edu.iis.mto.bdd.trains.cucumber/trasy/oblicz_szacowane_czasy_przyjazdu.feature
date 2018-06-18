# language: pl
 Funkcja: Informacja dla podróżnych o czasie przybycia do stacji docelowej
 	W celu bardziej efektywnego planowania podróży
 	Jako podróżny
 	Chcę wiedzieć, o której godzinie dotrę do stacji docelowej

Scenariusz: Szacowanie czasu przyjazdu 
	Zakładając chcę się dostać z "Parramatta" do "Town Hall"
	I następny pociąg odjeżdża o 8:02 na linii "Western"
Szablon scenariusza: Szacowanie czasu przyjazdu 
	Zakładając chcę się dostać z "<miastoA>" do "<miastoB>"
	I następny pociąg odjeżdża o <godzinaOdjazdu> na linii "<linia>"
 	Gdy zapytam o godzinę przyjazdu
	Wtedy powinienem uzyskać następujący szacowany czas przyjazdu: 
		|	8:29	| 
	Wtedy powinienem uzyskać następujący szacowany czas przyjazdu: <godzinaPrzyjazdu>	
	Przykłady:
		| miastoA		|	miastoB		|	godzinaOdjazdu	|	linia		|	godzinaPrzyjazdu	|
		| Paramatta		|	Town Hall	|		8:02		|	Western		|		8:29			|
		| Epping		|	Central		|		8:03		|	Northern	|		8:48			|
		| Epping		|	Central		|		8:07		|	Newcastle	|		8:37			|
		| Epping		|	Central		|		8:13		|	Epping		|		8:51			|
