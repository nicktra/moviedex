<p align="center">
  <a href="https://github.com/nicktra/moviedex">
    <img src="app/src/main/res/drawable/icon.png" alt="Logo" width="80" height="80">
  </a>
</p>

<h3 align="center">MovieDex</h3>

<p align="center">
    MovieDex is android app about Movies and TV Shows. Built by applying Clean Architecture, Reactive Programming, Dependency Injection, Modularization, Continuous Integration, Security, and Useful Libraries.
    <br />
    <a href="https://github.com/nicktra/moviedex"><strong>Explore the docs »</strong></a>
</p>

[![nicktra](https://circleci.com/gh/nicktra/moviedex.svg?style=shield)](https://circleci.com/gh/nicktra/moviedex)

# MovieDex

MovieDex is android app about Movies and TV Shows. Built by applying Clean Architecture, Reactive Programming, Dependency Injection, Modularization, Continuous Integration, Security, and Useful Libraries.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Things you need to install the software and how to install them

* Android Studio (I use Android Studio 4.1.1)

### Installing

1. Clone the repo
```sh
git clone https://github.com/nicktra/moviedex.git
```
2. Open with your Android Studio

## Features

* Popular Movies
* Popular TV Shows
* Favorited Movies & TV Shows
* Detail Movie & TV Show
* Share Movie & TV Show
* Support Dark Theme

## Screenshots App
<pre>
<img src="screenshots/screenshot1.jpg" width="25%">    <img src="screenshots/screenshot2.jpg" width="25%">    <img src="screenshots/screenshot3.jpg" width="25%">    <img src="screenshots/screenshot4.jpg" width="25%">    <img src="screenshots/screenshot5.jpg" width="25%">    <img src="screenshots/screenshot6.jpg" width="25%">    <img src="screenshots/screenshot7.jpg" width="25%">    <img src="screenshots/screenshot8.jpg" width="25%">>
</pre>

## Tech Stack
* MVVM + Clean Architecture
* Modularization
  * Module [core](https://github.com/nicktra/moviedex/tree/master/core) as library module
  * Module [favorite](https://github.com/nicktra/moviedex/tree/master/favorite) as dynamic-feature module
* Koin for dependency injection
* Kotlin Coroutines for handling data flow
* Shimmer for loading animation
* Leak Canary for memory leak detection
* Continuous Integration with Circle-CI

## Security
* Encryption with SQLCipher
* Obfuscation with Proguard
* Certificate Pinning with OkHttp

## Built With

* [Kotlin](https://kotlinlang.org/) - The Programming Language
* [Retrofit](https://github.com/square/retrofit) A type-safe HTTP client for Android
* [View Binding](https://developer.android.com/topic/libraries/view-binding) is a feature that allows you to more easily write code that interacts with views.
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) is an observable data holder class.
* [Navigation Component](https://developer.android.com/guide/navigation) helps you implement navigation, from simple button clicks to more complex patterns, such as app bars and the navigation drawer.
* [Room](https://developer.android.com/topic/libraries/architecture/room) persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) class is designed to store and manage UI-related data in a lifecycle conscious way. 
* [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) Lifecycle-aware components perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
* [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines) a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
* [Koin](https://insert-koin.io/) A pragmatic lightweight dependency injection framework for Kotlin developers.
* [SQLCipher](https://github.com/sqlcipher/android-database-sqlcipher) Android SQLite API based on SQLCipher
* [Leak Canary](https://github.com/square/leakcanary) A memory leak detection library for Android.
* [Shimmer](https://github.com/facebook/shimmer-android) an Android library that provides an easy way to add a shimmer effect to any view in your Android app.
* [Glide](https://github.com/bumptech/glide) is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
* [CircleImageView](https://github.com/hdodenhof/CircleImageView) A circular ImageView for Android


## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Contact

* **Konik Saputra** - [@koniksaputra](https://twitter.com/koniksaputra) - koniksaputra@live.com

## License

This project is licensed under the MIT License. See `LICENSE` for more information