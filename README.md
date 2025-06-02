# ControlGastos - Aplicación Web de Control de Gastos Personales

ControlGastos es una aplicación web desarrollada en ASP.NET Core MVC y SQL Server, diseñada para ayudar a los usuarios a llevar un registro y control detallado de sus ingresos y egresos personales, gestionados por fondo monetario.

[![.NET](https://img.shields.io/badge/.NET-7.0-blueviolet.svg)](https://dotnet.microsoft.com/download/dotnet/7.0)
[![SQL Server](https://img.shields.io/badge/SQL%20Server-on%20Windows%2FLinux%2FAzure-orange.svg)](https://www.microsoft.com/es-es/sql-server/sql-server-downloads)
[![ASP.NET Core MVC](https://img.shields.io/badge/ASP.NET%20Core-MVC-green.svg)](https://docs.microsoft.com/aspnet/core/mvc/overview)

## ✨ Características Principales

*   **Gestión de Datos Maestros:**
    *   Mantenimiento de Tipos de Gasto (con generación automática de código).
    *   Mantenimiento de Fondos Monetarios (cuentas bancarias, efectivo, etc.).
*   **Registro de Movimientos:**
    *   Gestión de Presupuestos mensuales por tipo de gasto.
    *   Registro detallado de Gastos (encabezado y múltiples ítems por gasto).
    *   Validación de sobregiro de presupuesto al registrar gastos, con alertas al usuario.
    *   Registro de Depósitos a fondos monetarios.
*   **Consultas y Reportes:**
    *   Consulta consolidada de todos los movimientos (ingresos y egresos) por rango de fechas.
    *   Gráfico comparativo (barras) de Presupuesto vs. Ejecución por tipo de gasto.
*   **Autenticación y Autorización:**
    *   Sistema de login con usuario administrador predefinido (`admin`/`admin`).
    *   Acceso restringido a funcionalidades de mantenimiento solo para el administrador.

## 🔗 Enlace a la Aplicación Publicada

**Visita la aplicación en vivo:** [https://TU_APLICACION_PUBLICADA.COM](https://TU_APLICACION_PUBLICADA.COM)
*(Reemplaza esta URL con el enlace real de tu aplicación desplegada)*

### Credenciales de Acceso (para la aplicación publicada)

*   **Usuario:** `admin`
*   **Contraseña:** `admin`

## 🛠️ Tecnologías Utilizadas

*   **Backend:** ASP.NET Core MVC (.NET 7.0)
*   **Base de Datos:** SQL Server
*   **ORM:** Entity Framework Core
*   **Autenticación:** ASP.NET Core Identity
*   **Frontend:**
    *   HTML5, CSS3, JavaScript (vanilla)
    *   Bootstrap 5 (para estilos y componentes UI)
    *   Chart.js (para la generación de gráficos)
*   **Arquitectura:** Aproximación a arquitectura por capas (Dominio, Aplicación, Infraestructura, Web/Presentación).

## 📂 Estructura del Proyecto

El proyecto sigue una estructura orientada a la separación de responsabilidades:

*   `ControlGastos.sln`: Archivo de solución de Visual Studio.
*   `src/`: Contiene el código fuente de los proyectos.
    *   `ControlGastos.Domain/`: Entidades del dominio, enums y lógica de negocio central.
    *   `ControlGastos.Application/`: Lógica de aplicación, casos de uso, DTOs, e interfaces de servicios.
    *   `ControlGastos.Infrastructure/`: Implementaciones de persistencia (DbContext, migraciones, seeders) y otros servicios de infraestructura.
    *   `ControlGastos.Web/`: Proyecto ASP.NET Core MVC (controladores, vistas, wwwroot, configuración de inicio).
*   `README.md`: Este archivo.

*(Ajusta los nombres de los proyectos si usaste `GastosApp` en lugar de `ControlGastos` internamente)*

## ⚙️ Configuración y Puesta en Marcha Local

Sigue estos pasos para configurar y ejecutar la aplicación en tu entorno de desarrollo local.

### Requisitos Previos

*   **.NET SDK 7.0** (o la versión correspondiente que utilizaste).
*   **SQL Server** (cualquier edición: Express, Developer, LocalDB, etc.).
*   Un **IDE** como Visual Studio 2022 o Visual Studio Code (con extensiones de C#).
*   **(Opcional) Git** para clonar el repositorio.

### Pasos de Instalación

1.  **Clonar el Repositorio (si aplica):**
    ```bash
    git clone https://URL_DE_TU_REPOSITORIO.git ControlGastosApp
    cd ControlGastosApp
    ```

2.  **Configurar la Cadena de Conexión:**
    *   Abre el archivo `src/ControlGastos.Web/appsettings.Development.json` (o `appsettings.json`).
    *   Modifica la cadena `DefaultConnection` en la sección `ConnectionStrings` para que apunte a tu instancia local de SQL Server.

    **Ejemplo para SQL Server LocalDB:**
    ```json
    {
      "ConnectionStrings": {
        "DefaultConnection": "Server=(localdb)\\mssqllocaldb;Database=ControlGastosDB_Local;Trusted_Connection=True;MultipleActiveResultSets=true;TrustServerCertificate=True;"
      },
      // ... otras configuraciones ...
    }
    ```
    (Consulta la sección "Ejemplos de Cadenas de Conexión" más abajo para otras opciones).

3.  **Crear Base de Datos y Aplicar Migraciones:**
    *   Abre una terminal o Símbolo del sistema.
    *   Navega al directorio del proyecto web: `cd src/ControlGastos.Web`
    *   Ejecuta el siguiente comando:
        ```bash
        dotnet ef database update
        ```
        Esto creará la base de datos (si no existe) y aplicará la estructura de tablas definida por las migraciones. También ejecutará el seeder para crear el usuario `admin`.

4.  **Ejecutar la Aplicación:**
    *   **Desde la terminal** (en la carpeta `src/ControlGastos.Web`):
        ```bash
        dotnet run
        ```
    *   **Desde Visual Studio:**
        Abre `ControlGastos.sln`, establece `ControlGastos.Web` como proyecto de inicio y presiona `Ctrl+F5` o `F5`.

5.  **Acceder a la Aplicación:**
    Abre tu navegador y ve a la URL indicada por la consola (ej. `https://localhost:7XXX` o `http://localhost:5XXX`).

    *   **Usuario local:** `admin`
    *   **Contraseña local:** `admin`

### Ejemplos de Cadenas de Conexión para `appsettings.Development.json`

*   **SQL Server Express (instancia por defecto `SQLEXPRESS`, Autenticación Windows):**
    `"Server=.\\SQLEXPRESS;Database=ControlGastosDB_Local;Trusted_Connection=True;MultipleActiveResultSets=true;TrustServerCertificate=True;"`
*   **SQL Server (Autenticación SQL):**
    `"Server=TU_SERVIDOR;Database=ControlGastosDB_Local;User ID=TU_USUARIO;Password=TU_CONTRASENA;Trusted_Connection=False;Encrypt=False;TrustServerCertificate=True;"`

*(Ajusta `Database=ControlGastosDB_Local` al nombre que prefieras para tu base de datos de desarrollo)*

## 🚀 Despliegue

La aplicación está diseñada para ser desplegada en plataformas que soporten ASP.NET Core y SQL Server, como:
*   Azure App Service con Azure SQL Database.
*   Servidores Windows con IIS.
*   Otros proveedores de hosting ASP.NET.

Para el despliegue, se recomienda:
1.  Publicar el proyecto `ControlGastos.Web` en modo `Release`.
    ```bash
    dotnet publish src/ControlGastos.Web -c Release -o ./publish_output
    ```
2.  Configurar la cadena de conexión (`DefaultConnection`) en el entorno de producción para apuntar a la base de datos de producción.
3.  Asegurar que las migraciones se apliquen a la base de datos de producción (ya sea automáticamente al inicio de la app o como un paso de despliegue separado).

## 🤝 Contribuciones

Este proyecto fue desarrollado como un ejercicio técnico. Por el momento, no se buscan contribuciones externas, pero el código está disponible para consulta y aprendizaje.

## 📄 Licencia

Este proyecto se distribuye bajo la licencia [MIT](LICENSE.md). (Opcional: crea un archivo `LICENSE.md` con el texto de la licencia MIT si decides incluir una).

## ✍️ Autor

*   **Nombre:** [Tu Nombre Completo]
*   **Email:** [Tu Email de Contacto]
*   **(Opcional) Perfil de GitHub/LinkedIn:** [Enlace a tu perfil]

---

Este README es bastante completo. Asegúrate de:
*   Reemplazar todos los placeholders como `https://TU_APLICACION_PUBLICADA.COM`, `https://URL_DE_TU_REPOSITORIO.git`, `[Tu Nombre Completo]`, etc.
*   Verificar que los nombres de los proyectos (`ControlGastos.Web`, etc.) coincidan con los que realmente usaste.
*   Ajustar la versión de .NET SDK si usaste una diferente a 7.0.
*   (Opcional) Crear un archivo `LICENSE.md` si decides especificar una licencia. La MIT es una buena opción para proyectos de ejemplo. Puedes encontrar el texto de la licencia MIT fácilmente en línea.

¡Este README debería dar una excelente primera impresión y toda la información necesaria!