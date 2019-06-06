/**
 * pruebaProducto.java
 * Programa para gestionar un almacén
 * usando la clase Producto definida en el fichero Producto.java
 * @author Alvaro García Fuentes
 * @author Rafael Jesús Nieto Cardador
 */
package capaPresentacion;

import capaNegocio.*;
import utiles.*;

public class PruebaProducto {

  private static final Menu menuIva = new Menu("Elige uno de los siguientes tipos de IVA:",
      new String[] { "GENERAL", "REDUCIDO", "SUPER REDUCIDO" });
  private static final Menu menuGeneral = new Menu("Elige una de las opciones del almacén:", new String[] {
      "Mostrar Almacén", "Alta", "Baja", "Modificación", "Entrada de Mercancía", "Salida de Mercancía", "Salir" });
  private static final Almacen almacen = new Almacen();

  public static void main(String[] args) {
    int opcion = 0;

    anadirProductosPrueba();

    do {
      opcion = menuGeneral.gestionMenu();
      switch (opcion) {
      case 1:
        mostrarAlmacen();
        break;
      case 2:
        altaProducto();
        break;
      case 3:
        bajaProducto();
        break;
      case 4:
        modificaProducto();
        break;
      case 5:
        entradaMercancia();
        break;
      case 6:
        salidaMercancia();
        break;
      case 7:
        System.out.println("Saliendo...");
        break;
      }
    } while (opcion != 7);
  }

  private static void anadirProductosPrueba() {
    try {
      almacen.anadir("zumo", 1, 2, 300, Iva.GENERAL);
      almacen.anadir("cerveza", 1, 2, 20, Iva.GENERAL);
      almacen.anadir("agua", 1, 2, 300, Iva.GENERAL);
      almacen.anadir("cc", 1, 2, 300, Iva.GENERAL);
      almacen.anadir("fanta", 1, 2, 300, Iva.SUPER_REDUCIDO);
      almacen.anadir("zusdfasdfsamo", 1, 2, 30, Iva.GENERAL);
    } catch (IvaInvalidoException | StockNegativoException | PrecioDeCompraNegativoException
        | PrecioDeVentaNegativoException e) {
      // No entrará
    }
  }

  public static void mostrarAlmacen() {
    System.out.println(almacen);
  }

  private static void altaProducto() {
    try {
      System.out.println("\n======DAR DE ALTA PRODUCTO======");
      almacen.anadir(Teclado.leerCadena("Introduzca la descripción del producto."),
          Teclado.leerDecimal("Introduzca el precio de compra del producto."),
          Teclado.leerDecimal("Introduzca el precio de venta del producto."),
          Teclado.leerEntero("Introduzca el stock del producto."), gestionaIva(menuIva.gestionMenu()));
    } catch (IvaInvalidoException | StockNegativoException
        | PrecioDeCompraNegativoException | PrecioDeVentaNegativoException e) {
      System.err.println(e.getMessage());
    }
  }

  private static void bajaProducto() {
    System.out.println("\n======DAR DE BAJA PRODUCTO======");
    
      if (!almacen.eliminar(Teclado.leerEntero("Introduzca el código del producto a eliminar"))) {
        System.err.println("No se ha encontrado el producto.");
      }
    

  }

  private static void modificaProducto() {
    System.out.println("\n======MODIFICAR EL PRODUCTO======");
    try {
      int codigo = Teclado.leerEntero("Introduzca el código del producto: ");
      System.out.println(almacen.get(codigo));
      almacen.set(codigo, Teclado.leerCadena("Nueva descripción: "),
          Teclado.leerDecimal("Nuevo precio de compra: "), Teclado.leerDecimal("Nuevo precio de venta: "),
          Teclado.leerEntero("Nuevo stock: "), gestionaIva(menuIva.gestionMenu()));
    } catch (IvaInvalidoException | StockNegativoException | PrecioDeCompraNegativoException
        | PrecioDeVentaNegativoException | ProductoNoEncontradoException err) {
      System.err.println(err.getMessage());
    }
  }

  private static void entradaMercancia() {
    System.out.println("\n======ENTRADA DE MERCANCÍA======");
    try {
      int codigo = Teclado.leerEntero("Introduzca el código del producto");
      System.out.println(almacen.get(codigo));
      almacen.entrada(codigo, Teclado.leerEntero("Introduzca la cantidad que desea sumar al stock"));
    } catch (ProductoNoEncontradoException | ArgumentoNegativoException | 
         StockNegativoException | ArrayIndexOutOfBoundsException e) {
      System.err.println(e.getMessage());
      return;
    }
  }

  private static void salidaMercancia() {
    System.out.println("\n======SALIDA DE MERCANCÍA======");
    try {
      int codigo = Teclado.leerEntero("Introduzca el código del producto");
      System.out.println(almacen.get(codigo));
      almacen.salida(codigo, Teclado.leerEntero("Introduzca la cantidad que desea restar al stock"));
    } catch (ProductoNoEncontradoException | ArgumentoNegativoException 
        | StockNegativoException | ArrayIndexOutOfBoundsException e) {
      System.err.println(e.getMessage());
      return;
    }
  }

  public static Iva gestionaIva(int opcion) {
    switch (opcion) {
    case 1:
      return Iva.GENERAL;
    case 2:
      return Iva.REDUCIDO;
    case 3:
      return Iva.SUPER_REDUCIDO;
    default:
      return null;

    }
  }
}