# Generacion y clasificacion de datos

Proyecto final de Eclipse para Java 8. Incluye las entregas 1, 2 y 3.

## Ejecucion

1. En Eclipse seleccione **File > Import > Existing Projects into Workspace**.
2. Seleccione la carpeta `entrega-1-generacion-datos`.
3. Abra `src/GenerateInfoFiles.java` y ejecutela como **Java Application**.
4. Abra `src/main.java` y ejecutela como **Java Application**.

El primer programa no solicita datos al usuario. Crea los archivos de entrada dentro de `data/input`:

- `products.csv`: `IDProducto;NombreProducto;PrecioPorUnidad`
- `salesmen.csv`: `TipoDocumento;NumeroDocumento;Nombres;Apellidos`
- `sales_<nombre>_<id>.csv`: un archivo de ventas por vendedor. La primera linea identifica al vendedor y las siguientes contienen `IDProducto;CantidadVendida`.

El segundo programa tampoco solicita datos al usuario. Lee los archivos anteriores y crea, dentro de `data/output`:

- `salesmen_report.csv`: `NombreCompleto;DineroRecaudado`, ordenado de mayor a menor recaudo.
- `products_report.csv`: `NombreProducto;PrecioUnitario`, ordenado de mayor a menor cantidad vendida.

## Requisitos cubiertos

- Dos y solo dos clases ejecutables: `GenerateInfoFiles` y `main`.
- Metodos requeridos: `createSalesMenFile`, `createProductsFile` y `createSalesManInfoFile`.
- Datos pseudoaleatorios, coherentes y sin valores negativos.
- Productos referenciados en ventas que existen en `products.csv`.
- Nombres en ingles, indentacion y documentacion JavaDoc.
- Reportes requeridos y validacion de archivos con formato o datos incoherentes.
- Archivo `conslusion.txt` con los aprendizajes, aplicaciones profesionales y
  dificultades presentadas durante el proyecto.
