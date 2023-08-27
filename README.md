# HeadHunterKingdoms

Плагин на добавление кармы на сервер. Green-Tavern

# Как использовать:

```
  <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
```
	<dependency>
	    <groupId>com.github.MrTvistYT</groupId>
	    <artifactId>HeadHunterKingdoms</artifactId>
	    <version>v2</version>
	</dependency>
```


Основные методы в плагине:

```HeadHunterKingdoms.getKarma(<ник игрока>);```  - получить карму.


```HeadHunterKingdoms.settKarma(<ник игрока>, количество);``` - установить карму.


```HeadHunterKingdoms.addKarma(<ник игрока>, количество);``` - добавить карму.


```HeadHunterKingdoms.isTasked(<ник игрока>);``` - узнать есть ли задание на игрока.
