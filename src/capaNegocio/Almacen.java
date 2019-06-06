/**
 * Almacen.java
 * Definicion de la clase envoltorio Almacen
 * @author Alvaro Garcia Fuentes
 * @author Rafael Jesus Nieto Cardador
 */
package capaNegocio;
import java.util.ArrayList;

public class Almacen{

  //Atributos
  private ArrayList<Producto> almacen = new ArrayList<Producto>();
  
  //Metodos
  
  /**
   * Anade un producto al arraylist
   * @param p Objeto producto
   * @see retirarArticulo
   */
  public void anadir( String d, double pC, double pV, int s, Iva iva) throws IvaInvalidoException, StockNegativoException, PrecioDeCompraNegativoException, PrecioDeVentaNegativoException {
    almacen.add(new Producto(d, pC, pV, s, iva));
    }
  
  /**
   * Retira un producto del arraylist
   * @param c Codigo del producto
   * @see anadirArticulo
   */
  public boolean eliminar( int codigo ) {
    return almacen.remove(new Producto(codigo));
  }
  /**
   * Incrementa el stock del producto
   * @param cantidad Cantidad que se suma al stock
   * @throws StockNegativoException 
   * @throws ArgumentoNegativoException 
   * @see reducirExistencias
   */
  
  public void entrada( int codigo, int cantidad ) throws ArgumentoNegativoException, StockNegativoException, ProductoNoEncontradoException{
    if( cantidad < 0){
      throw new ArgumentoNegativoException("ERROR: La cantidad debe ser positiva.");
    }
    
    get(codigo).incrementarStock(cantidad);
    
  }
/**
 * 
 * @param codigo
 * @return
 * @throws ProductoNoEncontradoException
 */
  public Producto get(int codigo) throws ProductoNoEncontradoException {
    try {
      return this.almacen.get(this.almacen.indexOf(new Producto(codigo)));
    } catch (ArrayIndexOutOfBoundsException e) {
        throw new ProductoNoEncontradoException("ERROR: No se ha encontrado el producto");   
    }
  }
  /**
   * Decrementa el stock del producto
   * @param c Cantidad que se resta al stock
   * @throws StockNegativoException 
   * @throws ArgumentoNegativoException 
   */
  public void salida( int codigo, int cantidad ) throws StockNegativoException, ArgumentoNegativoException, ProductoNoEncontradoException{ 
    if( cantidad < 0){
      throw new ArgumentoNegativoException("ERROR: La cantidad debe ser positiva.");
    }
    get(codigo).reducirStock(cantidad);
  }
  
  public void set(int codigo, String d, double pC, double pV, int s, Iva iva) throws IvaInvalidoException, StockNegativoException, PrecioDeCompraNegativoException, PrecioDeVentaNegativoException, ProductoNoEncontradoException {
    get(codigo).set(d, pC, pV, s, iva);
  }
  
  /**
   * método toString de la clase
   * @return String
   */
  
  @Override
  public String toString(){
      return "" + almacen;
}

} // Fin de la clase Almacen