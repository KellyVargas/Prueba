package Prueba;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class punto1 {

    public static void main(String[] args) {

        String Texto= "asfsdalgkjerio435j342234234nh234o32rnowei23io4h324oi32423o4w.,345.,34534534ew342rasfsdalgkjerio435j342234234nh234o32rnowei23io4h324oi32423o4w.,345.,34534534ewf342rasfsdalgkjerio435j342234234nh234o32rnowei23io4h324oi32423o4w.,345.,34534534ewf342rasfsdalgkjerio435j342234234nh234o32rnowei23io4h324oi32423o4w.,345.,34534534ewf342r";

        try {
            int suma = sumaNumeros(Texto);
            System.out.println("La suma de los números en la cadena es: " + suma);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static int sumaNumeros(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("La cadena de entrada no puede ser nula o vacía.");
        }

        int suma = 0;
        Pattern pattern = Pattern.compile("\\d+");//busca secuencia de números
        Matcher matcher = pattern.matcher(input);//los agrupa

        //busca las agrupaciones que encontró
        while (matcher.find()) {
            suma += Integer.parseInt(matcher.group());
        }

        if (suma == 0) {
            throw new IllegalArgumentException("No se encontraron números en la cadena.");
        }

        return suma;
    }
}

