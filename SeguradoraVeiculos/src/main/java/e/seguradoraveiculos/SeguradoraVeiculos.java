package e.seguradoraveiculos;

import entidades.Segurado;
import util.Data;


public class SeguradoraVeiculos {
    public enum CategoriaRisco {baixa, media, alta, indefinida};
    
    public static void cadastrarSegurados() {
        Segurado.inserir(new Segurado("Marina Tempra", "111.111.111-11", "Ponta Porã", 
                          new Data(15, 5, 2000), 4, Segurado.TipoResidencia.casa));
        
        Segurado.inserir(new Segurado("Leonardo Talure", " 222.222.222-22", "Belo Horizonte",
                        new Data(10, 11, 1986), 17, Segurado.TipoResidencia.apartamento));
        
        Segurado.inserir(new Segurado("Adriana Raski", "333.333.333-33", "Dourados",
                        new Data(11, 4, 2004), 0, Segurado.TipoResidencia.condominio_fechado));
        
        Segurado.inserir(new Segurado("Fabrício Salvi", "777.777.777-77", "Florianópolis",
                        new Data(15, 5, 1946), 5, Segurado.TipoResidencia.apartamento));
        
        Segurado.inserir(new Segurado("Marina Tempra", "888.888.888-88", "Salvador",
                        new Data(7, 3, 2003), 1, Segurado.TipoResidencia.casa));
        
        Segurado.inserir(new Segurado("Alexia Caltaro", " 999.999.999-99", "São Paulo",
                        new Data(14, 2, 1930), 60, Segurado.TipoResidencia.casa));

    }
    
    public static CategoriaRisco calcularCategoriaRiscoSeguro(Segurado segurado) {
        int riscoSeguro = 0;
        int idade = segurado.getDataNascimento().calcularIdade();
   
        
        // CALCULO RISCO POR IDADE
        if (idade >= 18 || idade <= 21)
            riscoSeguro += 3;
        else if (idade >= 22 || idade <= 26)
            riscoSeguro += 1;
        else if (idade >= 80 || idade <= 90)
            riscoSeguro += 2;
        else if (idade > 90)
            riscoSeguro += 4;

        if (segurado.getAnosHabilitacao() <= 1)
            riscoSeguro += 2;

        int num_habitantes = Segurado.getPopulacaoCidade(segurado.getCidade());
        Segurado.TipoResidencia residencia = segurado.getTipoResidencia();
        
        // CALCULO RISCO POR TAMANHO DE POPULACAO
        switch (residencia) {
            case casa -> {
                if (num_habitantes < 100000)
                    riscoSeguro += 1;
                
                else if (num_habitantes > 100000 && num_habitantes < 40000)
                    riscoSeguro += 2;
                
                else if (num_habitantes > 400000 && num_habitantes < 1000000)
                    riscoSeguro += 3;
                
                else if (num_habitantes > 1000000 && num_habitantes < 3000000)
                    riscoSeguro += 5;
                else if (num_habitantes > 3000000)
                    riscoSeguro += 7;
            }
            case apartamento -> {
                if (num_habitantes > 100000 && num_habitantes < 40000)
                    riscoSeguro += 1;
                
                else if (num_habitantes > 400000 && num_habitantes < 1000000)
                    riscoSeguro += 2;
                
                else if (num_habitantes > 1000000 && num_habitantes < 3000000)
                    riscoSeguro += 2;
                else if (num_habitantes > 3000000)
                    riscoSeguro += 3;
            }
            case condominio_fechado -> {
                if (num_habitantes > 1000000 && num_habitantes < 3000000)
                    riscoSeguro += 2;
                else if (num_habitantes > 3000000)
                    riscoSeguro += 3;
            }
            default -> {
            }
        }

        if (riscoSeguro <= 5)
            return CategoriaRisco.baixa;
        if (riscoSeguro > 5 && riscoSeguro < 10)
            return CategoriaRisco.media;
        if (riscoSeguro >= 10)
            return CategoriaRisco.alta;

        return CategoriaRisco.indefinida;

    }
    
    public static void imprimirCategoriaRiscoSegurados(Iterable<Segurado> segurados) {
        for (Segurado segurado : segurados) {
            CategoriaRisco risco = SeguradoraVeiculos.calcularCategoriaRiscoSeguro(segurado);
            System.out.println(segurado + " - com categoria de risco " + risco);
        }
    }
    public static void main(String[] args) {
        SeguradoraVeiculos.cadastrarSegurados();
        Segurado.definirPopulacoesCidades();
         
        Iterable seguradosAsIterable = Segurado.getSegurados().values();
         
        imprimirCategoriaRiscoSegurados(seguradosAsIterable);
     }

}
