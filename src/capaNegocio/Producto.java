/**
 * Producto.java
 * Definicion de la clase Producto
 * @author Alvaro Garcia Fuentes
 * @author Rafael Jesus Nieto Cardador
 */
package capaNegocio;

public class Producto{
  
  //Atributos de instancia
  private int codigo;
  private String descripcion;
  private double precioCompra;
  private double precioVenta;
  private int stock;
  
  private Iva tipoDeIva;

  //Atributo de clase
  private static int cuentaProductos = 0;

  /**
   * Constructor parametrico
   * @param d
   * @param pC
   * @param pV
   * @param s
   * @param iva
   * @throws IvaInvalidoException 
   * @throws PrecioDeCompraNegativoException 
   * @throws PrecioDeVentaNegativoException 
   * @throws StockNegativoException 
   */
  public Producto( String d , double pC , double pV , int s, Iva iva ) throws IvaInvalidoException, PrecioDeCompraNegativoException, PrecioDeVentaNegativoException, StockNegativoException{
    setCodigo();
    setDescripcion(d);
    setPrecioCompra(pC);
    setPrecioVenta(pV);
    setStock(s);
    setTipoDeIva(iva);
    }

  public Producto(int c) {
    setCodigo(c);
  }

  private void setCodigo(int c) {
    this.codigo = c;
    
  }

  // Getters
  /**
   * getter para codigo
   * @return int
   * @see setCodigo
   */
  public int getCodigo(){
    return this.codigo;
    }
  
  /**
   * getter para descripcion
   * @return String
   * @see setDescripcion
   */
  public String getDescripcion(){
    return this.descripcion;
    }

  /**
   * getter para precioCompra
   * @return double
   * @see setPrecioCompra
   */
  public double getPrecioCompra(){
    return this.precioCompra;
    }
  
  /**
   * getter para precioVenta
   * @return double
   * @see setPrecioVenta
   */
  public double getPrecioVenta(){
    return this.precioVenta;
    }
  
  /**
   * getter para stock
   * @return int
   * @see setStock
   */
  public int getStock(){
    return this.stock;
    }
  
  /**
   * getter para el tipo de IVA
   * @return String (contiene el tipo de IVA)
   */
  public Iva getTipoDeIva() {
    return this.tipoDeIva;
  }
  
  //Setters
  /**
   * setter para codigo
   * @see getCodigo
   */
  private void setCodigo(){
    this.codigo = cuentaProductos++;
    }

  /**
   * setter para descripcion
   * @param d
   * @see getDescripcion
   */
  private void setDescripcion( String d ){
    this.descripcion = d;
    }
  
  /**
   * setter para PrecioCompra
   * @param pc
   * @see getPrecioCompra
   */
  private void setPrecioCompra( double pc )throws PrecioDeCompraNegativoException{
    if( pc < 0 ){
      throw new PrecioDeCompraNegativoException("ERROR: El precio de compra no puede ser negativo");
    }
    this.precioCompra = pc;
    }
  
  /**
   * setter para precioVenta
   * @param pv
   * @throws PrecioDeVentaNegativoException 
   * @see getPrecioVenta
   */
  private void setPrecioVenta( double pv ) throws PrecioDeVentaNegativoException{
    if( pv < 0 ){
      throw new PrecioDeVentaNegativoException("ERROR: El precio de venta no puede ser negativo.");
    }
    this.precioVenta = pv;
  }
  
  /**
   * setter para stock
   * @param s
   * @throws StockNegativoException 
   * @see getStock
   */
  private void setStock( int s ) throws StockNegativoException{
    if( s < 0 ){
      throw new StockNegativoException("ERROR: El stock no puede ser negativo");
    }
    this.stock = s; 
  }
  
  /**
   * setter para el tipo de IVA
   * @param iva
   */
  private void setTipoDeIva(Iva iva) throws IvaInvalidoException {
    if(iva == null) {
      throw new IvaInvalidoException("ERROR: El tipo de iva no es válido.");
    }
    this.tipoDeIva = iva;

    
  }
  
  //Metodos
  
  void incrementarStock(int cantidad) throws StockNegativoException {
    setStock(getStock()+cantidad);
  }
  
  void reducirStock(int cantidad) throws StockNegativoException {
    setStock(getStock()-cantidad);
  }
  
  void set(String d, double pC, double pV, int s, Iva iva) throws IvaInvalidoException, StockNegativoException, PrecioDeCompraNegativoException, PrecioDeVentaNegativoException {
    setDescripcion(d);
    setPrecioCompra(pC);
    setPrecioVenta(pV);
    setStock(s);
    setTipoDeIva (iva);
  }
  

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + codigo;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Producto other = (Producto) obj;
    if (codigo != other.codigo)
      return false;
    return true;
  }

  /**
   * Metodo toString de la clase
   * @return String
   */
  @Override
  public String toString(){
    return "\nCodigo - " + this.codigo
        + "\nDescripcion - " + this.descripcion
        + "\nPrecio de compra - " + this.precioCompra
        + "\nPrecio de venta - " + this.precioVenta
        + "\nStock - " + this.stock
        + "\nTipo de IVA - "+ this.tipoDeIva + "\n";
    }
  
} // Fin de la clase Producto