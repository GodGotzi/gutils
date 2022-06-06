package at.gotzi.api.ano;

import java.lang.annotation.*;

public @interface Comment {

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.CONSTRUCTOR)
    @interface Constructor {

    }

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.METHOD)
    @interface Getter {

    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    @interface Setter {

    }

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.METHOD)
    @interface Init {

    }
}

/*

Variablen (int zahl, int primzahlFlag, char check),

Titlel nicht in schleife + *****

do {
    variablen reset

    Zahl eingeben in schleife am Anfang

	Alogrithums für die Hauptausgabe unseres Programms

	Ausgabe ob Zahl Primzahl

	neue primzahlbestimmung an letzter stelle der schleife
} while(check if neuer Durchlauf);

*/
