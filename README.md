# Sistema CRM para Gestión de Clientes

Este proyecto es un sistema CRM (Customer Relationship Management) desarrollado en Java para la gestión integral de clientes, productos, proveedores, empleados, categorías y facturas.

## Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación principal utilizado para el desarrollo del sistema.
- **Maven**: Sistema de gestión de dependencias y construcción del proyecto.
- **MySQL**: Sistema de gestión de base de datos relacional para el almacenamiento de la información.
- **JDBC**: API de Java para la conexión y manipulación de bases de datos.
- **MySQL Connector/J 8.4.0**: Driver oficial de MySQL para Java.
- **Java Swing**: Framework para la creación de la interfaz gráfica de usuario (GUI).

## Interfaz de Usuario

El sistema cuenta con una interfaz gráfica desarrollada en Java Swing que incluye:

- Ventanas principales para cada módulo del sistema
- Formularios para la entrada de datos
- Tablas para la visualización de registros
- Menús de navegación intuitivos
- Diálogos para confirmaciones y mensajes al usuario

## Estructura del Proyecto

El proyecto sigue una arquitectura por capas con los siguientes componentes:

### Modelos
Clases que representan las entidades del sistema:
- Cliente
- Producto
- Proveedor
- Empleado
- Categoría
- Factura
- Provee (relación entre proveedores y productos)

### Repositorios
Clases responsables de la comunicación con la base de datos para cada entidad:
- ClienteRepository
- ProductoRepository
- ProveedorRepository
- EmpleadoRepository
- CategoriaRepository
- FacturaRepository
- ProveeRepository

### Menús
Interfaces de usuario para la gestión de cada entidad:
- MenuCliente
- MenuProducto
- MenuProveedor
- MenuEmpleado
- MenuCategoria
- MenuFactura
- MenuProvee

### Base de Datos
La aplicación utiliza una base de datos MySQL llamada "proyectojava" para almacenar toda la información del sistema.

## Funcionalidades Principales

- Gestión completa de clientes (alta, baja, modificación, consulta)
- Gestión de productos y categorías
- Administración de proveedores
- Control de empleados
- Emisión y gestión de facturas
- Registro de relaciones entre proveedores y productos

## Requisitos del Sistema

- Java 17 o superior
- MySQL 8.0 o superior
- Maven 3.6 o superior

## Configuración de la Base de Datos

La configuración de conexión a la base de datos se encuentra en el archivo `DatabaseConnection.java`. Por defecto, utiliza:
- URL: jdbc:mysql://localhost:3306/proyectojava
- Usuario: root
- Contraseña: 1234

## Instalación y Ejecución

1. Clonar este repositorio
2. Crear la base de datos "proyectojava" en MySQL
3. Ejecutar el script SQL ubicado en la carpeta `resources` (si está disponible)
4. Compilar el proyecto con Maven: `mvn clean package`
5. Ejecutar la aplicación: `java -cp target/demo-1.0-SNAPSHOT-jar-with-dependencies.jar org.carlosydiego.crmclientes.app.Main`



## Autores

- Carlos y Diego 