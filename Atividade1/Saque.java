package Atividade1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;


public class Saque implements AcoesSaque {
    protected float x;
    //protected int[] v = new int[13];
    //protected float[] n = new float[]{200, 100, 50, 20, 10, 5, 2, 1, 0.5f, 0.25f, 0.1f, 0.05f, 0.01f};

    BigDecimal n1 = new BigDecimal(200);
    BigDecimal n2 = new BigDecimal(100);
    BigDecimal n3 = new BigDecimal(50);
    BigDecimal n4 = new BigDecimal(20);
    BigDecimal n5 = new BigDecimal(10);
    BigDecimal n6 = new BigDecimal(5);
    BigDecimal n7 = new BigDecimal(2);
    BigDecimal n8 = new BigDecimal(1);
    BigDecimal n9 = new BigDecimal("0.5");
    BigDecimal n10 = new BigDecimal("0.25");
    BigDecimal n11 = new BigDecimal("0.1");
    BigDecimal n12 = new BigDecimal("0.05");
    BigDecimal n13 = new BigDecimal("0.01");

    ArrayList<BigDecimal> notas = new ArrayList<BigDecimal>();
    ArrayList<Integer> qntdCed = new ArrayList<Integer>();


    public void criaList() {

        notas.add(n1);
        notas.add(n2);
        notas.add(n3);
        notas.add(n4);
        notas.add(n5);
        notas.add(n6);
        notas.add(n7);
        notas.add(n8);
        notas.add(n9);
        notas.add(n10);
        notas.add(n11);
        notas.add(n12);
        notas.add(n13);

        qntdCed.add(0);
        qntdCed.add(0);
        qntdCed.add(0);
        qntdCed.add(0);
        qntdCed.add(0);
        qntdCed.add(0);
        qntdCed.add(0);
        qntdCed.add(0);
        qntdCed.add(0);
        qntdCed.add(0);
        qntdCed.add(0);
        qntdCed.add(0);
        qntdCed.add(0);
        operacao();

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
        for(int i = 0; i <= 12;){
            if(xBD.compareTo(this.notas.get(i)) >= 0){
                xBD = xBD.subtract(this.notas.get(i));
                qntdCed.add(i,1);
                i = 0;
            }else{
                i++;
            }
        }

        for(int i = 0; i <= 6; i++){
            if(qntdCed.get(i) > 0){
                    System.out.println(qntdCed.get(i) + " Notas de " + this.notas.get(i) + " reais");
            }
        }

        for(int i = 7; i <= 12; i++){
            if(qntdCed.get(i) > 0){
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
        this.validaValor();
        calcSaque(x);
    }


}
