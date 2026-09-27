# Generacion y clasificacion de datos

Proyecto de Eclipse para Java 8. Incluye la Entrega 1 y la version preliminar de la Entrega 2.

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
- Documento `faltantes_entrega_2.txt` con el estado de esta entrega preliminar.
