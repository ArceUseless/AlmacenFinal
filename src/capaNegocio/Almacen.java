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
  public void anadirArticulo( String d, double pC, double pV, int s, Iva iva) throws IvaInvalidoException, StockNegativoException, PrecioDeCompraNegativoException, PrecioDeVentaNegativoException {
    almacen.add(new Producto(d, pC, pV, s, iva));
    }
  
  /**
   * Retira un producto del arraylist
   * @param c Codigo del producto
   * @see anadirArticulo
   */
  public boolean retirarArticulo( int codigo ) {
    return almacen.remove(new Producto(codigo));
  }
  /**
   * Incrementa el stock del producto
   * @param cantidad Cantidad que se suma al stock
   * @throws StockNegativoException 
   * @throws ArgumentoNegativoException 
   * @see reducirExistencias
   */
  
  public void incrementarExistencias( int codigo, int cantidad ) throws ArgumentoNegativoException, StockNegativoException, ProductoNoEncontradoException{
    if( cantidad < 0){
      throw new ArgumentoNegativoException("ERROR: El argumento debe ser positivo.");
    }else {
      get(codigo).incrementarStock(cantidad);
    }
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
   * @see incrementarExistencias
   */
  public void reducirExistencias( int codigo, int cantidad ) throws StockNegativoException, ArgumentoNegativoException, ProductoNoEncontradoException{ 
    if( cantidad < 0){
      throw new ArgumentoNegativoException("ERROR: El argumento debe ser positivo.");
    }else {
      if(get(codigo).getStock()-cantidad <0) {
        throw new StockNegativoException("ERROR: El stock pasaría a ser negativo.");
      }
      get(codigo).reducirStock(cantidad);
    }
  }
  
  public void modificaProducto(int codigo, String d, double pC, double pV, int s, Iva iva) throws IvaInvalidoException, StockNegativoException, PrecioDeCompraNegativoException, PrecioDeVentaNegativoException, ProductoNoEncontradoException {
    get(codigo).modificarProducto(d, pC, pV, s, iva);
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