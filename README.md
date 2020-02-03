# OpenWeatherMap API: 

For getting APPID (secret key for weather api) we need to create an account and sign-in at: https://home.openweathermap.org/users/sign_in

# App Workflow:

After launching the app, we hit two API's:

1. For Current Weather data:  https://api.openweathermap.org/data/2.5/weather?id=1277333&units=metric&APPID=<your_appid>
2. For 5 days 3 hours Forecast Weather data:  https://api.openweathermap.org/data/2.5/forecast?id=1277333&units=metric&APPID=<your_appid>


# Project Specifications:

Language: Kotlin </br>
Architecture: MVVM </br>
Network Calls: Retrofit, RxJava/RxAndroid </br>
Logging Network Calls: OkHttp/HttpLoggingInterceptor </br>
Image Processing: Glide </br>
Layout: ConstraintLayout </br>
Supported OS: 22+



