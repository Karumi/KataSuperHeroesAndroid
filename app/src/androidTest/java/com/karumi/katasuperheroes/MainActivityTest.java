/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.karumi.katasuperheroes;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import com.karumi.katasuperheroes.di.MainComponent;
import com.karumi.katasuperheroes.di.MainModule;
import com.karumi.katasuperheroes.model.SuperHero;
import com.karumi.katasuperheroes.model.SuperHeroesRepository;
import com.karumi.katasuperheroes.recyclerview.RecyclerViewInteraction;
import com.karumi.katasuperheroes.ui.view.MainActivity;
import it.cosenonjaviste.daggermock.DaggerMockRule;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class) @LargeTest public class MainActivityTest {

  private static final int ANY_NUMBER_OF_SUPER_HEROES = 3;

  @Rule public DaggerMockRule<MainComponent> daggerRule =
      new DaggerMockRule<>(MainComponent.class, new MainModule()).set(
          new DaggerMockRule.ComponentSetter<MainComponent>() {
            @Override public void setComponent(MainComponent component) {
              SuperHeroesApplication app =
                  (SuperHeroesApplication) InstrumentationRegistry.getInstrumentation()
                      .getTargetContext()
                      .getApplicationContext();
              app.setComponent(component);
            }
          });

  @Rule public ActivityTestRule<MainActivity> activityRule =
      new ActivityTestRule<>(MainActivity.class, false, false);

  @Mock SuperHeroesRepository repository;

  @Test public void showsEmptyCaseIfThereAreNoSuperHeroes() {
    givenThereAreNoSuperHeroes();

    startActivity();

    onView(withText("¯\\_(ツ)_/¯")).check(matches(isDisplayed()));
  }

  @Test public void showsSuperHeroesNameIfThereAreSuperHeroes() {
    List<SuperHero> superHeroes = givenThereAreSuperHeroes(ANY_NUMBER_OF_SUPER_HEROES);

    startActivity();

    for (SuperHero superHero : superHeroes) {
      onView(withText(superHero.getName())).check(matches(isDisplayed()));
    }
  }

  @Test public void showsAvengersBadgeIfSuperHeroIsPartOfTheAvengersTeam() {
    List<SuperHero> superHeroes = givenThereAreSuperHeroes(ANY_NUMBER_OF_SUPER_HEROES, true);

    startActivity();

    RecyclerViewInteraction.<SuperHero>onRecyclerView(withId(R.id.recycler_view))
        .withItems(superHeroes)
        .check(new RecyclerViewInteraction.ItemViewAssertion<SuperHero>() {
          @Override public void check(SuperHero superHero, View view, NoMatchingViewException e) {
            matches(hasDescendant(allOf(withId(R.id.iv_avengers_badge),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))).check(view, e);
          }
        });
  }

  private List<SuperHero> givenThereAreSomeAvengers(int numberOfAvengers) {
    return givenThereAreSuperHeroes(numberOfAvengers, true);
  }

  private List<SuperHero> givenThereAreSuperHeroes(int numberOfSuperHeroes) {
    return givenThereAreSuperHeroes(numberOfSuperHeroes, false);
  }

  private List<SuperHero> givenThereAreSuperHeroes(int numberOfSuperHeroes, boolean avengers) {
    List<SuperHero> superHeroes = new LinkedList<>();
    for (int i = 0; i < numberOfSuperHeroes; i++) {
      String superHeroName = "SuperHero - " + i;
      String superHeroPhoto = "https://i.annihil.us/u/prod/marvel/i/mg/c/60/55b6a28ef24fa.jpg";

      String superHeroDescription = "Description Super Hero - " + i;
      superHeroes.add(new SuperHero(superHeroName, superHeroPhoto, avengers, superHeroDescription));
    }
    when(repository.getAll()).thenReturn(superHeroes);
    return superHeroes;
  }

  private void givenThereAreNoSuperHeroes() {
    when(repository.getAll()).thenReturn(Collections.<SuperHero>emptyList());
  }

  private void startActivity() {
    activityRule.launchActivity(null);
  }
}