package Atividade1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Saque implements AcoesSaque {
    protected float x;
    BigDecimal valorTotalDisponivel = BigDecimal.valueOf(0);
    ArrayList<BigDecimal> notas = new ArrayList<BigDecimal>();
    ArrayList<Integer> qntdCed = new ArrayList<Integer>();
    ArrayList<Integer> qntdCedPre = new ArrayList<Integer>();

    @Override
    public void criaList() {

        String quantidadeP = "2//2//1//5//2//10//8//5//3//12//6//15//23";
        List<String> PList = Arrays.asList(quantidadeP.split("//"));
        this.qntdCedPre = new ArrayList<>(PList.stream().map(Integer::new).collect(Collectors.toList()));
        notas.forEach(nota-> nota.add(new BigDecimal(2)));


        String values = "200//100//50//20//10//5//2//1//0.5//0.25//0.1//0.05//0.01";
        List<String> list = Arrays.asList(values.split("//"));
        this.notas = new ArrayList<>(list.stream().map(BigDecimal::new).collect(Collectors.toList()));

        for(int i = 0; i <= 12; i++) {
            this.qntdCed.add(i, 0);
        }

    }

    @Override
    public void mostraValorDisponivel(){
        for(int i = 0; i < qntdCedPre.size(); i++){
            this.valorTotalDisponivel = this.valorTotalDisponivel.add(this.notas.get(i)
                    .multiply(BigDecimal.valueOf(qntdCedPre.get(i))));
        }
        System.out.println("Valor total disponivel para saque: " + this.valorTotalDisponivel);
    }

    @Override
    public void recebeValor(){
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Insira o valor a ser sacado");
            x = sc.nextFloat();
            if(x<0 || this.valorTotalDisponivel.compareTo(BigDecimal.valueOf(x)) < 0){
                System.out.println("Valor inválido\n");
            }
        }while(x < 0 || this.valorTotalDisponivel.compareTo(BigDecimal.valueOf(x)) < 0);
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
        BigDecimal xBD = new BigDecimal(x);
        xBD = xBD.setScale(2, RoundingMode.HALF_EVEN);
        int y = 0;
        int q;
        while(xBD.compareTo(BigDecimal.valueOf(0)) > 0 && y <= 12) {
            q = 0;
            while(xBD.compareTo(this.notas.get(y)) >= 0 && qntdCedPre.get(y) > q){
                q++;
                xBD = xBD.subtract(this.notas.get(y));
            }
            qntdCed.add(y, qntdCed.get(y) + q);
            y++;
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
        this.criaList();
        this.mostraValorDisponivel();
        this.validaValor();
        this.calcSaque(x);
    }


}
