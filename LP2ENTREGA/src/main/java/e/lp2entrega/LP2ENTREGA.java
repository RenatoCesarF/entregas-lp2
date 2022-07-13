package e.lp2entrega;

import controle.SeguradoraVeiculos;
import static controle.SeguradoraVeiculos.imprimirCategoriaRiscoSegurados;
import entidades.Segurado;

public class LP2ENTREGA {

    public static void main(String[] args) {
       SeguradoraVeiculos.cadastrarSegurados();
       Segurado.definirPopulacoesCidades();
        
       Iterable seguradosAsIterable = Segurado.getSegurados().values();
        
       imprimirCategoriaRiscoSegurados(seguradosAsIterable);
    }
}
