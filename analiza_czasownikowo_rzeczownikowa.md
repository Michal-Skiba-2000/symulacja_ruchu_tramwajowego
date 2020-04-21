# Analiza czasownikowo-rzeczownikowa

Projekt ma na celu zasymulowanie ruchu tramwajowego, bazując na uproszczonej mapie Wrocławia. Na mapie można będzie wyróżnić tory, oraz przystanki. Tramwaje bedą poruszały się po torach, przystanki bedą przypisane do danego kawałka torów, a pasażerowie będą mogli przebywać na przystanku lub w tramwaju. W każdym momencie będzie szansa na zdarzenie losowe(wykolejenie, stłuczka), która opóźni tramwaj i zablokuje przejazd dla innych tramwaji. Ocena wydajności ruchu będzię się opierała na tym jaka była średnia długość oczekiwania na tramwaj, jak często tramwaje jeździły przepełnione, oraz jak długo tramwaje stały uszkodzone lub stały z powodu innego uszkodzonego tramwaju.

## Poruszanie się tramwaji

Tramwaje będą poruszały się po określonej trasie(lini tramwajowej) od przystanku A do B, a potem od przystanku B do A itd. dopóki symulacja się nie zakończy. Każdy tramwaj będzie miał taką samą prędkość. Z przystanków bedą zabierały tyle pasażerów ile są wstanie zmieścić. Jeżeli nie będzie miejsca dla części pasażerów zostaną oni odrzuceni losowo i bedą musieli czekać na następny tramwaj. Jeżeli tramwaje spotkają się na złączeniu dwóch torów, jeden wybrany losowo będzie musiał poczekać na przejazd drugiego, choć będzie też istniała mała szansa, że się zderzą. Na jedym kawałku torów bedą mogły się poruszać dwa tramwaje, jeżeli jadą w przeciwnych kierunkach.

## Zachowanie pasażerów

Pasażerowie bedą pojawiali się losowo na przystankach z zamiarem przejechania na dany przystanek danym tramwajem, dla uproszczenia symulacji nie uwzględniamy przesiadek pasażerów. Pasażer będzie czekał na przystanku dopóki nie przyjedzie tramwaj na kóry czeka, chyba, że będzie przepełniony, wtedy będzie dalej czekał. Gdy tramwaj ulegnie uszkodzeniu, pasażer będzie musiał czekać w środku, dopóki tramwaj nie zostanie naprawiony, chyba, że uszkodzenie nastąpi przy jego przystanku docelowym.

## Parametry symulacji

- ilość tramwajów na jedną linie tramwajową
- procentowa szansa na każde z wydarzeń losowych(wykolejenie,stłuczka,zderzenie dwóch tramwaji)
- częstotliwość pojawiania się pasażerów na przystankach






