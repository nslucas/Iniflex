package org.lucasns;

import org.lucasns.Entidades.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Funcionario> funcionarios = new ArrayList<>();
        DecimalFormat decimalFormat = new DecimalFormat("#####.00");

        // 3.1 - Inserir funcionários
        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", dateFormatter), new BigDecimal(2009.44), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", dateFormatter), new BigDecimal(2284.38), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", dateFormatter), new BigDecimal(9836.14), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", dateFormatter), new BigDecimal(19119.88), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", dateFormatter), new BigDecimal(2234.68), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", dateFormatter), new BigDecimal(1582.72), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", dateFormatter), new BigDecimal(4071.84), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", dateFormatter), new BigDecimal(3017.45), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", dateFormatter), new BigDecimal(1606.85), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", dateFormatter), new BigDecimal(2799.93),  "Gerente"));

        // 3.2 - Remover funcionário "João"
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir funcionários
        System.out.println("Lista de Funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome() + ", Data Nascimento: " + funcionario.getDataNascimento().format(dateFormatter) +
                    ", Salário: " + funcionario.getSalario().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString().replace(".", ",") + ", Função: " + funcionario.getFuncao());
        }

        System.out.println("\nNovo salário dos funcionários:");
        // 3.4 - Aumentar salários
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.1"));
            String salarioFormatado = decimalFormat.format(novoSalario);
            System.out.println(funcionario.getNome() + ": " + salarioFormatado);
        }

        // 3.5 - Agrupar funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(funcionario -> funcionario.getFuncao()));

        // 3.6 - Imprimir funcionários agrupados por função
        System.out.println("\nFuncionários agrupados por função:");
        for (String funcao : funcionariosPorFuncao.keySet()) {
            System.out.println(funcao);
            for (Funcionario funcionario : funcionariosPorFuncao.get(funcao)) {
                System.out.println("- " + funcionario.getNome() + "\n");
            }
        }

        // 3.8 - Imprimir aniversariantes
        int mesAniversario = 10;
        System.out.println("\nAniversariantes do mês " + mesAniversario + ":");
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDataNascimento().getMonthValue() == mesAniversario) {
                System.out.println(funcionario.getNome());
            }
        }

        // 3.9 - Encontrar funcionário com maior idade
        LocalDate dataAtual = LocalDate.now();
        Funcionario funcionarioMaisVelho = Collections.max(funcionarios, Comparator.comparingInt(
                funcionario -> funcionario.getDataNascimento().until(dataAtual).getYears()));
        int idade = funcionarioMaisVelho.getDataNascimento().until(dataAtual).getYears();
        System.out.println("\nFuncionário mais velho: " + funcionarioMaisVelho.getNome() + ", Idade: " + idade);

        // 3.10 - Ordenar funcionários por ordem alfabética
        Collections.sort(funcionarios, Comparator.comparing(funcionario -> funcionario.getNome()));
        System.out.println("\nFuncionários ordenados alfabeticamente:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
        }

        // 3.11 - Imprimir total dos salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(funcionario -> funcionario.getSalario())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        String totalSalariosFormatado = decimalFormat.format(totalSalarios);
        System.out.println("\nTotal dos salários: " + totalSalariosFormatado.toString().replace(".", ","));

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nSalários em salários mínimos:");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + ": " + salariosMinimos);
        }
    }
}

