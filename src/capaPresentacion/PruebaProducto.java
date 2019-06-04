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

  /**
   * Programa principal
   * 
   * @param args
   */
  public static void main(String[] args) {

    Almacen almacen = new Almacen();
    Menu menuIva = new Menu("Elige uno de los siguientes tipos de IVA:",
        new String[] { "GENERAL", "REDUCIDO", "SUPER REDUCIDO" });
    Menu menuGeneral = new Menu("Elige una de las opciones del almacén:", new String[] { "Mostrar Almacén", "Alta",
        "Baja", "Modificación", "Entrada de Mercancía", "Salida de Mercancía", "Salir" });

    try {
      almacen.anadirArticulo("zumo", 1, 2, 300, Iva.GENERAL);
      almacen.anadirArticulo("cerveza", 1, 2, 20, Iva.GENERAL);
      almacen.anadirArticulo("agua", 1, 2, 300, Iva.GENERAL);
      almacen.anadirArticulo("cc", 1, 2, 300, Iva.GENERAL);
      almacen.anadirArticulo("fanta", 1, 2, 300, Iva.SUPER_REDUCIDO);
      almacen.anadirArticulo("zusdfasdfsamo", 1, 2, 30, Iva.GENERAL);
    } catch (IvaInvalidoException | StockNegativoException | PrecioDeCompraNegativoException
        | PrecioDeVentaNegativoException e) {
      // No entrará
    }
    int opcion = 0;
    // Menu
    do {
      try {
        opcion = menuGeneral.gestionMenu();
      } catch (EnteroNoValidoException enve) {
        System.err.println(enve.getMessage());
        opcion = -1;
      }

      // Estructura switch para manejar el menú
      switch (opcion) {
      // Mostrar almacén
      case 1:
        mostrarAlmacen(almacen);
        break;
      case 2:
        // Alta
        altaProducto(almacen, menuIva);
        break;
      case 3:
        // Baja
        bajaProducto(almacen);
        break;
      case 4:
        // Modificar
        modificaProducto(almacen, menuIva);
        break;
      case 5:
        // Entrada de mercancia
        entradaMercancia(almacen);
        break;
      case 6:
        // Salida de mercancia
        salidaMercancia(almacen);
        break;
      case 7:
        // Salir del menu
        System.out.println("Saliendo...");
        break;
      }
    } while (opcion != 7);
  }

  public static void mostrarAlmacen(Almacen almacen) {
    System.out.println(almacen);
  }

  private static void altaProducto(Almacen almacen, Menu menuIva) {
    try {
      System.out.println("\n======DAR DE ALTA PRODUCTO======");
      almacen.anadirArticulo(Teclado.leerCadena("Introduzca la descripción del producto."), Teclado.leerDecimal("Introduzca el precio de compra del producto."), Teclado.leerDecimal("Introduzca el precio de venta del producto."), Teclado.leerEntero("Introduzca el stock del producto."), gestionaIva(menuIva.gestionMenu()));
    } catch (NumberFormatException | EnteroNoValidoException | IvaInvalidoException | StockNegativoException | PrecioDeCompraNegativoException | PrecioDeVentaNegativoException e) {
      System.err.println(e.getMessage());
    }
  }

  private static void bajaProducto(Almacen almacen) {
    System.out.println("\n======DAR DE BAJA PRODUCTO======");
    try {
      if (!almacen.retirarArticulo(Teclado.leerEntero("Introduzca el código del producto a eliminar"))) {
        System.err.println("No se ha encontrado el producto.");
      }
    } catch (EnteroNoValidoException enve) {
      System.err.println(enve.getMessage());
      return;
    }
    
  }

  private static void modificaProducto(Almacen almacen, Menu menuIva) {
    System.out.println("\n======MODIFICAR EL PRODUCTO======");
    try {
      int codigo=Teclado.leerEntero("Introduzca el código del producto: ");
      System.out.println(almacen.get(codigo));
      almacen.modificaProducto(codigo,
          Teclado.leerCadena("Nueva descripción: "), Teclado.leerDecimal("Nuevo precio de compra: "), Teclado.leerDecimal("Nuevo precio de venta: "), Teclado.leerEntero("Nuevo stock: "), gestionaIva(menuIva.gestionMenu()));
    } catch (IvaInvalidoException | StockNegativoException | PrecioDeCompraNegativoException
        | PrecioDeVentaNegativoException |ProductoNoEncontradoException | NumberFormatException | EnteroNoValidoException err) {
      System.err.println(err.getMessage());
    }
  }

  private static void entradaMercancia(Almacen almacen) {
    System.out.println("\n======ENTRADA DE MERCANC�A======");
    try {
      int codigo = Teclado.leerEntero("Introduzca el código del producto");
      System.out.println(almacen.get(codigo));
      almacen.incrementarExistencias(codigo, Teclado.leerEntero("Introduzca la cantidad que desea sumar al stock"));
    } catch (ProductoNoEncontradoException | ArgumentoNegativoException | EnteroNoValidoException | StockNegativoException | ArrayIndexOutOfBoundsException e) {
      System.err.println(e.getMessage());
      return;
    } 
  }

  private static void salidaMercancia(Almacen almacen) {
    System.out.println("\n======SALIDA DE MERCANC�A======");
    try {
      int codigo = Teclado.leerEntero("Introduzca el código del producto");
      System.out.println(almacen.get(codigo));
      almacen.reducirExistencias(codigo, Teclado.leerEntero("Introduzca la cantidad que desea restar al stock"));
    } catch (ProductoNoEncontradoException | ArgumentoNegativoException | EnteroNoValidoException | StockNegativoException | ArrayIndexOutOfBoundsException e) {
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