# Movie Cool

Ve recomendaciones, califica y agrega a tus favoritos las películas  con las que contamos para tener sus datos en cualquier lugar.

Nota: Utiliza la cuenta de themoviedb.org

## Compilación 

1. Clonar el repositorio
`git clone `

2. Agregar token y api_key de the moviedb.org en el build.gradle(App)

```
resValue 'string', "moviedb_api_4", "{YOUR_TOKEN}"
resValue 'string', "moviedb_api_3", "{YPUR_API_KEY}"
```

3. Correr la aplicación en un emulador o dispositivo físico

## Pruebas

Se creó una prueba de unidad para MovieRepository revisando que los datos de la base de datos si crearan la lista paginada

## Aplicación

Se puede encontrar el apk de la aplicación en el siguiente [link](https://drive.google.com/open?id=1AmovnLHxaLJWBxZlYfGOZm2OMM-Obj3U)


### Esta aplicación tiene las siguientes capas:
* data: Encargada del manejo de todos los datos dentro de la aplicación  
    * AppSharedPreferences.kt
    * Result.kt
    * db: Encargada de la base de datos

        * MovieCoolDataBase.kt
        * MovieDao.kt
        * MovieTypeConverter.kt
    * repositories: Encargada de definir la forma de obtener y enviar de datos

        * AuthenticationRepository.kt
        * MovieActionsRepository.kt
        * MovieBoundaryCallback.kt
        * MovieDiscoverBoundaryCallback.kt
        * MovieFavoriteBoundaryCallback.kt
        * MovieRaedBoundaryCallback.kt
        * MovieRepository.kt
        * MovieV3Repository.kt

* UI: Encargada de las vistas y el parseo de datos para la vista
    * DetailFragment.kt
    * DetailViewModel.kt
    * FavoritesFragment.kt
    * FavoritesViewModel.kt
    * HomeActivity.kt
    * LoginActivity.kt
    * LogInFragment.kt
    * LogInViewModel.kt
    * MainActivity.kt
    * OnBoardingFragment.kt
    * OnboardingViewModel.kt
    * RatedFragment.kt
    * RatedViewModel.kt
    * RecommendationsFragment.kt
    * RecommendationsViewModel.kt
    * SearchFragment.kt
    * SearchViewModel.kt
    * adapter: ui de los adaptadores
        * MoviewAdapter.kt

* network: Encargado de la conexión  a internet
    * MovieDb3Api.kt
    * MovieDb4Api.kt
    * Routes.kt

* model: Encargado de todas las data class de la aplicación
    * AccessTokenRequest.kt
    * AccessTokenResponse.kt
    * AuthRequest.kt
    * AuthResponse.kt
    * FavoriteRequest.kt
    * ListResponse.kt
    * Movie.kt
    * RateRequest.kt
    * SessionV3Request.kt
    * SessionV3Response.kt
    * SimpleResponse.kt


### Principio de responsabilidad unica

Este principio nos marca que cada parte del código módulo, clase, etc solo debe tener control sobre una parte especifica del código, como también que cada función ejecute solo un caso de uso en específico.

### código limpio

Considero que para tener un código limpio lo más importante es la estructura del código ya que debe ser legible y fácil de navegar a través de él, hace que se pueda llegar a tener una documentación explícita del funcionamiento de la aplicación y el código.

Las características que deben cumplir para mi son los siguiente:

* Cumplir con el principio de responsabilidad única cubierto previamente
* Seguir una arquitectura correcta para toda la aplicación y que sea entendible cual es
* Agregar las clases en el lugar correcto dentro de la estructura del proyecto
* Que las funciones y variables tengan nombre entendibles de que hacen y para que sirven
* Contar con un árbol de dependencias definido para evitar spaghetti code 
* Definir bien los accesos de cada función 
* Comentarios de JavaDoc o Dokka para facilitar el entendimiento de las clases y funciones
* Cumplir con las convenciones de programación o de un lint en específico

### Arquitectura

Esta aplicación sigue la arquitectura Model View ViewModel, esta decisión se tomó analizando lo requerimientos de la aplicación y la facilidad para programar dentro de android. Al tomar en cuenta lo anterior se concluyo que por la forma en que se manejarian los datos(Local y Api) se ajustaba bien a la arquitectura de MVVM ayudado también con un repository pattern.

![Architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

En la parte de android los componente de jetpack ayudan a implementar de una buena manera esta arquitectura como también dar muchas herramientas para proveer el correcto funcionamiento dentro de la aplicación, todo brindandolo de una manera sencilla y rápida de desarrollar.

