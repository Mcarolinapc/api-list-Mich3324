# ApiDisney 

**ApiDisney** es una aplicación móvil nativa para Android enfocada en explorar el universo de personajes de Disney. Este proyecto escolar destaca por integrar una arquitectura robusta orientada a componentes modernos de Android, gestionando de forma eficiente el consumo de servicios web, la persistencia en bases de datos locales y la reactividad de la interfaz.

---

##  Resumen del Proyecto

* **Problema/Desafío**: Consumir datos externos de una API pública de manera asíncrona, garantizando que la aplicación sea capaz de almacenar información de forma local y recordar las preferencias estéticas elegidas por el usuario entre sesiones.
* **Propósito**: Activar un flujo de arquitectura limpia (MVVM) donde se combinan peticiones de red en tiempo real con una base de datos local persistente.

---

##  Características Principales

* **Consumo de API REST (Retrofit)**: Integración con la API pública de Disney para consultar dinámicamente personajes, detalles de películas, series de televisión y videojuegos.
* **Persistencia Local de Favoritos (Room Database)**: Los personajes marcados con el botón de corazón se almacenan de forma local en una base de datos SQLite gestionada a través de **Room**, lo que permite consultarlos incluso sin conexión a internet.
* **Persistencia de Configuración (DataStore)**: El estado del tema visual (Claro/Oscuro) y la disposición de la interfaz (Vista de Rejilla o Lista) se guardan en **Jetpack DataStore Preferences**, asegurando que los ajustes del usuario permanezcan al reiniciar la app.
* **Carga de Imágenes Eficiente (Coil)**: Procesamiento y renderizado asíncrono de las imágenes de los personajes mediante Coil, optimizando la memoria y gestionando la caché del dispositivo.
* **Flujo de Navegación Seguro**: Implementación de **Jetpack Compose Navigation** para transiciones fluidas entre la pantalla principal, los detalles del personaje y el panel de configuración.

---

##  Core Tecnológico

####  Mobile Development & UI
* ![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white)
* ![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
* ![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)

<div style="display: flex; flex-wrap: wrap; gap: 20px;">

  <div style="flex: 1; min-width: 250px;">
    <h3> Networking & Async</h3>
    <ul>
      <li><strong>Retrofit & Gson:</strong> Cliente HTTP y parseo automático de estructuras JSON.</li>
      <li><strong>OkHttp Logging Interceptor:</strong> Monitorización y depuración de peticiones de red.</li>
      <li><strong>Kotlin Coroutines:</strong> Gestión de hilos asíncronos para tareas en segundo plano (peticiones de red y consultas a base de datos).</li>
    </ul>
  </div>

  <div style="flex: 1; min-width: 250px;">
    <h3> Storage & Architecture</h3>
    <ul>
      <li><strong>Room DB (SQLite):</strong> Almacenamiento local estructurado para la gestión de favoritos.</li>
      <li><strong>DataStore Preferences:</strong> Guardado síncrono/asíncrono de configuraciones de usuario mediante flujos reactivos.</li>
      <li><strong>MVVM Architecture:</strong> Separación de responsabilidades con ViewModels, States y LiveData.</li>
    </ul>
  </div>

</div>

---

## Demostración y Capturas de la Aplicación

<div align="center">
  <h3 Vídeo Demostrativo de la App</h3>
 https://github.com/user-attachments/assets/3daa6122-3628-4c1a-89c5-7e31f028986e
  <p><em>Exploración de personajes, alternancia de vistas (Grid/Lista) y sistema de favoritos en tiempo real.</em></p>
</div>

<br>

<div style="display: flex; flex-wrap: wrap; gap: 10px; justify-content: center;">
  <img src="Imagenes/home_grid.jpeg" width="220" alt="Vista Home en Grid">
  <img src="Imagenes/home_list.jpeg" width="220" alt="Vista Home en Lista">
  <img src="Imagenes/detalle.jpeg" width="220" alt="Detalle del Personaje">
   <img src="Imagenes/favoritos.jpeg" width="220" alt="Pantalla Favoritos">
  <img src="Imagenes/configuracion.jpeg" width="220" alt="Pantalla de Configuración">
  
</div>

---

##  Autor

* **Michelle Carolina Posligua Contreras** (Desarrollo Android / Fullstack)
* **Institución**: Institut Tecnològic Barcelona (ITB)
