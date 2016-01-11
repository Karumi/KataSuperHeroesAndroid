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

* ``MainActivity`` showing a list of super heroes with name, photo and a special badge if is part of the Avengers Team.

![MainActivityScreenhot][mainActivityScreenshot]

* ``SuperHeroDetailActivity`` showing detailed information for a super hero like the name, photo and description.

![SuperHeroDetailActivityScreenshot][superHeroDetailActivityScreenshot]


**Your task as Android Developer is to write all the UI tests needed to check if the Application UI is working as should. The application architecture, dependencies and configuration is ready to just start writing tests. In this project you'll find a Dagger2 configured to be able to replace production code with test doubles easily and Espresso to be able to interact with the application user interface.**

This repository is ready to build the application, pass the checkstyle and your tests in Travis-CI environments. **Our recommendation for this exercise is to fork this repository, checkout to the brach project-withtout-tests and start testing the application.**

Before to start, execute the application, explore it manually and review the code to design your test scenarios.

## Extra Tasks

If you've covered all the application functionality using UI tests try to continue with the following tasks:

* Add a pull to refresh mechanism to ``MainActivity`` and test it.
* Modify ``SuperHeroDetailActivity`` to handle an error case where the name of the super hero used to start this activity does not exist and show a message if this happens.
* Modify the project to handle connection errors and show a ``SnackBar`` to indicate something went wrong.
* Modify ``SuperHeroesRepository`` test double to perform a ``Thread.sleep`` and use the custom idling resources you'll find in this repository to get your tests working.

---

## Documentation

There are some links which can be useful to finish these tasks:

* [Android Testing Support Library official documentation][androidTestingDocumentation]
* [Espresso Cheat Sheet][espressoCheatSheet]
* [Espresso Idling Resources][espressoIdlingResources]
* [Espresso Custom Matchers][espressoCustomMatchers]
* [Finding UI views][findingUIViews]
* [Espresso Test Toolbat Title][toolbarMatcher]

Data provided by Marvel. © 2016 MARVEL

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
[espressoIdlingResources]: http://dev.jimdo.com/2014/05/09/wait-for-it-a-deep-dive-into-espresso-s-idling-resources/
[espressoCustomMatchers]: http://blog.xebia.com/android-custom-matchers-in-espresso/
[findingUIViews]: http://www.adavis.info/2015/12/testing-tricks-2-finding-ui-views.html?utm_source=Android+Weekly&utm_campaign=9ed0cecaff-Android_Weekly_186&utm_medium=email&utm_term=0_4eb677ad19-9ed0cecaff-337845529
[toolbarMatcher]: http://blog.sqisland.com/2015/05/espresso-match-toolbar-title.html