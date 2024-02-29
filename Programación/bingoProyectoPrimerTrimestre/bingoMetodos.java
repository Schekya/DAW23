import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class bingoMetodos {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        System.out.println("¡Bienvenido al Bingo!");
        TimeUnit.SECONDS.sleep(1);
        // Pedir cantidad que servirá como apuesta y pedir cantidad de números que se prevé en la que se acertará el bingo:
        System.out.println("Introduce la cantidad que deseas apostar en euros (cantidad mínima 1€):");
        int apuesta = input.nextInt();
        if (apuesta >= 1) { //Controla que la apuesta mínima se esté cumpliendo.
            System.out.println("¿En cuántos números crees que cantarás bingo?");
            int previsionBingo = input.nextInt();

            if (previsionBingo >= 10) { //Si la apuesta es menos de 1€ y la previsión menor que 10 (cosa que sería imposible), no será válida.
                input.nextLine();
                int[] carton = generadorCarton();
                System.out.println("Este es tu cartón: " + Arrays.toString(carton));
                TimeUnit.SECONDS.sleep(1);
                System.out.println("¡Comienza el Bingo!");
                int[] resultadoBingo = contadorBingo(carton);
                int x = resultadoBingo[0];
                int y = resultadoBingo[1];

                comprobadorBingo(previsionBingo, x, y, apuesta);
            } else {
                System.out.println("Tu previsión no es válida. Debes introducir un número igual o superior a 10."); //Mensaje de error si se preveé hacer bingo en menos de 10 números.
            } //cierra el main
        } else {
            System.out.println("La apuesta mínima es de 1€. Prueba de nuevo.");
        }
    }

    public static int[] generadorCarton() { //Método que genera el cartón
        int[] carton = new int[10];
        for (int a = 0; a < 10; a++) {
            boolean contiene; //boolean para comprobar si el número generado está ya dentro del cartón o no.
            int aleatorio;
            do {
                contiene = false;
                aleatorio = (int) (Math.random() * 99); //Generamos un número entero aleatorio hasta el 99 y forzamos que sea un número entero.
                for (int item : carton) { //Comprobamos elemento a elemento del array del cartón, que ningún número se repite.
                    if (item == aleatorio) {
                        contiene = true;
                        break;
                    }
                }
            } while (contiene);

            carton[a] = aleatorio; // Pusheamos el numero al array carton después de comprobar que no se repite.
        }
        return carton;
    }

    public static int[] contadorBingo(int[] carton) {
        boolean numeroSalido; //Boolean para evitar que un número salga repetido.
        int contadorNumerosSalidos = 0; //Variable que almacenará la cantidad de números que han salido hasta que el usuario canta bingo.
        int contadorNumerosLinea = 0; //Variable que almacenará la cantidad de números que han salido hasta acertar 5 números y hacer línea.
        int[] numerosSalidos = new int[99]; //Variable que almacenará los números que se van cantando en el bingo.
        //Generador de 99 números aleatorios que no se repiten:
        for (int n = 0; n < 99; n++) {
            int aleatorio;
            do {
                numeroSalido = false;
                aleatorio = (int) (Math.random() * 99); //Generamos un número entero aleatorio hasta el 99, con un for que se repetirá 99 veces.
                for (int item : numerosSalidos) { //Comprobamos si los números almacenados en numerosSalidos coinciden con el nuevo número aleatorio generado, puesto que no se pueden repetir.
                    if (item == aleatorio) {
                        numeroSalido = true;
                        break;
                    }

                }
            } while (numeroSalido);
            numerosSalidos[n] = aleatorio; //Una vez comprobado que el número aleatorio no se repite, se introduce en el array numerosSalidos.
            contadorNumerosSalidos++;

            System.out.println("El número " + aleatorio + "."); //Cantará los números del bingo.

            int contadorBingo = 0;
            for (int contiene : numerosSalidos) {
                for (int item : carton) {
                    if (contiene == item) {
                        contadorBingo++; //El contador cuenta cada número que sale y que tenemos en el cartón.


                    }
                }
            }
            if (contadorBingo == 5) {
                contadorNumerosLinea = contadorNumerosSalidos; //Cuenta los números que han tenido que salir para hacer linea (acertar 5 números) y almacena esa cifra.
            }
            if (contadorBingo == 10) { //Cuando el contador llega a 10, significa que hemos hecho bingo.
                System.out.println("¡Has cantado bingo!");
                break;
            }
        }
        return new int[]{contadorNumerosSalidos, contadorNumerosLinea};
    }

    public static void comprobadorBingo(int previsionBingo, int contadorNumerosSalidos, int contadorNumerosLinea, int apuesta) {
        switch (Integer.compare(previsionBingo, contadorNumerosSalidos)) { //El switch compara los dos números almacenados en previsionBingo y en contadorNumerosSalidos. Si coincide se dará el case 0 y el usuario habrá ganado.
            case 0:
                System.out.println("¡Enhorabuena, has acertado!. Tu apuesta inicial de " + apuesta + "€" + " te ha hecho ganar " + apuesta * 10 + "€. Tu previsión: " + previsionBingo + ". Números que han hecho falta para que cantases bingo: " + contadorNumerosSalidos + ". Números que han hecho falta para cantar línea: " + contadorNumerosLinea + ".");
                break;
            default:
                System.out.println("¡Enhorabuena! Sin embargo, tu apuesta no se ha cumplido, por lo que no ganas nada. Tu previsión: " + previsionBingo + ". Números que han salido para que cantases bingo: " + contadorNumerosSalidos + ". Números que han salido para cantar línea: " + contadorNumerosLinea + ".");
        }
    }

}//cierra el class


