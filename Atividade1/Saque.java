package Atividade1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Saque implements AcoesSaque {
    protected float x, y = 0f;
    BigDecimal valorTotalDisponivel = BigDecimal.valueOf(0);
    ArrayList<BigDecimal> notas = new ArrayList<>();
    ArrayList<Integer> qntdCed = new ArrayList<>();
    ArrayList<Integer> qntdCedPre = new ArrayList<>();
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
                System.out.println("Valor inválido");
            }
        }
    }
    @Override
    public void mostraSaldo(){
        System.out.println("Saldo: " + y);
    }
    @Override
    public void criaList() {

        String quantidadeP = "2//2//1//5//2//10//8//5//3//12//6//15//23";
        List<String> PList = Arrays.asList(quantidadeP.split("//"));
        this.qntdCedPre = PList.stream().map(Integer::new).collect(Collectors.toCollection(ArrayList::new));



        String values = "200//100//50//20//10//5//2//1//0.5//0.25//0.1//0.05//0.01";
        List<String> list = Arrays.asList(values.split("//"));
        this.notas = list.stream().map(BigDecimal::new).collect(Collectors.toCollection(ArrayList::new));

        for(int i = 0; i <= 12; i++) {
            this.qntdCed.add(i, 0);
        }

    }

    @Override
    public void mostraValorDisponivel(){
        for(int i = 0; i < this.qntdCedPre.size(); i++){
            this.valorTotalDisponivel = this.valorTotalDisponivel.add(this.notas.get(i)
                    .multiply(BigDecimal.valueOf(this.qntdCedPre.get(i))));
        }
        System.out.println("Valor total disponivel para saque: " + this.valorTotalDisponivel);
    }

    @Override
    public void recebeValor(){
        do {
            System.out.println("Insira o valor a ser sacado");
            x = sc.nextFloat();
            if(x<0 || this.valorTotalDisponivel.compareTo(BigDecimal.valueOf(x)) < 0 || this.x >= this.y){
                System.out.println("Valor inválido\n");
            }
        }while(x<0 || this.valorTotalDisponivel.compareTo(BigDecimal.valueOf(x)) < 0 || this.x >= this.y);
    }
    @Override
    public void validaValor(){
        while(true){
            try {
                recebeValor();
                break;
            }catch (Exception e) {
                System.out.println("Valor inválido\n");
            }

        }
    }
    @Override
    public void calcSaque(float x) {
        BigDecimal yBD = new BigDecimal(y);
        BigDecimal xBD = new BigDecimal(x);
        xBD = xBD.setScale(2, RoundingMode.HALF_EVEN);
        int d = 0;
        int q;
        while(xBD.compareTo(BigDecimal.valueOf(0)) > 0 && d <= 12 && y > 0) {
            q = 0;
            while(xBD.compareTo(this.notas.get(d)) >= 0 && this.qntdCedPre.get(d) > q){
                q++;
                xBD = xBD.subtract(this.notas.get(d));
                yBD = yBD.subtract(this.notas.get(d));

            }
            qntdCed.add(d, qntdCed.get(d) + q);
            d++;
        }

        for(int i = 0; i <= 6; i++){
            if(qntdCed.get(i) != 0){
                System.out.println(qntdCed.get(i) + " Notas de "  + this.notas.get(i) + " reais");
            }
        }

        for(int i = 7; i <= 12; i++){
            if(qntdCed.get(i) != 0){
                if(this.notas.get(i).compareTo(BigDecimal.valueOf(1)) == 0){
                    System.out.println(qntdCed.get(i) + " Moeda(s) de " + this.notas.get(i) + " real");
                }
                if(!(this.notas.get(i).equals(BigDecimal.valueOf(1))) && qntdCed.get(i) > 0){
                    System.out.println(qntdCed.get(i) + " Moeda(s) de " + (this.notas.get(i).multiply(BigDecimal.valueOf(100))) + " centavos");
                }
            }
        }
    }


    @Override
    public void operacao(){
        this.insereSaldo();
        this.mostraSaldo();
        this.criaList();
        this.mostraValorDisponivel();
        this.validaValor();
        this.calcSaque(x);
    }


}
