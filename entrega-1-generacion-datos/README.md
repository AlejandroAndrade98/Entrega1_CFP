# Entrega 1 - Generacion y clasificacion de datos

Proyecto de Eclipse para Java 8. Esta primera entrega genera los archivos de entrada que se usaran en el programa de reportes de ventas.

## Ejecucion

1. En Eclipse seleccione **File > Import > Existing Projects into Workspace**.
2. Seleccione la carpeta `entrega-1-generacion-datos`.
3. Abra `src/GenerateInfoFiles.java`.
4. Ejecute la clase como **Java Application**.

El programa no solicita datos al usuario. Al terminar, crea los archivos dentro de `data/input`:

- `products.csv`: `IDProducto;NombreProducto;PrecioPorUnidad`
- `salesmen.csv`: `TipoDocumento;NumeroDocumento;Nombres;Apellidos`
- `sales_<nombre>_<id>.csv`: un archivo de ventas por vendedor. La primera linea identifica al vendedor y las siguientes contienen `IDProducto;CantidadVendida`.

## Requisitos cubiertos

- Clase ejecutable requerida: `GenerateInfoFiles`.
- Metodos requeridos: `createSalesMenFile`, `createProductsFile` y `createSalesManInfoFile`.
- Datos pseudoaleatorios, coherentes y sin valores negativos.
- Productos referenciados en ventas que existen en `products.csv`.
- Nombres en ingles, indentacion y documentacion JavaDoc.

La segunda clase con `main`, llamada exactamente `main`, se incorporara en la entrega final para leer estos archivos y generar los reportes.
