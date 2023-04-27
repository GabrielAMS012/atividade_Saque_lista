package Atividade1;

import jdk.internal.org.objectweb.asm.ClassReader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;


public class Saque extends ClearBuffer implements AcoesSaque {
    protected float x, y = 0f;
    Map<BigDecimal, Integer> mapNotasSacadas = new TreeMap<>((Collections.reverseOrder()));
    Map<BigDecimal, Integer> mapNotasP = new TreeMap<>((Collections.reverseOrder()));
    BigDecimal valorTotalDisponivel = BigDecimal.ZERO;
    ArrayList<BigDecimal> notas = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    @Override
    public void insereSaldo(){
        while(true) {
            try {
                do {
                    System.out.println("Informe o saldo disponível na sua conta");
                    y = sc.nextFloat();
                } while (y <= 0f);
                break;
            } catch (Exception e) {
                clearBuffer(sc);
                System.out.println("Valor inserido para saldo não é um número");
            }
        }
    }
    @Override
    public void mostraSaldo(){
        System.out.println("Saldo: " + y);
    }
    @Override
    public void criaList() {

        String values = "0.01//0.05//0.1//0.25//0.5//1//2//5//10//20//50//100//200";
        List<String> list = Arrays.asList(values.split("//"));
        this.notas = list.stream().map(BigDecimal::new).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public void mostraValorDisponivel(){

        mapNotasP.keySet().forEach(key -> {
            this.valorTotalDisponivel = this.valorTotalDisponivel.add(key
                    .multiply(BigDecimal.valueOf(mapNotasP.get(key)))).setScale(2, RoundingMode.HALF_EVEN);;
        });
        System.out.println("Valor total disponivel para saque: " + this.valorTotalDisponivel);
    }
    @Override
    public void perguntaQntd(){
        notas.forEach(nota -> {
            while (true) {
                try {
                    System.out.println("Qual a quantidade de notas de " + nota + "?");
                    int qp = sc.nextInt();
                    mapNotasP.put(nota, qp);
                    break;
                } catch (InputMismatchException e) {
                    clearBuffer(sc);
                    System.out.println("Valor não é um número");
                }
            }
        });
    }
    @Override
    public void adicionaNota(){
        notas.forEach(nota -> {
            mapNotasSacadas.put(nota, 0);
        });
    }
    @Override
    public void recebeValor(){
        do {
            System.out.println("Insira o valor a ser sacado");
            x = sc.nextFloat();
            if(x<0){
                System.out.println("Valor menor que 0\n");
            } else if (this.x > this.y) {
                System.out.println("Valor maior que o saldo disponível em conta\n");
            } else if (BigDecimal.valueOf(x).compareTo(this.valorTotalDisponivel) > 0) {
                System.out.println("Valor maior que o valor disponível para saque");
                System.out.println("Valor de x " + x);
                System.out.println("Valor total " + valorTotalDisponivel);
            }
        }while(x<0 || BigDecimal.valueOf(x).setScale(2, RoundingMode.HALF_EVEN)
                .compareTo(this.valorTotalDisponivel) > 0 || this.x > this.y);
    }
    @Override
    public void validaValor(){
        while(true){
            try {
                recebeValor();
                break;
            }catch (Exception e) {
                clearBuffer(sc);
                System.out.println("Valor não é um número\n");
            }

        }
    }
    @Override
    public void calcSaque(float x) {
        BigDecimal[] xBD2= {new BigDecimal(x)};
        Integer[] q2 = new Integer[1];
        Integer[] y2 = new Integer[1];
        xBD2[0] = BigDecimal.valueOf(x);
        xBD2[0] = xBD2[0].setScale(2, RoundingMode.HALF_EVEN);

        mapNotasSacadas.keySet().forEach(key -> {
            if(!xBD2[0].equals(BigDecimal.ZERO)){
                y2[0] = mapNotasP.get(key);
                q2[0] = 0;
                while(xBD2[0].compareTo(key) >= 0 && y2[0] > 0) {
                    xBD2[0] = xBD2[0].subtract(key);
                    q2[0] += 1;
                    y2[0] -= 1;
                }
                mapNotasSacadas.put(key, q2[0]);
            }

        });

        mapNotasSacadas.keySet().forEach(key -> {
            if(mapNotasSacadas.get(key) != 0) {
                if (key.compareTo(BigDecimal.valueOf(1)) > 0) {
                    System.out.println(mapNotasSacadas.get(key) + " nota(s) de " + key + " reais");
                } else if (key.compareTo(BigDecimal.valueOf(1)) == 0) {
                    System.out.println(mapNotasSacadas.get(key) + " moeda(s) de " + key + " real(is)");
                } else {
                    System.out.println(mapNotasSacadas.get(key) + " moeda(s) de " + key + " centavo(s)");
                }
            }
        });
    }


    @Override
    public void operacao(){
        this.criaList();
        this.adicionaNota();
        this.perguntaQntd();
        this.insereSaldo();
        this.mostraSaldo();
        this.mostraValorDisponivel();
        this.validaValor();
        this.calcSaque(x);
    }


}
