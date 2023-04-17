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
    ArrayList<BigDecimal> notas = new ArrayList<BigDecimal>();
    ArrayList<Integer> qntdCed = new ArrayList<Integer>();


    public void criaList() {
        notas.forEach(nota-> nota.add(new BigDecimal(2)));


        String values = "200//100//50//20//10//5//2//1//0.5//0.25//0.1//0.05//0.01";
        List<String> list = Arrays.asList(values.split("//"));
        this.notas = new ArrayList<>(list.stream().map(value-> new BigDecimal(value)).collect(Collectors.toList()));

        for(int i = 0; i <= 12; i++) {
            this.qntdCed.add(i, 0);
        }

    }

    @Override
    public void recebeValor(){
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Insira o valor a ser sacado");
            x = sc.nextFloat();
            if(x<0){
                System.out.println("Valor inválido\n");
            }
        }while(x < 0);
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
        int l = 0;
        int q = 0;
        while(xBD.compareTo(BigDecimal.valueOf(0)) > 0 && y <= 12 && l <= 12) {
            q = 0;
            while(xBD.compareTo(this.notas.get(y)) >= 0){
                q++;
                xBD = xBD.subtract(this.notas.get(y));
            }
            qntdCed.add(l, qntdCed.get(l) + q);
            l++;
            y++;
        }


        for(int i = 0; i <= 6; i++){
            if(qntdCed.get(i) != 0){
                System.out.println(qntdCed.get(i) + " Notas de " + this.notas.get(i) + " reais");
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
        this.validaValor();
        this.calcSaque(x);
    }


}
