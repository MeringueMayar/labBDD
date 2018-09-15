# language: pl
Właściwość: Dowiedz się, o której godzinie odjeżdżają nastepne pociągi do stacji docelowej
	W celu bardziej efektywnego planowania podróży
	Jako podróżny
	Chcę się dowiedzieć, jakie następne pociągi odjeżdżają do mojej stacji docelowej

Scenariusz: Znajdź optymalną trasę pomiędzy stacjami na tej samej linii.
	Zakładając pociągi linii "Western" z "Emu Plains" odjeżdżają ze stacji "Parramatta" do "Town Hall" o
		|	7:58	|
		|	8:00	|
		|	8:02	|
		|	8:11	|
		|	8:14	|
		|	8:21	|
		|	8:31	|
		|	8:36	|
	Jeżeli chcę podróżować z "Parramatta" do "Town Hall" o 8:00
	Wtedy powinienem uzyskać informację o pociągach o: 
		|	8:03	|
		|	8:05	|
		|	8:07	|
		|	8:16	|
	