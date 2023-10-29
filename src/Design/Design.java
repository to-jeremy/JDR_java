package Design;

public class Design {
    public static void effacerConsole(){
        for(int i = 0; i<100; i++)
            System.out.println();
    }

    public static void separateurAffichage(int n){
        for(int i = 0; i<n; i++)
            System.out.print("-");
        System.out.println();
    }

    public static void titreAffichage_1(String titre){
        separateurAffichage(40);
        System.out.println(titre);
        separateurAffichage(40);
    }

    public static void titreAffichage_2(String titre){
        separateurAffichage(30);
        System.out.println(titre);
        separateurAffichage(30);
    }

    public static void titreAffichage_3(String titre){
        separateurAffichage(30);
        System.out.println(titre);
    }

    public static void titreAffichage_4(String titre){
        System.out.println(titre);
        separateurAffichage(30);
    }

    public static void titreAffichage_5(String titre){
        separateurAffichage(40);
        System.out.println(titre);
    }
}
