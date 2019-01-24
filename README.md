![Karumi logo][karumilogo] KataSuperHeroes for Android
============================

- We are here to practice UI Testing.
- We are going to use [Espresso][espresso] to interact with the Application UI.
- We are going to use [Dagger2][dagger2] to replace production code with [Test Doubles][testDoubles].
- We are going to practice pair programming.

---

## Getting started

This repository contains an Android application to show Super Heroes information:

![ApplicationScreencast][applicationScreencast]

This Application is based on two Activities:

* ``MainActivity`` showing a list of super heroes with name, photo and a special badge if is part of the Avengers Team.

![MainActivityScreenhot][mainActivityScreenshot]

* ``SuperHeroDetailActivity`` showing detailed information about a super hero like his or her name, photo and description.

![SuperHeroDetailActivityScreenshot][superHeroDetailActivityScreenshot]


The application architecture, dependencies and configuration is ready to just start writing tests. In this project you'll find  ``Dagger2`` configured to be able to replace production code with test doubles easily and Espresso to be able to interact with the application user interface.


## Tasks

Your task as Android Developer is to **write all the UI tests** needed to check if the Application UI is working as it should. 

**This repository is ready to build the application, pass the checkstyle and your tests in Travis-CI environments.**


Our recommendation for this exercise is:

  * Before starting
    1. Fork this repository.
    2. Checkout `kata-super-heroes` branch.
    3. Execute the application, explore it manually and make yourself familiar with the code.
    4. Execute `MainActivityTest` and watch the only test it contains pass.

  * To help you get started, these are some test cases for `MainActivity`:     
    1. Setup mocked `SuperHeroesRepository` in `MainActivityTest` to return a list of some Super Heroes.
    2. Test that RecyclerView is listing the correct number of elements when `SuperHeroesRepository` returns a list of some Super Heroes.
    3. Test that each of this elements contains the correct Super Hero name.

## Considerations

* If you get stuck, `Master` branch contains already solved tests for `MainActivity` and `SuperHeroDetailActivity`

* A [DaggerMockRule][daggermock] is an utility to let you create [Dagger 2][dagger2] modules dynamically. In this case we are using it to create a new `MainModule` in this testing scope. Instead of returning real objects, this new `MainModule` will returns the mock for `SuperHeroesRepository` defined in this test.

* You will find some utilities to help you test RecyclerViews and Toolbars easily in:
  ``app/src/androidTest/java/com/karumi/katasuperheroes/matchers`` and ``app/src/androidTest/java/com/karumi/katasuperheroes/recyclerview``.

  * `RecyclerViewInteraction`: provides an easy way to apply an Espresso matcher to all of the RecyclerView elements

	```java
	
	RecyclerViewInteraction.<ITEM_TYPE>onRecyclerView(withId(R.id.recycler_view))
	.withItems(A_LIST_OF_ITEMS)
	.check(new RecyclerViewInteraction.ItemViewAssertion<ITEM_TYPE>() {
	    @Override
	    public void check(ITEM_TYPE item, View view, NoMatchingViewException e) {
	        matches(A_MATCHER).check(view, e);
	    }
	});
	```
  * `RecyclerViewItemsCountMatcher`: a matcher that returns true if RecyclerView contains the expected number of elements.

  * `ToolbarMatcher`: a matcher that returns true if a Toolbar with expected title is found.
  
## Extra Tasks

If you've covered all the application functionality using UI tests try to continue with the following tasks:

* Add a pull to refresh mechanism to ``MainActivity`` and test it.
* Modify ``SuperHeroDetailActivity`` to handle an error case where the name of the super hero used to start this activity does not exist and show a message if this happens.
* Modify the project to handle connection errors and show a ``SnackBar`` to indicate something went wrong.
* Modify ``SuperHeroesRepository`` test double to perform a ``Thread.sleep`` and use the custom idling resources you'll find in this repository to get your tests working.

---

## Documentation

There are some links which can be useful to finish these tasks:

* [Kata Super Heroes in Kotlin][kataSuperHeroesKotlin]
* [Android Testing Support Library official documentation][androidTestingDocumentation]
* [Espresso Cheat Sheet][espressoCheatSheet]
* [Espresso Idling Resources][espressoIdlingResources]
* [Espresso Custom Matchers][espressoCustomMatchers]
* [Finding UI views][findingUIViews]
* [Espresso Test Toolbar Title][toolbarMatcher]

Data provided by Marvel. © 2016 MARVEL

# License

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
[espresso]: https://developer.android.com/training/testing/espresso
[dagger2]: http://google.github.io/dagger/
[testDoubles]: http://www.martinfowler.com/bliki/TestDouble.html
[applicationScreencast]: ./art/ApplicationScreencast.gif
[mainActivityScreenshot]: ./art/MainActivityScreenshot.png
[superHeroDetailActivityScreenshot]: ./art/SuperHeroDetailActivityScreenshot.png
[androidTestingDocumentation]: https://developer.android.com/training/testing/
[espressoCheatSheet]: https://developer.android.com/training/testing/espresso/cheat-sheet
[espressoIdlingResources]: http://dev.jimdo.com/2014/05/09/wait-for-it-a-deep-dive-into-espresso-s-idling-resources/
[espressoCustomMatchers]: http://blog.xebia.com/android-custom-matchers-in-espresso/
[findingUIViews]: http://www.adavis.info/2015/12/testing-tricks-2-finding-ui-views.html?utm_source=Android+Weekly&utm_campaign=9ed0cecaff-Android_Weekly_186&utm_medium=email&utm_term=0_4eb677ad19-9ed0cecaff-337845529
[toolbarMatcher]: http://blog.sqisland.com/2015/05/espresso-match-toolbar-title.html
[daggermock]: https://github.com/fabioCollini/DaggerMock
[kataSuperHeroesKotlin]: https://github.com/Karumi/KataSuperHeroesKotlin
