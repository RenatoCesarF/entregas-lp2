
package entidades;

import java.util.HashMap;
import util.Data;


public class Segurado {

    public enum TipoResidencia {apartamento, casa, condominio_fechado};
    public Segurado(String nome, String cpf, String cidade, 
            Data data_nascimento, int anos_habilitacao, TipoResidencia tipo_residencia) {
        this.nome = nome;
        this.cpf = cpf;
        this.cidade = cidade;
        this.data_nascimento = data_nascimento;
        this.anos_habilitacao = anos_habilitacao;
        this.tipo_residencia = tipo_residencia;
    }
    

    private String nome, cpf, cidade;
    private Data data_nascimento;
    private int anos_habilitacao;
    private TipoResidencia tipo_residencia;
    
    public TipoResidencia getTipoResidencia() { return this.tipo_residencia; };
    public String getNome() { return this.nome; };
    public String getCpf() { return this.cpf; };
    public String getCidade() { return this.cidade; };
    public Data getDataNascimento() { return this.data_nascimento; };
    public int getAnosHabilitacao() { return this.anos_habilitacao; };
    
    private static HashMap<String, Segurado> segurados = new HashMap();
    private static HashMap<String, Integer> populacoes = new HashMap();
    public static HashMap<String, Segurado> getSegurados() { return segurados; };
    public static HashMap<String, Integer> getPopulacoes() { return populacoes; };
    public static void inserir(Segurado segurado) {
        String cpf = segurado.getCpf();
        
        if(segurados.get(cpf) == null)
            segurados.put(cpf, segurado);         
        else
            System.out.println("Erro -> consulente já cadastrado com cpf : " + cpf);
    }
    
    public static int getPopulacaoCidade(String cidade) { return populacoes.get(cidade); };
    

    public static void definirPopulacoesCidades() {
        Segurado.populacoes.put("Belo Horizonte", 2521564);
        Segurado.populacoes.put("Dourados", 225495);
        Segurado.populacoes.put("Florianópolis", 508826);
        Segurado.populacoes.put("Ponta Porã", 93937);
        Segurado.populacoes.put("Salvador", 2886698);
        Segurado.populacoes.put("São Paulo", 12325232);
    }
    
    @Override
    public String toString() {
      String seguratoString = String.format(
                "Segurado %s (cpf %s) - %s anos - %s anos de habilitação "
                 + "- reside em %s - na cidade de %s (%s habitantes)", 
              this.nome, this.cpf,this.data_nascimento.calcularIdade(), this.anos_habilitacao, 
              this.tipo_residencia, this.cidade, Segurado.getPopulacaoCidade(this.cidade));
      return seguratoString;
    }
}