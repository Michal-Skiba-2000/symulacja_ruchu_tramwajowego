# Analiza czasownikowo-rzeczownikowa

Projekt ma na celu zasymulowanie ruchu tramwajowego, bazując na uproszczonej <b>mapie</b> Wrocławia. Na <b>mapie</b> można będzie wyróżnić <b>tory</b>, oraz <b>przystanki</b>. <b>Tory</b> bedą podzielone na <b>sektory</b>, których przebycie zajmuje każdemu <b>tramwajowi</b> jedną minutę. <b>Tramwaje</b> bedą poruszały się po <b>torach</b>, <b>przystanki</b> bedą przypisane do danego <b>sektora torów</b>, a <b>pasażerowie</b> będą mogli przebywać na <b>przystanku</b> lub w <b>tramwaju</b>. <i>W każdym momencie będzie szansa na <b>zdarzenie losowe(wykolejenie, stłuczka)</b>, która opóźni <b>tramwaj</b> i zablokuje przejazd w danym <b>sektorze</b> dla innych <b>tramwaji</b>. Ocena wydajności ruchu będzię się opierała na tym jaka była średnia długość oczekiwania na tramwaj, jak często tramwaje jeździły przepełnione, oraz jak długo tramwaje stały uszkodzone lub stały z powodu innego uszkodzonego tramwaju.</i>

## Poruszanie się tramwajów

<i><b>Tramwaje</b> będą poruszały się po określonej <b>trasie(lini tramwajowej)</b> od <b>przystanku</b> A do B, a potem od <b>przystanku</b> B do A itd. dopóki symulacja się nie zakończy. Każdy <b>tramwaj</b> będzie miał taką samą prędkość. Z <b>przystanków</b> bedą zabierały tyle <b>pasażerów</b> ile są wstanie zmieścić. Jeżeli nie będzie miejsca dla części <b>pasażerów</b> zostaną oni odrzuceni losowo i bedą musieli czekać na następny <b>tramwaj</b>. Jeżeli <b>tramwaje</b> spotkają się na złączeniu dwóch <b>torów</b>, jeden wybrany losowo będzie musiał poczekać na przejazd drugiego, choć będzie też istniała mała szansa, że się zderzą. W jednym <b>sektorze</b> bedą mogły się poruszać dwa <b>tramwaje</b>, jeżeli jadą w przeciwnych kierunkach.</i>

## Zachowanie pasażerów

<i><b>Pasażerowie</b> bedą pojawiali się losowo na <b>przystankach</b> z zamiarem przejechania na dany <b>przystanek</b>, dla uproszczenia symulacji nie uwzględniamy przesiadek <b>pasażerów</b>. Ilość <b>pasażerów</b> pojawiających się będzie zależna od <b>pory dnia</b>. <b>Pasażer</b> będzie czekał na <b>przystanku</b> dopóki nie przyjedzie <b>tramwaj</b>, którym może dojechać na docelowy <b>przystanek</b>, chyba, że będzie przepełniony, wtedy będzie czekał dalej. Gdy <b>tramwaj</b> ulegnie uszkodzeniu, <b>pasażer</b> będzie musiał czekać w środku, dopóki <b>tramwaj</b> nie zostanie naprawiony, chyba, że <b>uszkodzenie</b> nastąpi przy jego <b>przystanku</b> docelowym.</i>

## Parametry symulacji

- ilość tramwajów na jedną linie tramwajową
- procentowa szansa na każde z wydarzeń losowych(wykolejenie,stłuczka,zderzenie dwóch tramwajów)
- częstotliwość pojawiania się pasażerów na przystankach






