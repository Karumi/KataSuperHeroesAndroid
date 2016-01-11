![Karumi logo][karumilogo] KataSuperHeroes for Android. [![Build Status](https://travis-ci.org/Karumi/KataSuperHeroes.svg?branch=master)](https://travis-ci.org/Karumi/https://travis-ci.com/Karumi/KataSuperHeroesAndroid)
============================

- We are here to practice UI Testing.
- We are going to use [Espresso][espresso] to interact with the Application UI.
- We are going to use [Dagger2][dagger2] to replace production code with [Test Doubles][testDoubles].
- We are going to practice pair programming.

---

## Tasks

This repository contains an Android application to show Super Heroes information:

![ApplicationScreencast][applicationScreencast]

This Application is based on two Activities:

* ``MainActivity``, showing a list of super heroes with name, photo and a special badge if is part of the Avengers Team.

![MainActivityScreenhot][mainActivityScreenshot]

* ``SuperHeroDetailActivity``, showing detailed information for a super hero like the name, photo and description.

![SuperHeroDetailActivityScreenshot][superHeroDetailActivityScreenshot]


**Your task as Android Developer for this kata is to write all the UI tests needed to check if the Application UI is working as should. The application architecture, dependencies and configuration is ready to just start writing tests. In this project you'll find a Dagger2 configured to be able to replace production code with test doubles easily and Espresso to be able to interact with the application user interface.**

Before to start, execute the application, explore it manually and review the code to design your test scenarios.

## Extra Tasks

If you've covered all the application functionality using UI tests try to continue with the following tasks:

* Add a pull to refresh mechanism to ``MainActivity`` and test it.
* Modify ``SuperHeroDetailActivity`` to handle an error case where the name of the super hero used to start this activity does not exist and show a message if this happens.
* Modify the project to handle connection errors and show a ``SnackBar`` to indicate something went wrong.

---

## Documentation

There are some links which can be useful to finish these tasks:

* [Android Testing Support Library official documentation][androidTestingDocumentation]
* [Espresso Cheat Sheet][espressoCheatSheet]

#License

Copyright 2016 Karumi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[karumilogo]: https://cloud.githubusercontent.com/assets/858090/11626547/e5a1dc66-9ce3-11e5-908d-537e07e82090.png
[espresso]: https://google.github.io/android-testing-support-library/docs/
[dagger2]: http://google.github.io/dagger/
[testDoubles]: http://www.martinfowler.com/bliki/TestDouble.html
[applicationScreencast]: ./art/ApplicationScreencast.gif
[mainActivityScreenshot]: ./art/MainActivityScreenshot.png
[superHeroDetailActivityScreenshot]: ./art/SuperHeroDetailActivityScreenshot.png
[androidTestingDocumentation]: https://google.github.io/android-testing-support-library
[espressoCheatSheet]: https://google.github.io/android-testing-support-library/docs/espresso/cheatsheet/index.html