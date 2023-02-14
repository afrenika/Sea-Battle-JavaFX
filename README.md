# Sea-Battle-JavaFX(Kotlin)
Игра морской бой на Kotlin с использование JavaFX и Scene Builder
Сначала пользователю предлгается заполнить поле кораблями разных размеров, в любом из направлений, можно сгенерировать поле рандомно и перейти к игре. 
![image](https://user-images.githubusercontent.com/114728654/218874048-9b981054-68db-49d9-842e-cc3e4f2a3893.png)
На следующем окне будет показан прогресс компьтера и предложено для атаки его поле.
Атаки производятся друг-другом и визуально отображаются. В том числе показывается состояние кораблей. При разрушении всех кораблей одной и сторон, обьявляется победитель и игра заканчивается. Предусмотрена возможность сдаться и начать игру заново.
Компьютер во время игры не имеет доступа к полю игрока и выбирает поле рандомно, если попадает в какой-то корабль, то двигается относительно точки попадания, чтобы уничтожить корабль.
![image](https://user-images.githubusercontent.com/114728654/218874108-9359b528-6fa4-4edb-90fe-6fe104679a51.png)
К сожалению в ряде случаев, игра зависает, причина этому пока не найдена.
