![Karumi logo][karumilogo] KataSuperHeroes for Android. [![Build Status](https://travis-ci.org/Karumi/KataSuperHeroes.svg?branch=master)](https://travis-ci.org/Karumi/https://travis-ci.com/Karumi/KataSuperHeroesAndroid)
============================

- We are here to practice UI Testing.
- We are going to use [Espresso][espresso] to interact with the Application UI.
- We are going to use [Dagger2][dagger2] to replace production code with [Test Doubles][testDoubles].
- We are going to practice pair programming.

---

## Tasks

We've developed and Android application to show Super Heroes in two views:

![ApplicationScreencast][applicationScreencast]

This Application is based on two main Activities:

* MainActivity, showing a list of super heroes with name, photo and a special badge if is part of the Avengers Team.

![MainActivityScreenhot][mainActivityScreenshot]

* SuperHeroDetailActivity, showing detailed information for a super hero like the name, photo and description.

![SuperHeroDetailActivityScreenshot][superHeroDetailActivityScreenshot]


**Your task as Android Developer for this kata is to write all the UI tests needed to check if the Application UI is working as should.**

Before to start execute the application and explore it manually to design your test scenarios.

---

#License

Copyright 2015 Karumi

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